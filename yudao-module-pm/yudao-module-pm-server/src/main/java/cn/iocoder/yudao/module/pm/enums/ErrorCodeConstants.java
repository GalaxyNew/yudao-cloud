package cn.iocoder.yudao.module.pm.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * PM 模块错误码
 */
public interface ErrorCodeConstants {

    // ========== 项目 1-020-000-000 ==========
    ErrorCode PROJECT_NOT_EXISTS = new ErrorCode(1_020_000_000, "项目不存在");
    ErrorCode PROJECT_CODE_EXISTS = new ErrorCode(1_020_000_001, "项目编号已存在");

    // ========== 里程碑 1-020-001-000 ==========
    ErrorCode MILESTONE_NOT_EXISTS = new ErrorCode(1_020_001_000, "里程碑不存在");

    // ========== 文档 1-020-002-000 ==========
    ErrorCode DOCUMENT_NOT_EXISTS = new ErrorCode(1_020_002_000, "文档不存在");

    // ========== 决策 1-020-003-000 ==========
    ErrorCode DECISION_NOT_EXISTS = new ErrorCode(1_020_003_000, "决策不存在");
    ErrorCode DECISION_CODE_EXISTS = new ErrorCode(1_020_003_001, "决策编号已存在");
    ErrorCode DECISION_NOT_ALLOW_DECIDE = new ErrorCode(1_020_003_002, "当前状态不允许决策");

    // ========== 验收 1-020-004-000 ==========
    ErrorCode ACCEPTANCE_NOT_EXISTS = new ErrorCode(1_020_004_000, "验收记录不存在");
    ErrorCode ACCEPTANCE_TASK_EXISTS = new ErrorCode(1_020_004_001, "该任务已有验收记录");

    // ========== 经验库 1-020-005-000 ==========
    ErrorCode LESSON_NOT_EXISTS = new ErrorCode(1_020_005_000, "经验卡不存在");

}
