package cn.iocoder.yudao.module.agent.service.info;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.agent.controller.admin.info.vo.AgentInfoCreateReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.info.vo.AgentInfoPageReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.info.vo.AgentInfoUpdateReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentInfoDO;
import cn.iocoder.yudao.module.agent.dal.mysql.AgentInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.agent.enums.ErrorCodeConstants.*;

/**
 * Agent 名录 Service 实现类
 */
@Service
@Validated
public class AgentInfoServiceImpl implements AgentInfoService {

    @Resource
    private AgentInfoMapper agentInfoMapper;

    @Override
    public Long createAgent(AgentInfoCreateReqVO createReqVO) {
        // 校验 agent_id 唯一
        if (agentInfoMapper.selectByAgentId(createReqVO.getAgentId()) != null) {
            throw exception(AGENT_AGENT_ID_DUPLICATE);
        }
        AgentInfoDO agent = new AgentInfoDO();
        agent.setAgentNum(createReqVO.getAgentNum());
        agent.setAgentId(createReqVO.getAgentId());
        agent.setName(createReqVO.getName());
        agent.setRole(createReqVO.getRole());
        agent.setAppId(createReqVO.getAppId());
        agent.setOpenId(createReqVO.getOpenId());
        agent.setStatus(createReqVO.getStatus() != null ? createReqVO.getStatus() : "STAFF");
        agent.setNote(createReqVO.getNote());
        agentInfoMapper.insert(agent);
        return agent.getId();
    }

    @Override
    public void updateAgent(AgentInfoUpdateReqVO updateReqVO) {
        // 校验存在
        AgentInfoDO existAgent = agentInfoMapper.selectById(updateReqVO.getId());
        if (existAgent == null) {
            throw exception(AGENT_NOT_EXISTS);
        }
        AgentInfoDO updateObj = new AgentInfoDO();
        updateObj.setId(updateReqVO.getId());
        updateObj.setAgentNum(updateReqVO.getAgentNum());
        updateObj.setAgentId(updateReqVO.getAgentId());
        updateObj.setName(updateReqVO.getName());
        updateObj.setRole(updateReqVO.getRole());
        updateObj.setAppId(updateReqVO.getAppId());
        updateObj.setOpenId(updateReqVO.getOpenId());
        updateObj.setStatus(updateReqVO.getStatus());
        updateObj.setNote(updateReqVO.getNote());
        agentInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteAgent(Long id) {
        AgentInfoDO existAgent = agentInfoMapper.selectById(id);
        if (existAgent == null) {
            throw exception(AGENT_NOT_EXISTS);
        }
        agentInfoMapper.deleteById(id);
    }

    @Override
    public AgentInfoDO getAgent(Long id) {
        return agentInfoMapper.selectById(id);
    }

    @Override
    public AgentInfoDO getAgentByAgentId(String agentId) {
        return agentInfoMapper.selectByAgentId(agentId);
    }

    @Override
    public PageResult<AgentInfoDO> pageAgent(AgentInfoPageReqVO reqVO) {
        return agentInfoMapper.selectPage(reqVO);
    }

}
