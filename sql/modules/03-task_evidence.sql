-- =====================================================
-- M1.2 yudao-module-task — task_evidence 证据表 DDL
-- =====================================================
CREATE TABLE IF NOT EXISTS `task_evidence` (
  `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `task_id`     varchar(20)  NOT NULL COMMENT '关联任务卡 ID',
  `type`        varchar(20)  NOT NULL COMMENT '证据类型：URL/COMMAND/FILE_PATH/LOG/SCREENSHOT',
  `url`         varchar(500)          COMMENT 'URL（type=URL 时使用）',
  `file_path`   varchar(500)          COMMENT '文件路径（type=FILE_PATH 时使用）',
  `command`     text                  COMMENT '命令文本（type=COMMAND 时使用）',
  `output`      text                  COMMENT '命令输出 / 日志内容',
  `summary`     varchar(500)          COMMENT '证据摘要',
  `seq`         int          NOT NULL DEFAULT 0 COMMENT '同任务内排序号',
  `creator`     varchar(50)  NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否删除',
  `tenant_id`   bigint       NOT NULL DEFAULT 0 COMMENT '租户 ID',
  PRIMARY KEY (`id`),
  KEY `idx_task` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='任务证据表';
