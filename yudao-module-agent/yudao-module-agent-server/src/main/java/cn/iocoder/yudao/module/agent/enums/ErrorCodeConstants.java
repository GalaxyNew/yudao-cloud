package cn.iocoder.yudao.module.agent.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Agent 模块错误码
 */
public interface ErrorCodeConstants {

    // ========== Agent 名录 1-011-000-000 ==========
    ErrorCode AGENT_NOT_EXISTS = new ErrorCode(1_011_000_000, "Agent 不存在");
    ErrorCode AGENT_AGENT_ID_DUPLICATE = new ErrorCode(1_011_000_001, "OpenClaw agent_id 已存在");
    ErrorCode AGENT_AGENT_NUM_DUPLICATE = new ErrorCode(1_011_000_002, "Agent 编号已存在");

    // ========== Agent 档案 1-011-001-000 ==========
    ErrorCode PROFILE_NOT_EXISTS = new ErrorCode(1_011_001_000, "Agent 档案不存在");

    // ========== Agent 考核 1-011-002-000 ==========
    ErrorCode REVIEW_NOT_EXISTS = new ErrorCode(1_011_002_000, "考核记录不存在");

    // ========== Agent 培训 1-011-003-000 ==========
    ErrorCode TRAINING_NOT_EXISTS = new ErrorCode(1_011_003_000, "培训记录不存在");

    // ========== Agent 模型路由 1-011-004-000 ==========
    ErrorCode MODEL_ROUTE_NOT_EXISTS = new ErrorCode(1_011_004_000, "模型路由不存在");
    ErrorCode MODEL_ROUTE_AGENT_TAG_DUPLICATE = new ErrorCode(1_011_004_001, "Agent+任务标签 路由已存在");

}
