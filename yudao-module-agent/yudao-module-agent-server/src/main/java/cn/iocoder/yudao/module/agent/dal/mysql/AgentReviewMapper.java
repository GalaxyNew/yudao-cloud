package cn.iocoder.yudao.module.agent.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.agent.controller.admin.review.vo.AgentReviewPageReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentReviewDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Agent 绩效考核 Mapper
 */
@Mapper
public interface AgentReviewMapper extends BaseMapperX<AgentReviewDO> {

    default PageResult<AgentReviewDO> selectPage(AgentReviewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AgentReviewDO>()
                .eqIfPresent(AgentReviewDO::getAgentId, reqVO.getAgentId())
                .eqIfPresent(AgentReviewDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(AgentReviewDO::getGrade, reqVO.getGrade())
                .betweenIfPresent(AgentReviewDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AgentReviewDO::getId));
    }

    default List<AgentReviewDO> selectListByAgentId(String agentId) {
        return selectList(AgentReviewDO::getAgentId, agentId);
    }

}
