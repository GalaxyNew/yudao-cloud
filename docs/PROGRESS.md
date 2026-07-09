# yudao-cloud 开发进度

| 里程碑 | 内容 | 状态 | 完成时间 |
|--------|------|------|----------|
| M0.A | JDK25 + MySQL8 + Redis 底座 | ✅ PASSED | 2026-07-09 |
| M0.B | AI 基座 + HR 17角色 + codegen 评估 | ✅ PASSED | 2026-07-09 |
| M0.C | 公网入口 fg.775767.xyz + fg-api.775767.xyz | ✅ PASSED | 2026-07-09 |
| M0.5 | codegen JDK25 兼容性 | ❌ BLOCKED | 等 mybatis-plus-generator |
| M1.1 | 架构设计 13表 + 73端点 + 状态机 | ✅ PASSED | 2026-07-09 |
| M1.2 | yudao-module-task 任务卡模块 | 🔧 IN_PROGRESS | server 端完成 |
| M1.3 | yudao-module-pm 项目管理模块 | ⏳ PENDING | |
| M1.4 | yudao-module-agent 智能体模块 | ⏳ PENDING | |

## M1.2 当前状态
- 29 Java files, 15 REST endpoints, 2 DB tables (task_card + task_evidence)
- 状态机：DRAFT→STAFFING→ASSIGNED→IN_PROGRESS→REVIEW→PASSED/REJECTED/BLOCKED/CANCELLED
- 门禁：5 项校验（githubIssue/docRef/processStage/owner/evidence）
- API 验证全部通过 ✅
- DDL 文件已补到 sql/modules/03-*.sql
- 前端：等 UI 设计稿
