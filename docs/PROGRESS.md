# yudao-cloud 单体部署进度

> 最后更新：2026-07-09 10:45 CST
> 任务卡：T20260709-07-M0A-Mac底座
> 分支：master-jdk25

## 环境版本

| 组件 | 版本 | 来源 |
|------|------|------|
| JDK | Temurin 25.0.3+9 | 手动安装 ~/dev/java/jdk-25.0.3+9/Contents/Home |
| MySQL | 9.7.1 | Homebrew |
| Redis | 8.8.0 | Homebrew |
| Maven | 3.9.16 | Homebrew |
| Node | v22.22.3 | Homebrew |
| pnpm | 11.7.0 | npm global |
| Spring Boot | 4.1.0 | yudao-cloud parent |
| 数据库 | ruoyi-vue-pro (utf8mb4) | 61 张表 |

## JDK25 兼容修复清单

| # | 修复项 | 类型 | 方式 |
|---|--------|------|------|
| 1 | WxMa/WxMp AutoConfiguration | NoClassDefFoundError (Apache HttpClient TrustStrategy) | application-local.yaml exclude |
| 2 | 5 个 @EnableFeignClients RpcAutoConfiguration | FeignClient 代理缺失 | exclude |
| 3 | 7 个 FeignClient CommonApi 接口 | 单体无 RPC | YudaoMonolithRpcConfiguration fallback bean |
| 4 | WxMpService/WxMaService 等 4 个 bean | YudaoWxClientConfiguration 依赖 | mock bean |
| 5 | Tenant Security Filter | 拦截所有请求 | yudao.tenant.ignore-urls: /admin-api/** |
| 6 | spring-boot-maven-plugin repackage | BOOT-INF 双重嵌套 | 删除 system/infra-server pom 中的 repackage goal |
| 7 | Security permit-all-urls | 登录接口 401 | /system/auth/**, /infra/** |

### 关键根因：repackage 双重嵌套
`yudao-module-system-server` 和 `yudao-module-infra-server` 的 `pom.xml` 中使用了 `spring-boot-maven-plugin` 的 `repackage` goal，导致类路径为 `BOOT-INF/classes/cn/iocoder/yudao/module/...`。
当 yudao-server 的 fat jar 再将这两个 jar 嵌套到 `BOOT-INF/lib/` 时，Spring Boot 4.x 的 `LaunchedURLClassLoader` 无法看到嵌套 jar 内的 `BOOT-INF/classes/` 下的类，导致 Component Scan 扫出 0 个业务 Bean（仅 21 框架 mappings）。

## 修改文件清单

### framework 层（yudao-cloud 仓库）
- `yudao-module-system/yudao-module-system-server/pom.xml` — 删除 spring-boot-maven-plugin repackage
- `yudao-module-infra/yudao-module-infra-server/pom.xml` — 删除 spring-boot-maven-plugin repackage

### yudao-server 层
- `yudao-server/src/main/resources/application-local.yaml` — exclude + tenant + security 配置
- `yudao-server/src/main/java/cn/iocoder/yudao/server/config/YudaoMonolithRpcConfiguration.java` — 7 CommonApi + 4 Wx mock beans

## 验证命令

```bash
# 编译
cd ~/dev/yudao-cloud
export JAVA_HOME=~/dev/java/jdk-25.0.3+9/Contents/Home
mvn clean package -pl yudao-server -am -DskipTests -T 4

# 启动
java -jar yudao-server/target/yudao-server.jar --spring.profiles.active=local

# 登录验证
curl -X POST http://localhost:48080/admin-api/system/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"username":"admin","password":"***"}'
# → {"code":0,"data":{"userId":1,"accessToken":"...","refreshToken":"...","expiresTime":...}}

# 权限验证
curl -X GET http://localhost:48080/admin-api/system/auth/get-permission-info \
  -H "Authorization: Bearer ${TOKEN}"
# → {"code":0,"data":{"user":{"username":"admin","nickname":"芋道源码"},...}}

# Swagger UI
open http://localhost:48080/admin-api/swagger-ui/index.html
```

## M1.2 yudao-module-task 开发 — 任务卡模块

- **日期**: 2026-07-09
- **状态**: 编译通过，DDL 执行完成
- **任务卡**: T20260709-16

### 已完成
- [x] DDL: task_card + task_evidence 建表成功（ruoyi-vue-pro 库）
- [x] Maven 双子模块骨架: yudao-module-task-api + yudao-module-task-server
- [x] 枚举: TaskStatusEnum, TaskPriorityEnum, TaskLevelEnum, EvidenceTypeEnum
- [x] RPC 接口: TaskCardApi (getCard, getCardListByIds, validateCard), TaskEvidenceApi (listByTaskId)
- [x] DTO: TaskCardRespDTO, TaskEvidenceRespDTO
- [x] DAL: TaskCardDO, TaskEvidenceDO, TaskCardMapper, TaskEvidenceMapper
- [x] Service: TaskCardServiceImpl (CRUD + 状态机 + 门禁 + 派单/领单), TaskEvidenceServiceImpl
- [x] 状态机: TaskStateMachine (10 态流转，14 条合法转换)
- [x] 门禁: 5 条规则（github_issue, doc_ref, process_stage, owner, evidence）
- [x] Controller: TaskCardController (12 个端点), TaskEvidenceController (3 个端点)
- [x] yudao-server pom 依赖添加
- [x] `mvn clean package -pl yudao-server -am -DskipTests -T 4` BUILD SUCCESS

### 文件结构
```
yudao-module-task/
├── pom.xml (parent, packaging=pom)
├── yudao-module-task-api/
│   └── src/.../task/
│       ├── api/ (TaskCardApi, TaskEvidenceApi)
│       │   ├── card/dto/TaskCardRespDTO
│       │   └── evidence/dto/TaskEvidenceRespDTO
│       └── enums/ (4 enums)
└── yudao-module-task-server/
    └── src/.../task/
        ├── api/impl/ (TaskCardApiImpl, TaskEvidenceApiImpl)
        ├── controller/admin/card/ (TaskCardController + 7 VOs)
        ├── controller/admin/evidence/ (TaskEvidenceController + 2 VOs)
        ├── dal/dataobject/ (TaskCardDO, TaskEvidenceDO)
        ├── dal/mysql/ (TaskCardMapper, TaskEvidenceMapper)
        ├── enums/ErrorCodeConstants
        └── service/card/ (TaskCardService, TaskCardServiceImpl, TaskStateMachine)
            service/evidence/ (TaskEvidenceService, TaskEvidenceServiceImpl)
```

### 待验证
- [ ] 启动 yudao-server，验证 Swagger UI 可见 task 模块端点
- [ ] POST /admin-api/task/card/create 返回成功
- [ ] 门禁校验测试（缺 github_issue → code!=0）

---

## M0.C frp 公网入口 — 方案变更

- **日期**: 2026-07-09
- **状态**: CANCELLED → 方案变更
- **原方案**: frpc 隧道（Mac → 广州 frps → Caddy HTTPS → ga.775767.xyz）
- **新方案**: Cloudflare Tunnel（用户决策替代 frp）
- **已完成**: frpc 安装/配置/SSH 隧道双代理/广州本地验证通过（API code=0 + vben HTML）
- **清理**: LaunchAgent + frpc 二进制 + 配置全部移除
- **根因**: Shadowrocket utun5 拦截 frp mTLS 握手 + Cloudflare DNS 代理冲突
- **经验**: SSH 隧道可绕过本地代理干扰进行 frp 通信
