package cn.iocoder.yudao.module.agent.api.profile;

import cn.iocoder.yudao.module.agent.api.profile.dto.AgentProfileRespDTO;

import java.util.List;

/**
 * Agent 能力档案 RPC 接口 — 供 HR 工作台、task 模块调用
 */
public interface AgentProfileApi {

    /**
     * 查能力档案
     */
    AgentProfileRespDTO getProfile(String agentId);

    /**
     * 校验 Agent 是否有资格领取带特定标签的任务
     */
    boolean canTakeTask(String agentId, List<String> tags);
}
