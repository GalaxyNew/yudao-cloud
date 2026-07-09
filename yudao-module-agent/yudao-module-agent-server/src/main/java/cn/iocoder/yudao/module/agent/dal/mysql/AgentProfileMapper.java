package cn.iocoder.yudao.module.agent.dal.mysql;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentProfileDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Agent 能力档案 Mapper
 */
@Mapper
public interface AgentProfileMapper extends BaseMapperX<AgentProfileDO> {

    default AgentProfileDO selectByAgentId(String agentId) {
        return selectOne(AgentProfileDO::getAgentId, agentId);
    }

}
