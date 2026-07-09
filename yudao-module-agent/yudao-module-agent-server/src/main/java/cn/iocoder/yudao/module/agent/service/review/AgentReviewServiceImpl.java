package cn.iocoder.yudao.module.agent.service.review;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.agent.controller.admin.review.vo.AgentReviewCreateReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.review.vo.AgentReviewPageReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentReviewDO;
import cn.iocoder.yudao.module.agent.dal.mysql.AgentReviewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Agent 绩效考核 Service 实现类
 */
@Service
@Validated
public class AgentReviewServiceImpl implements AgentReviewService {

    @Resource
    private AgentReviewMapper agentReviewMapper;

    @Override
    public Long createReview(AgentReviewCreateReqVO createReqVO) {
        AgentReviewDO review = new AgentReviewDO();
        review.setAgentId(createReqVO.getAgentId());
        review.setTaskId(createReqVO.getTaskId());
        review.setDimensionPass(createReqVO.getDimensionPass());
        review.setDimensionOntime(createReqVO.getDimensionOntime());
        review.setDimensionEvidence(createReqVO.getDimensionEvidence());
        review.setDimensionCompliance(createReqVO.getDimensionCompliance());
        review.setDimensionCollab(createReqVO.getDimensionCollab());
        review.setGrade(createReqVO.getGrade());
        review.setComment(createReqVO.getComment());
        agentReviewMapper.insert(review);
        return review.getId();
    }

    @Override
    public AgentReviewDO getReview(Long id) {
        return agentReviewMapper.selectById(id);
    }

    @Override
    public List<AgentReviewDO> listByAgentId(String agentId) {
        return agentReviewMapper.selectListByAgentId(agentId);
    }

    @Override
    public PageResult<AgentReviewDO> pageReview(AgentReviewPageReqVO reqVO) {
        return agentReviewMapper.selectPage(reqVO);
    }

}
