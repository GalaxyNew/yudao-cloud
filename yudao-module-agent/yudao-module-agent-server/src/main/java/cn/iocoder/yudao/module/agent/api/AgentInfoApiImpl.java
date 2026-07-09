package cn.iocoder.yudao.module.agent.api;

import cn.iocoder.yudao.module.agent.api.info.AgentInfoApi;
import cn.iocoder.yudao.module.agent.api.info.dto.AgentInfoRespDTO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentInfoDO;
import cn.iocoder.yudao.module.agent.service.info.AgentInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * AgentInfoApi 本地实现（单体模式）
 */
@Component
public class AgentInfoApiImpl implements AgentInfoApi {

    @Resource
    private AgentInfoService agentInfoService;

    @Override
    public AgentInfoRespDTO getAgentByAgentId(String agentId) {
        AgentInfoDO agent = agentInfoService.getAgentByAgentId(agentId);
        return convert(agent);
    }

    @Override
    public List<AgentInfoRespDTO> getAgentListByIds(List<Long> ids) {
        return ids.stream()
                .map(agentInfoService::getAgent)
                .filter(java.util.Objects::nonNull)
                .map(this::convert)
                .collect(java.util.stream.Collectors.toList());
    }

    private AgentInfoRespDTO convert(AgentInfoDO agent) {
        if (agent == null) {
            return null;
        }
        AgentInfoRespDTO dto = new AgentInfoRespDTO();
        dto.setId(agent.getId());
        dto.setAgentNum(agent.getAgentNum());
        dto.setAgentId(agent.getAgentId());
        dto.setName(agent.getName());
        dto.setRole(agent.getRole());
        dto.setAppId(agent.getAppId());
        dto.setOpenId(agent.getOpenId());
        dto.setStatus(agent.getStatus());
        dto.setNote(agent.getNote());
        dto.setCreateTime(agent.getCreateTime());
        return dto;
    }

}
