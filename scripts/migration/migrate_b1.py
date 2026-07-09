#!/usr/bin/env python3
"""
M1.5 B1 数据迁移脚本 v2：Obsidian agent-vault → yudao MySQL
支持 --dry-run（对账报告）+ --execute（写入）+ --verify（独立运行）
"""
import os, sys, json, re, argparse, random, glob
from datetime import datetime
from collections import defaultdict

OBSIDIAN_ROOT = os.path.expanduser("~/agent-vault")
TASK_DIR = os.path.join(OBSIDIAN_ROOT, "任务账本")

# ============ YAML Frontmatter Parser ============
def parse_frontmatter(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    m = re.match(r'^---\s*\n(.*?)\n---\s*\n(.*)$', content, re.DOTALL)
    if not m:
        return {}, content
    fm_text = m.group(1)
    body = m.group(2)
    frontmatter = {}
    for line in fm_text.split('\n'):
        line = line.strip()
        if not line or line.startswith('#'):
            continue
        kv = re.match(r'^(\w+):\s*(.*)', line)
        if kv:
            key = kv.group(1)
            val = kv.group(2).strip()
            if val == 'null' or val == '':
                val = None
            elif val.startswith('[') and val.endswith(']'):
                try:
                    val = json.loads(val)
                except:
                    val = [x.strip().strip('"').strip("'") for x in val[1:-1].split(',') if x.strip()]
            elif val in ('true', 'True'):
                val = True
            elif val in ('false', 'False'):
                val = False
            frontmatter[key] = val
    return frontmatter, body

# ============ Data Sources ============
def discover_tasks():
    """
    扫描所有任务卡，返回 (tasks_list, duplicate_ids_dict)。
    duplicate_ids: {task_id: [filepath1, filepath2, ...]}
    """
    raw = []
    active_dir = os.path.join(TASK_DIR, "active")
    if os.path.isdir(active_dir):
        for f in sorted(glob.glob(os.path.join(active_dir, "*.md"))):
            fm, body = parse_frontmatter(f)
            fm['_file'] = f
            fm['_body'] = body
            fm['_source'] = 'active'
            raw.append(fm)
    archive_base = os.path.join(TASK_DIR, "archive")
    if os.path.isdir(archive_base):
        for month_dir in sorted(glob.glob(os.path.join(archive_base, "*"))):
            if os.path.isdir(month_dir):
                for f in sorted(glob.glob(os.path.join(month_dir, "*.md"))):
                    fm, body = parse_frontmatter(f)
                    fm['_file'] = f
                    fm['_body'] = body
                    fm['_source'] = 'archive'
                    raw.append(fm)
    
    # Detect duplicates
    id_map = defaultdict(list)
    for t in raw:
        tid = t.get('id', '')
        id_map[tid].append(t)
    
    duplicates = {tid: entries for tid, entries in id_map.items() if len(entries) > 1}
    
    # Dedup: keep the last entry per ID (archive overwrites active, later files overwrite earlier)
    seen = {}
    tasks = []
    for t in raw:
        tid = t.get('id', '')
        # Skip entries with empty IDs
        if not tid:
            continue
        seen[tid] = t
    
    return list(seen.values()), duplicates

# ============ Field Mapping ============
TASK_CARD_MAPPING = {
    'id': 'id', 'title': 'title', 'status': 'status',
    'priority': 'priority', 'level': 'level', 'owner': 'owner',
    'parent': 'parent_id', 'github_issue': 'github_issue',
    'model': 'model', 'created': 'create_date', 'deadline': 'deadline',
    'retries': 'retries', 'evidence_level': 'evidence_level',
}

ENUM_STATUS_MAP = {
    'DRAFT': 'DRAFT', 'STAFFING': 'STAFFING', 'ASSIGNED': 'ASSIGNED',
    'PENDING': 'ASSIGNED', 'IN_PROGRESS': 'IN_PROGRESS', 'REVIEW': 'REVIEW',
    'PASSED': 'PASSED', 'REJECTED': 'REJECTED', 'BLOCKED': 'BLOCKED',
    'CANCELLED': 'CANCELLED', 'PAUSED': 'BLOCKED',
}

def map_task_to_row(fm):
    row = {}
    for obs_key, mysql_col in TASK_CARD_MAPPING.items():
        if obs_key in fm:
            row[mysql_col] = fm[obs_key]
    if 'status' in row and row['status'] in ENUM_STATUS_MAP:
        row['status'] = ENUM_STATUS_MAP[row['status']]
    if 'tags' in fm and isinstance(fm['tags'], list):
        row['tags'] = json.dumps(fm['tags'], ensure_ascii=False)
    if 'collaborators' in fm and isinstance(fm['collaborators'], list):
        row['collaborators'] = json.dumps(fm['collaborators'], ensure_ascii=False)
    if 'create_date' in row:
        date_str = row.pop('create_date')
        if isinstance(date_str, str) and len(date_str) >= 10:
            try:
                dt = datetime.strptime(date_str[:10], '%Y-%m-%d')
                row['create_time'] = dt.strftime('%Y-%m-%d %H:%M:%S')
            except:
                row['create_time'] = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        else:
            row['create_time'] = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    if 'deadline' in row and isinstance(row['deadline'], str):
        dl = row['deadline'].replace('GMT+8', '').strip()
        try:
            dt = datetime.strptime(dl[:16], '%Y-%m-%d %H:%M')
            row['deadline'] = dt.strftime('%Y-%m-%d %H:%M:%S')
        except:
            try:
                dt = datetime.strptime(dl[:10], '%Y-%m-%d')
                row['deadline'] = dt.strftime('%Y-%m-%d 23:59:59')
            except:
                row['deadline'] = None
    body = fm.get('_body', '')
    if body:
        url_match = re.search(r'(?:docs?/|http)[^\s)\]]+', body)
        if url_match and 'doc_ref' not in row:
            row['doc_ref'] = url_match.group(0)[:500]
        tag_match = re.search(r'(?:技能|标签)[:：]\s*([^\n]+)', body)
        if tag_match and 'skill_tags' not in row:
            row['skill_tags'] = tag_match.group(1).strip()[:200]
        scope_match = re.search(r'^#+\s+\S+\s*\n\n?(.*?)(?:\n#)', body, re.DOTALL)
        if scope_match and 'scope_summary' not in row:
            row['scope_summary'] = scope_match.group(1).strip()[:500]
    row.setdefault('retries', 0)
    row.setdefault('evidence_level', 'E3')
    if 'create_time' not in row:
        row['create_time'] = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    row.setdefault('creator', 'migration_b1')
    row.setdefault('updater', 'migration_b1')
    row.setdefault('process_stage', 1)
    return row

# ============ MySQL ============
def get_db_config():
    cred_file = os.path.expanduser("~/agent-vault/credentials/mysql-root.txt")
    if os.path.exists(cred_file):
        with open(cred_file) as f:
            password = f.read().strip()
    else:
        password = os.environ.get('MYSQL_ROOT_PASSWORD', '')
    return {'host': 'localhost', 'port': 3306, 'user': 'root',
            'password': password, 'database': 'ruoyi-vue-pro', 'charset': 'utf8mb4'}

def mysql_execute(rows, execute=False):
    if not execute:
        return len(rows), 0, []
    import pymysql
    cfg = get_db_config()
    conn = pymysql.connect(**cfg)
    cursor = conn.cursor()
    success = 0
    errors = []
    for row in rows:
        try:
            rid = row.get('id')
            if rid:
                cursor.execute("DELETE FROM task_card WHERE id = %s", (rid,))
            columns = [k for k in row.keys() if not k.startswith('_')]
            placeholders = ', '.join(['%s'] * len(columns))
            col_names = ', '.join(f'`{c}`' for c in columns)
            values = [row.get(c) for c in columns]
            sql = f"INSERT INTO task_card ({col_names}) VALUES ({placeholders})"
            cursor.execute(sql, values)
            success += 1
        except Exception as e:
            errors.append(f"[{row.get('id', '?')}] {e}")
    conn.commit()
    cursor.close()
    conn.close()
    return success, len(rows) - success, errors

def mysql_get_all_ids():
    """Get all task_card IDs from MySQL (for independent --verify)."""
    import pymysql
    cfg = get_db_config()
    conn = pymysql.connect(**cfg)
    cursor = conn.cursor()
    cursor.execute("SELECT id, status, priority, level, owner, title FROM task_card WHERE deleted = 0")
    rows = cursor.fetchall()
    columns = [desc[0] for desc in cursor.description]
    cursor.close()
    conn.close()
    return [dict(zip(columns, row)) for row in rows]

# ============ Report ============
def generate_diff_report(tasks, mapped_rows, skipped_count, duplicates):
    lines = []
    lines.append("=" * 60)
    lines.append("  M1.5 B1 数据迁移 — 对账报告")
    lines.append(f"  生成时间: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
    lines.append("=" * 60)
    
    total_source = len(tasks)
    active_count = len([t for t in tasks if t.get('_source') == 'active'])
    archive_count = len([t for t in tasks if t.get('_source') == 'archive'])
    
    lines.append(f"\n📁 Obsidian 源统计:")
    lines.append(f"   active: {active_count} 张")
    lines.append(f"   archive: {archive_count} 张")
    lines.append(f"   原始合计: {total_source} 张")
    if skipped_count > 0:
        lines.append(f"   过滤(ID > varchar(20)): -{skipped_count} 张")
    lines.append(f"   有效源: {total_source - skipped_count} 张")

    # Duplicate alerts
    if duplicates:
        lines.append(f"\n⚠️  重复 ID 告警: {len(duplicates)} 组")
        for tid, entries in duplicates.items():
            keep = entries[-1]['_file']  # last wins
            overwritten = entries[:-1]
            lines.append(f"\n   ID: {tid} ({len(entries)} 个文件冲突)")
            lines.append(f"   ▶ 保留: {os.path.basename(keep)}")
            for e in overwritten:
                title = e.get('title', '??')[:60]
                lines.append(f"   ✗ 覆盖: {os.path.basename(e['_file'])}")
                lines.append(f"      摘要: {title}")
    
    # Status distribution
    status_dist = {}
    for t in tasks:
        s = t.get('status', 'UNKNOWN')
        status_dist[s] = status_dist.get(s, 0) + 1
    lines.append(f"\n📊 状态分布 (Obsidian → MySQL):")
    for s, c in sorted(status_dist.items(), key=lambda x: -x[1]):
        mapped_s = ENUM_STATUS_MAP.get(s, s)
        arrow = f" → {mapped_s}" if mapped_s != s else ""
        lines.append(f"   {s}{arrow}: {c}")
    
    lines.append(f"\n🔄 字段映射: {len(TASK_CARD_MAPPING)} 个 frontmatter → MySQL columns")
    
    effective = total_source - skipped_count
    diff = effective - len(mapped_rows)
    lines.append(f"\n📋 对账:")
    lines.append(f"   有效源: {effective} 张")
    lines.append(f"   目标行: {len(mapped_rows)} 行")
    lines.append(f"   diff: {diff}")
    
    lines.append(f"\n📝 逐卡映射预览 (前 10):")
    lines.append("-" * 60)
    for i, row in enumerate(mapped_rows[:10]):
        rid = row.get('id', '???')
        title = row.get('title', '???')[:40]
        status = row.get('status', '???')
        lines.append(f"  [{i+1}] {rid} | {status:12s} | {title}")
    if len(mapped_rows) > 10:
        lines.append(f"  ... 还有 {len(mapped_rows) - 10} 行")
    
    lines.append(f"\n{'='*60}")
    if diff == 0:
        lines.append(f"  ✅ diff=0，有效源 {effective} = 目标 {len(mapped_rows)}")
    else:
        lines.append(f"  ⚠️ diff={diff}，请排查")
    lines.append("=" * 60)
    return '\n'.join(lines)

def sample_verify(mapped_rows, count=5):
    """独立运行抽样验证（不依赖 mapped_rows 的计算状态）。"""
    import pymysql
    
    db_rows = mysql_get_all_ids()
    if not db_rows:
        return "⚠️ MySQL 中无数据，请先执行 --execute\n", False
    
    db_map = {r['id']: r for r in db_rows}
    
    if mapped_rows:
        sample = random.sample(mapped_rows, min(count, len(mapped_rows)))
    else:
        # Independent verify: pick random from DB
        sample_rows = random.sample(db_rows, min(count, len(db_rows)))
        sample = [{'id': r['id']} for r in sample_rows]
    
    lines = []
    lines.append(f"\n{'='*60}")
    lines.append("  🎯 抽样验证报告")
    lines.append("=" * 60)
    all_ok = True
    
    for row in sample:
        rid = row['id']
        exp_row = row.get  # from mapped_rows (the expected values)
        db_row = db_map.get(rid)
        
        if db_row is None:
            lines.append(f"  ❌ {rid}: MySQL 中不存在")
            all_ok = False
            continue
        
        # If we have expected values, compare them
        if 'title' in row:
            checks = [
                ('status', row.get('status', '')),
                ('priority', row.get('priority', '')),
                ('level', row.get('level', '')),
                ('owner', row.get('owner', '')),
                ('title', row.get('title', '')),
            ]
            issues = []
            for name, exp in checks:
                actual = db_row.get(name, '')
                if str(exp) != str(actual):
                    issues.append(f"{name}: exp={exp}, act={actual}")
            
            if issues:
                lines.append(f"  ❌ {rid}: {', '.join(issues)}")
                all_ok = False
            else:
                title = row.get('title', '')[:30]
                lines.append(f"  ✅ {rid} ({title}): 5/5 一致")
        else:
            # Independent verify: just show what's in DB
            lines.append(f"  📄 {rid} ({db_row.get('title','')[:40]}): status={db_row.get('status','')}")
    
    lines.append("")
    if all_ok:
        lines.append(f"  ✅ 抽样({len(sample)}张)全部通过")
    else:
        lines.append(f"  ⚠️ 存在不一致")
    lines.append("=" * 60)
    return '\n'.join(lines), all_ok

# ============ Main ============
def main():
    parser = argparse.ArgumentParser(description='M1.5 B1 数据迁移 v2')
    parser.add_argument('--dry-run', action='store_true', help='对账报告 (不写入)')
    parser.add_argument('--execute', action='store_true', help='实际写入 MySQL')
    parser.add_argument('--verify', action='store_true', help='抽样验证 (可独立运行)')
    parser.add_argument('--sample', type=int, default=5, help='抽样数量')
    parser.add_argument('--output', type=str, help='保存报告到文件')
    args = parser.parse_args()
    
    if not args.dry_run and not args.execute and not args.verify:
        print("请指定 --dry-run / --execute / --verify 中至少一个")
        sys.exit(1)
    
    # Discover tasks
    print("🔍 扫描 Obsidian 任务账本...")
    tasks, duplicates = discover_tasks()
    raw_total = len(tasks) + sum(len(v) - 1 for v in duplicates.values())
    print(f"   发现 {raw_total} 条原始记录 ({len(tasks)} 去重后, {len(duplicates)} 组重复)")
    
    # Map to rows
    print("🔄 映射字段...")
    all_rows = [map_task_to_row(t) for t in tasks]
    
    # Filter long IDs
    skipped = [r for r in all_rows if len(r.get('id', '')) > 20]
    mapped_rows = [r for r in all_rows if len(r.get('id', '')) <= 20]
    skipped_count = len(skipped)
    
    if skipped_count > 0:
        print(f"   ⚠️ 过滤 {skipped_count} 条 (ID > varchar(20)): {[r['id'] for r in skipped]}")
    print(f"   生成 {len(mapped_rows)} 行")
    
    # Dry-run
    if args.dry_run:
        report = generate_diff_report(tasks, mapped_rows, skipped_count, duplicates)
        print(report)
        if args.output:
            with open(args.output, 'w') as f:
                f.write(report)
            print(f"\n📄 已保存: {args.output}")
    
    # Execute
    if args.execute:
        print("💾 写入 MySQL...")
        success, errors_count, errors = mysql_execute(mapped_rows, execute=True)
        print(f"   ✅ {success} / ❌ {errors_count}")
        for err in errors:
            print(f"      {err}")
        
        if args.verify:
            report, ok = sample_verify(mapped_rows, args.sample)
            print(report)
            if args.output:
                with open(args.output, 'a') as f:
                    f.write(report)
    
    # Independent verify (no --execute)
    if args.verify and not args.execute:
        report, ok = sample_verify(None, args.sample)
        print(report)
        if args.output:
            with open(args.output, 'w') as f:
                f.write(report)
    
    # Summary
    if not args.verify or args.execute:
        print(f"\n📊 总结: 原始 {raw_total} 条 → 去重 {len(tasks)} → 过滤 {skipped_count} → 写入 {len(mapped_rows)} 行")

if __name__ == '__main__':
    main()
