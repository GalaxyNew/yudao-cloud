package cn.iocoder.yudao.module.agent.service.modelroute;

import cn.iocoder.yudao.module.agent.controller.admin.modelroute.vo.AgentModelRouteCreateReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.modelroute.vo.AgentModelRouteUpdateReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentModelRouteDO;
import cn.iocoder.yudao.module.agent.dal.mysql.AgentModelRouteMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.agent.enums.ErrorCodeConstants.*;

/**
 * Agent 模型路由 Service 实现类
 */
@Service
@Validated
public class AgentModelRouteServiceImpl implements AgentModelRouteService {

    @Resource
    private AgentModelRouteMapper agentModelRouteMapper;

    @Override
    public Long createRoute(AgentModelRouteCreateReqVO createReqVO) {
        // 校验 agent+tag 唯一
        AgentModelRouteDO existing = agentModelRouteMapper.selectByAgentIdAndTaskTag(
                createReqVO.getAgentId(), createReqVO.getTaskTag());
        if (existing != null) {
            throw exception(MODEL_ROUTE_AGENT_TAG_DUPLICATE);
        }
        AgentModelRouteDO route = new AgentModelRouteDO();
        route.setAgentId(createReqVO.getAgentId());
        route.setTaskTag(createReqVO.getTaskTag());
        route.setModel(createReqVO.getModel());
        route.setTier(createReqVO.getTier());
        route.setPriority(createReqVO.getPriority() != null ? createReqVO.getPriority() : 0);
        agentModelRouteMapper.insert(route);
        return route.getId();
    }

    @Override
    public void updateRoute(AgentModelRouteUpdateReqVO updateReqVO) {
        AgentModelRouteDO exist = agentModelRouteMapper.selectById(updateReqVO.getId());
        if (exist == null) {
            throw exception(MODEL_ROUTE_NOT_EXISTS);
        }
        AgentModelRouteDO updateObj = new AgentModelRouteDO();
        updateObj.setId(updateReqVO.getId());
        updateObj.setTaskTag(updateReqVO.getTaskTag());
        updateObj.setModel(updateReqVO.getModel());
        updateObj.setTier(updateReqVO.getTier());
        updateObj.setPriority(updateReqVO.getPriority());
        agentModelRouteMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoute(Long id) {
        AgentModelRouteDO exist = agentModelRouteMapper.selectById(id);
        if (exist == null) {
            throw exception(MODEL_ROUTE_NOT_EXISTS);
        }
        agentModelRouteMapper.deleteById(id);
    }

    @Override
    public List<AgentModelRouteDO> listByAgentId(String agentId) {
        return agentModelRouteMapper.selectListByAgentId(agentId);
    }

    @Override
    public AgentModelRouteDO routeModel(String agentId, String taskTag) {
        return agentModelRouteMapper.selectByAgentIdAndTaskTag(agentId, taskTag);
    }

}
