package cn.iocoder.yudao.module.agent.service.modelroute;

import cn.iocoder.yudao.module.agent.controller.admin.modelroute.vo.AgentModelRouteCreateReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.modelroute.vo.AgentModelRouteUpdateReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentModelRouteDO;

import java.util.List;

/**
 * Agent 模型路由 Service 接口
 */
public interface AgentModelRouteService {

    /**
     * 创建路由规则
     */
    Long createRoute(AgentModelRouteCreateReqVO createReqVO);

    /**
     * 更新路由规则
     */
    void updateRoute(AgentModelRouteUpdateReqVO updateReqVO);

    /**
     * 删除路由规则
     */
    void deleteRoute(Long id);

    /**
     * 按 Agent 查询路由列表
     */
    List<AgentModelRouteDO> listByAgentId(String agentId);

    /**
     * 查询推荐模型
     */
    AgentModelRouteDO routeModel(String agentId, String taskTag);

}
