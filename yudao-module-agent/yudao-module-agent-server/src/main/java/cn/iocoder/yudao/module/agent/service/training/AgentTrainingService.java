package cn.iocoder.yudao.module.agent.service.training;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.agent.controller.admin.training.vo.AgentTrainingCreateReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.training.vo.AgentTrainingPageReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.training.vo.AgentTrainingUpdateReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentTrainingDO;

import java.util.List;

/**
 * Agent 培训记录 Service 接口
 */
public interface AgentTrainingService {

    /**
     * 创建培训记录
     */
    Long createTraining(AgentTrainingCreateReqVO createReqVO);

    /**
     * 更新培训记录
     */
    void updateTraining(AgentTrainingUpdateReqVO updateReqVO);

    /**
     * 获取培训记录
     */
    AgentTrainingDO getTraining(Long id);

    /**
     * 按 Agent 查询培训列表
     */
    List<AgentTrainingDO> listByAgentId(String agentId);

    /**
     * 分页查询
     */
    PageResult<AgentTrainingDO> pageTraining(AgentTrainingPageReqVO reqVO);

}
