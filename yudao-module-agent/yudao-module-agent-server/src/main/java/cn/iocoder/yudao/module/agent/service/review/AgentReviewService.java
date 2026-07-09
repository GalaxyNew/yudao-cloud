package cn.iocoder.yudao.module.agent.service.review;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.agent.controller.admin.review.vo.AgentReviewCreateReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.review.vo.AgentReviewPageReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentReviewDO;

import java.util.List;

/**
 * Agent 绩效考核 Service 接口
 */
public interface AgentReviewService {

    /**
     * 创建考核记录
     */
    Long createReview(AgentReviewCreateReqVO createReqVO);

    /**
     * 获取考核记录
     */
    AgentReviewDO getReview(Long id);

    /**
     * 按 Agent 查询考核列表
     */
    List<AgentReviewDO> listByAgentId(String agentId);

    /**
     * 分页查询
     */
    PageResult<AgentReviewDO> pageReview(AgentReviewPageReqVO reqVO);

}
