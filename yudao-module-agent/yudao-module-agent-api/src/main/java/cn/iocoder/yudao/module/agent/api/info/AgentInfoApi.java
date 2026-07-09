package cn.iocoder.yudao.module.agent.api.info;

import cn.iocoder.yudao.module.agent.api.info.dto.AgentInfoRespDTO;

import java.util.List;

/**
 * Agent 名录 RPC 接口 — 供 task 等模块跨模块调用
 */
public interface AgentInfoApi {

    /**
     * 按 OpenClaw agent_id 查 Agent
     */
    AgentInfoRespDTO getAgentByAgentId(String agentId);

    /**
     * 批量查询 Agent
     */
    List<AgentInfoRespDTO> getAgentListByIds(List<Long> ids);
}
