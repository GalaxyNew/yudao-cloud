package cn.iocoder.yudao.module.agent.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.agent.controller.admin.info.vo.AgentInfoPageReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Agent 名录 Mapper
 */
@Mapper
public interface AgentInfoMapper extends BaseMapperX<AgentInfoDO> {

    default AgentInfoDO selectByAgentId(String agentId) {
        return selectOne(AgentInfoDO::getAgentId, agentId);
    }

    default PageResult<AgentInfoDO> selectPage(AgentInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AgentInfoDO>()
                .likeIfPresent(AgentInfoDO::getName, reqVO.getName())
                .eqIfPresent(AgentInfoDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AgentInfoDO::getAgentId, reqVO.getAgentId())
                .betweenIfPresent(AgentInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AgentInfoDO::getId));
    }

}
