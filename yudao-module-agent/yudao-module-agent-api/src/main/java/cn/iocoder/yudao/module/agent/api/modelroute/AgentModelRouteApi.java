package cn.iocoder.yudao.module.agent.api.modelroute;

import cn.iocoder.yudao.module.agent.api.modelroute.dto.AgentModelRouteRespDTO;

/**
 * Agent 模型路由 RPC 接口 — 供 task 模块下发时用
 */
public interface AgentModelRouteApi {

    /**
     * 查询推荐模型
     */
    AgentModelRouteRespDTO routeModel(String agentId, String taskTag);
}
