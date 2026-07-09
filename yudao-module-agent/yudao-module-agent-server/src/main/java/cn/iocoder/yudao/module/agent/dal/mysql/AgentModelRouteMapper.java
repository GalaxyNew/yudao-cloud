package cn.iocoder.yudao.module.agent.dal.mysql;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentModelRouteDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Agent 模型路由 Mapper
 */
@Mapper
public interface AgentModelRouteMapper extends BaseMapperX<AgentModelRouteDO> {

    default AgentModelRouteDO selectByAgentIdAndTaskTag(String agentId, String taskTag) {
        return selectOne(new LambdaQueryWrapperX<AgentModelRouteDO>()
                .eq(AgentModelRouteDO::getAgentId, agentId)
                .eq(AgentModelRouteDO::getTaskTag, taskTag)
                .orderByDesc(AgentModelRouteDO::getPriority)
                .last("LIMIT 1"));
    }

    default List<AgentModelRouteDO> selectListByAgentId(String agentId) {
        return selectList(new LambdaQueryWrapperX<AgentModelRouteDO>()
                .eq(AgentModelRouteDO::getAgentId, agentId)
                .orderByDesc(AgentModelRouteDO::getPriority));
    }

}
