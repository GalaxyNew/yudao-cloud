package cn.iocoder.yudao.module.agent.api;

import cn.iocoder.yudao.module.agent.api.modelroute.AgentModelRouteApi;
import cn.iocoder.yudao.module.agent.api.modelroute.dto.AgentModelRouteRespDTO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentModelRouteDO;
import cn.iocoder.yudao.module.agent.service.modelroute.AgentModelRouteService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * AgentModelRouteApi 本地实现（单体模式）
 */
@Component
public class AgentModelRouteApiImpl implements AgentModelRouteApi {

    @Resource
    private AgentModelRouteService agentModelRouteService;

    @Override
    public AgentModelRouteRespDTO routeModel(String agentId, String taskTag) {
        AgentModelRouteDO route = agentModelRouteService.routeModel(agentId, taskTag);
        if (route == null) {
            return null;
        }
        AgentModelRouteRespDTO dto = new AgentModelRouteRespDTO();
        dto.setAgentId(route.getAgentId());
        dto.setTaskTag(route.getTaskTag());
        dto.setModel(route.getModel());
        dto.setTier(route.getTier());
        dto.setPriority(route.getPriority());
        return dto;
    }

}
