package cn.iocoder.yudao.module.agent.api;

import cn.iocoder.yudao.module.agent.api.profile.AgentProfileApi;
import cn.iocoder.yudao.module.agent.api.profile.dto.AgentProfileRespDTO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentProfileDO;
import cn.iocoder.yudao.module.agent.service.profile.AgentProfileService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * AgentProfileApi 本地实现（单体模式）
 */
@Component
public class AgentProfileApiImpl implements AgentProfileApi {

    @Resource
    private AgentProfileService agentProfileService;

    @Override
    public AgentProfileRespDTO getProfile(String agentId) {
        AgentProfileDO profile = agentProfileService.getProfile(agentId);
        return convert(profile);
    }

    @Override
    public boolean canTakeTask(String agentId, List<String> tags) {
        AgentProfileDO profile = agentProfileService.getProfile(agentId);
        if (profile == null) {
            return false;
        }
        // WIP 限制：当前进行中任务数不能过多（默认限制 10）
        if (profile.getCurrentWip() != null && profile.getCurrentWip() >= 10) {
            return false;
        }
        // 技能匹配：profile 的 skill_tags 需包含至少一个请求标签
        if (profile.getSkillTags() == null || profile.getSkillTags().isEmpty()) {
            return false;
        }
        List<String> pTags = List.of(profile.getSkillTags().split(","));
        return tags.stream().anyMatch(pTags::contains);
    }

    private AgentProfileRespDTO convert(AgentProfileDO profile) {
        if (profile == null) {
            return null;
        }
        AgentProfileRespDTO dto = new AgentProfileRespDTO();
        dto.setAgentId(profile.getAgentId());
        dto.setSkillTags(profile.getSkillTags());
        dto.setTotalAssigned(profile.getTotalAssigned());
        dto.setPassRateFirst(profile.getPassRateFirst());
        dto.setReworkRate(profile.getReworkRate());
        dto.setCurrentWip(profile.getCurrentWip());
        dto.setRating(profile.getRating());
        dto.setModelTier(profile.getModelTier());
        return dto;
    }

}
