package cn.iocoder.yudao.module.task.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Task 模块错误码
 */
public interface ErrorCodeConstants {

    // ========== 任务卡 1-010-000-000 ==========
    ErrorCode CARD_NOT_EXISTS = new ErrorCode(1_010_000_000, "任务卡不存在");
    ErrorCode CARD_STATUS_NOT_ALLOW_UPDATE = new ErrorCode(1_010_000_001, "当前状态不允许更新任务卡");
    ErrorCode CARD_STATUS_NOT_ALLOW_DELETE = new ErrorCode(1_010_000_002, "当前状态不允许删除任务卡");
    ErrorCode CARD_STATUS_NOT_ALLOW_ASSIGN = new ErrorCode(1_010_000_003, "当前状态不允许派单");
    ErrorCode CARD_STATUS_NOT_ALLOW_CLAIM = new ErrorCode(1_010_000_004, "当前状态不允许领单");
    ErrorCode CARD_STATUS_NOT_ALLOW_REVIEW = new ErrorCode(1_010_000_005, "当前状态不允许提交验收");
    ErrorCode CARD_STATUS_NOT_ALLOW_RETRY = new ErrorCode(1_010_000_006, "当前状态不允许重试");
    ErrorCode CARD_GATE_CHECK_FAILED = new ErrorCode(1_010_000_007, "门禁校验失败：{}");

    // ========== 任务证据 1-010-001-000 ==========
    ErrorCode EVIDENCE_TASK_NOT_EXISTS = new ErrorCode(1_010_001_000, "关联任务卡不存在");

}
