package cn.iocoder.yudao.module.agent.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.agent.controller.admin.training.vo.AgentTrainingPageReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentTrainingDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Agent 培训记录 Mapper
 */
@Mapper
public interface AgentTrainingMapper extends BaseMapperX<AgentTrainingDO> {

    default PageResult<AgentTrainingDO> selectPage(AgentTrainingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AgentTrainingDO>()
                .eqIfPresent(AgentTrainingDO::getAgentId, reqVO.getAgentId())
                .eqIfPresent(AgentTrainingDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(AgentTrainingDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AgentTrainingDO::getId));
    }

    default List<AgentTrainingDO> selectListByAgentId(String agentId) {
        return selectList(AgentTrainingDO::getAgentId, agentId);
    }

}
