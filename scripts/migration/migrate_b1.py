#!/usr/bin/env python3
"""
M1.5 B1 数据迁移脚本：Obsidian agent-vault → yudao MySQL
支持 --dry-run（对账报告）+ --execute（实际写入）
"""
import os, sys, json, re, argparse, random, glob
from datetime import datetime

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
    tasks = []
    active_dir = os.path.join(TASK_DIR, "active")
    if os.path.isdir(active_dir):
        for f in sorted(glob.glob(os.path.join(active_dir, "*.md"))):
            fm, body = parse_frontmatter(f)
            fm['_file'] = f
            fm['_body'] = body
            fm['_source'] = 'active'
            tasks.append(fm)
    archive_base = os.path.join(TASK_DIR, "archive")
    if os.path.isdir(archive_base):
        for month_dir in sorted(glob.glob(os.path.join(archive_base, "*"))):
            if os.path.isdir(month_dir):
                for f in sorted(glob.glob(os.path.join(month_dir, "*.md"))):
                    fm, body = parse_frontmatter(f)
                    fm['_file'] = f
                    fm['_body'] = body
                    fm['_source'] = 'archive'
                    tasks.append(fm)
    return tasks

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

def mysql_verify(expected_ids):
    import pymysql
    cfg = get_db_config()
    conn = pymysql.connect(**cfg)
    cursor = conn.cursor()
    cursor.execute("SELECT id FROM task_card WHERE deleted = 0")
    db_ids = set(row[0] for row in cursor.fetchall())
    expected = set(expected_ids)
    missing = expected - db_ids
    extra = db_ids - expected
    cursor.close()
    conn.close()
    return len(expected), len(db_ids), missing, extra

# ============ Report ============
def generate_diff_report(tasks, mapped_rows):
    lines = []
    lines.append("=" * 60)
    lines.append("  M1.5 B1 数据迁移 — 对账报告")
    lines.append(f"  生成时间: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
    lines.append("=" * 60)
    active_count = len([t for t in tasks if t.get('_source') == 'active'])
    archive_count = len([t for t in tasks if t.get('_source') == 'archive'])
    lines.append(f"\n📁 Obsidian 源: active={active_count} + archive={archive_count} = {len(tasks)} 张")
    status_dist = {}
    for t in tasks:
        s = t.get('status', 'UNKNOWN')
        status_dist[s] = status_dist.get(s, 0) + 1
    lines.append(f"\n📊 状态分布:")
    for s, c in sorted(status_dist.items(), key=lambda x: -x[1]):
        mapped_s = ENUM_STATUS_MAP.get(s, s)
        arrow = f" → {mapped_s}" if mapped_s != s else ""
        lines.append(f"   {s}{arrow}: {c}")
    lines.append(f"\n🔄 字段映射: {len(TASK_CARD_MAPPING)} 个 frontmatter → MySQL columns")
    total_source = len(tasks) - len(skipped) if skipped else len(tasks)
    diff = total_source - len(mapped_rows)
    lines.append(f"\n📋 MySQL 写入行数: {len(mapped_rows)} (diff={diff})")
    lines.append("\n📝 逐卡预览 (前 10):")
    lines.append("-" * 60)
    for i, row in enumerate(mapped_rows[:10]):
        rid = row.get('id', '???')
        title = row.get('title', '???')[:40]
        status = row.get('status', '???')
        lines.append(f"  [{i+1}] {rid} | {status:12s} | {title}")
    if len(mapped_rows) > 10:
        lines.append(f"  ... 还有 {len(mapped_rows) - 10} 张")
    lines.append(f"\n{'='*60}")
    if diff == 0:
        lines.append(f"  ✅ Obsidian {len(tasks)} = MySQL {len(mapped_rows)} (diff=0)")
    else:
        lines.append(f"  ⚠️ diff={diff}，请排查")
    lines.append("=" * 60)
    return '\n'.join(lines)

def sample_verify(mapped_rows, count=5):
    import pymysql
    sample = random.sample(mapped_rows, min(count, len(mapped_rows)))
    cfg = get_db_config()
    conn = pymysql.connect(**cfg)
    cursor = conn.cursor()
    lines = []
    lines.append(f"\n{'='*60}")
    lines.append("  🎯 抽样验证报告")
    lines.append("=" * 60)
    all_ok = True
    for row in sample:
        rid = row['id']
        cursor.execute("SELECT id, status, priority, level, owner, title FROM task_card WHERE id = %s", (rid,))
        db_row = cursor.fetchone()
        if db_row is None:
            lines.append(f"  ❌ {rid}: 不存在")
            all_ok = False
            continue
        db_id, db_status, db_priority, db_level, db_owner, db_title = db_row
        checks = [
            (row.get('status',''), db_status, 'status'),
            (row.get('priority',''), db_priority, 'priority'),
            (row.get('level',''), db_level, 'level'),
            (row.get('owner',''), db_owner, 'owner'),
            (row.get('title',''), db_title, 'title'),
        ]
        issues = [f"{name}: exp={exp}, act={act}" for exp, act, name in checks if str(exp) != str(act)]
        if issues:
            lines.append(f"  ❌ {rid}: {', '.join(issues)}")
            all_ok = False
        else:
            lines.append(f"  ✅ {rid} ({row.get('title','')[:30]}): 5/5 一致")
    cursor.close()
    conn.close()
    if all_ok:
        lines.append(f"\n  ✅ 抽样({len(sample)}张)全部通过")
    else:
        lines.append(f"\n  ⚠️ 存在不一致")
    lines.append("=" * 60)
    return '\n'.join(lines), all_ok

# ============ Main ============
def main():
    parser = argparse.ArgumentParser(description='M1.5 B1 Migration')
    parser.add_argument('--dry-run', action='store_true')
    parser.add_argument('--execute', action='store_true')
    parser.add_argument('--verify', action='store_true')
    parser.add_argument('--sample', type=int, default=5)
    parser.add_argument('--output', type=str)
    args = parser.parse_args()
    if not args.dry_run and not args.execute:
        print("请指定 --dry-run 或 --execute")
        sys.exit(1)
    print("🔍 扫描 Obsidian 任务账本...")
    tasks = discover_tasks()
    print(f"   发现 {len(tasks)} 张任务卡")
    print("🔄 映射字段...")
    mapped_rows_all = [map_task_to_row(t) for t in tasks]
    # Filter: skip IDs longer than 20 chars (task_card.id is varchar(20))
    skipped = [r for r in mapped_rows_all if len(r.get('id','')) > 20]
    mapped_rows = [r for r in mapped_rows_all if len(r.get('id','')) <= 20]
    if skipped:
        print(f"   ⚠️ 跳过 {len(skipped)} 行(ID > 20 chars): {[r['id'] for r in skipped]}")
    print(f"   生成 {len(mapped_rows)} 行")
    if args.dry_run:
        report = generate_diff_report(tasks, mapped_rows)
        print(report)
        if args.output:
            with open(args.output, 'w') as f:
                f.write(report)
            print(f"\n📄 已保存: {args.output}")
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
    print(f"\n📊 总结: Obsidian {len(tasks)} 卡 → 有效 {len(mapped_rows)} 行 (跳过 {len(skipped) if skipped else 0} 长ID)")

if __name__ == '__main__':
    main()
