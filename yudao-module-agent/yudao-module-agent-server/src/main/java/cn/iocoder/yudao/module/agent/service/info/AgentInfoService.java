package cn.iocoder.yudao.module.agent.service.info;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.agent.controller.admin.info.vo.AgentInfoCreateReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.info.vo.AgentInfoPageReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.info.vo.AgentInfoUpdateReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentInfoDO;

/**
 * Agent 名录 Service 接口
 */
public interface AgentInfoService {

    /**
     * 创建 Agent
     */
    Long createAgent(AgentInfoCreateReqVO createReqVO);

    /**
     * 更新 Agent
     */
    void updateAgent(AgentInfoUpdateReqVO updateReqVO);

    /**
     * 删除 Agent（软删除）
     */
    void deleteAgent(Long id);

    /**
     * 获取 Agent
     */
    AgentInfoDO getAgent(Long id);

    /**
     * 按 OpenClaw agent_id 获取 Agent
     */
    AgentInfoDO getAgentByAgentId(String agentId);

    /**
     * 分页查询
     */
    PageResult<AgentInfoDO> pageAgent(AgentInfoPageReqVO reqVO);

}
