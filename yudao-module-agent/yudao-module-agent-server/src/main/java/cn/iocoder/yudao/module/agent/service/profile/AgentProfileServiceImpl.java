package cn.iocoder.yudao.module.agent.service.profile;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.agent.controller.admin.profile.vo.AgentProfileUpdateReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentProfileDO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentReviewDO;
import cn.iocoder.yudao.module.agent.dal.mysql.AgentProfileMapper;
import cn.iocoder.yudao.module.agent.dal.mysql.AgentReviewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.agent.enums.ErrorCodeConstants.PROFILE_NOT_EXISTS;

/**
 * Agent 能力档案 Service 实现类
 */
@Service
@Validated
public class AgentProfileServiceImpl implements AgentProfileService {

    @Resource
    private AgentProfileMapper agentProfileMapper;

    @Resource
    private AgentReviewMapper agentReviewMapper;

    @Override
    public AgentProfileDO getProfile(String agentId) {
        return agentProfileMapper.selectByAgentId(agentId);
    }

    @Override
    public void updateProfile(AgentProfileUpdateReqVO updateReqVO) {
        AgentProfileDO profile = agentProfileMapper.selectByAgentId(updateReqVO.getAgentId());
        if (profile == null) {
            throw exception(PROFILE_NOT_EXISTS);
        }
        AgentProfileDO updateObj = new AgentProfileDO();
        updateObj.setId(profile.getId());
        updateObj.setSkillTags(updateReqVO.getSkillTags());
        updateObj.setRating(updateReqVO.getRating());
        updateObj.setModelTier(updateReqVO.getModelTier());
        agentProfileMapper.updateById(updateObj);
    }

    @Override
    public void recalc(String agentId) {
        // 查询该 Agent 所有考核记录
        List<AgentReviewDO> reviews = agentReviewMapper.selectListByAgentId(agentId);

        AgentProfileDO profile = agentProfileMapper.selectByAgentId(agentId);
        if (profile == null) {
            throw exception(PROFILE_NOT_EXISTS);
        }

        AgentProfileDO updateObj = new AgentProfileDO();
        updateObj.setId(profile.getId());

        if (reviews.isEmpty()) {
            updateObj.setTotalAssigned(0);
            updateObj.setPassRateFirst(BigDecimal.ZERO);
            updateObj.setReworkRate(BigDecimal.ZERO);
            updateObj.setCurrentWip(0);
        } else {
            int totalAssigned = reviews.size();

            // 计算一次通过率（dimension_pass 平均值 / 100）
            double avgPass = reviews.stream()
                    .mapToInt(r -> r.getDimensionPass() != null ? r.getDimensionPass() : 0)
                    .average().orElse(0.0);
            BigDecimal passRateFirst = BigDecimal.valueOf(avgPass)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            // 返工率 = (1 - passRateFirst)
            BigDecimal reworkRate = BigDecimal.ONE.subtract(passRateFirst)
                    .setScale(2, RoundingMode.HALF_UP);

            updateObj.setTotalAssigned(totalAssigned);
            updateObj.setPassRateFirst(passRateFirst);
            updateObj.setReworkRate(reworkRate);
            updateObj.setCurrentWip(0);
        }

        agentProfileMapper.updateById(updateObj);
    }

    @Override
    public List<AgentProfileDO> recommend(String skillTags, Integer limit) {
        List<String> tagList = Arrays.stream(skillTags.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        return agentProfileMapper.selectList(new LambdaQueryWrapperX<AgentProfileDO>()).stream()
                .filter(p -> {
                    if (p.getSkillTags() == null) return false;
                    List<String> pTags = Arrays.stream(p.getSkillTags().split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    return pTags.stream().anyMatch(tagList::contains);
                })
                .sorted((a, b) -> {
                    // WIP 越低越优先
                    int wipCompare = Integer.compare(
                            a.getCurrentWip() != null ? a.getCurrentWip() : 0,
                            b.getCurrentWip() != null ? b.getCurrentWip() : 0);
                    if (wipCompare != 0) return wipCompare;
                    // 评级排序 S > A > B > C > D
                    return getRatingOrder(a.getRating()) - getRatingOrder(b.getRating());
                })
                .limit(limit != null ? limit : 5)
                .collect(Collectors.toList());
    }

    private int getRatingOrder(String rating) {
        return switch (rating != null ? rating : "D") {
            case "S" -> 0;
            case "A" -> 1;
            case "B" -> 2;
            case "C" -> 3;
            default -> 4;
        };
    }

}
