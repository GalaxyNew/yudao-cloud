# PROGRESS — yudao 业务中台

> 最后更新：2026-07-09 13:04 CST

---

## 当前里程碑：M1 管理地基（task + pm + agent）

### M0 底座 ✅ PASSED
| 卡 | 内容 | 状态 | 完成日期 |
|---|---|---|---|
| T-07 (M0.A) | Mac 底座：JDK25+MySQL8+Redis+两仓落地+单体跑通 | ✅ PASSED | 2026-07-09 |
| T-11 (M0.B) | AI 基座+codegen 演示 | ✅ PASSED | 2026-07-09 |
| T-08 (P0.4) | 首批培训：yudao 开发技能包 | 🔄 IN_PROGRESS（阶段1-2完成，结业实测待 M0.B） | 2026-07-09 |
| T-09 | UI 培训：vben-ui-design 技能包 | 🔄 IN_PROGRESS（阶段1-2完成，结业实测 S级 94/100） | 2026-07-09 |

### M1 管理地基（进行中）
| 卡 | 内容 | 状态 | 备注 |
|---|---|---|---|
| T-15 (M1.1) | 架构设计：02-架构设计.md + 总纲入仓 + PROGRESS.md | ✅ PASSED | 45KB架构文档+13表DDL+73端点+状态机 |
| M1.2 | yudao-module-task：任务卡+状态机+门禁+派单 API | ⏳ 待派单 | 前置 M1.1 |
| M1.3 | yudao-module-pm：项目登记册+里程碑+文档+决策 | ⏳ 待派单 | 前置 M1.1 |
| M1.4 | yudao-module-agent：名录+能力档案+考核+培训 | ⏳ 待派单 | 前置 M1.1 |
| M1.5 | B1 迁移：Obsidian 任务账本→数据库 | ⏳ 待派单 | 前置 M1.2-4 |
| M1.6 | Hermes 切轨：pulse_gate 改轮询 task API | ⏳ 待派单 | 前置 M1.5 |
| M1.7 | MCP 双向桥 v1 | ⏳ 待派单 | 前置 M1.2 |
| M1.8 | 验收演练：公司化全闭环 E2E | ⏳ 待派单 | 前置 M1.6 |

---

## 后续里程碑

- **M2 运维台账**：ops 模块 + B2 迁移 + 执行节点 + 反向备份
- **M3 SEO 重生**：collect + seo 模块 + 历史数据迁移 + 发布审批流
- **M4 增强**：GitHub 同步 / 外链 / 内容库 / AI 工作流 / 闲鱼评估

---

## 技术栈基线

| 维度 | 版本/说明 |
|---|---|
| JDK | 25 (Temurin) |
| Spring Boot | 4.1 |
| Spring AI | 2.0 |
| MySQL | 8.x |
| Redis | 7.x |
| 前端 | Vben Admin 5.x (Vue 3 + TypeScript) |
| 仓库 | github.com/GalaxyNew/yudao-cloud (main 分支) |
| 部署模式 | 单体模式（yudao-server 入口） |
