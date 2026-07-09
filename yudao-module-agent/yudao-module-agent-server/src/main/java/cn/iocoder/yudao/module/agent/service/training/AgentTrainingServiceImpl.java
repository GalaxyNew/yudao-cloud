package cn.iocoder.yudao.module.agent.service.training;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.agent.controller.admin.training.vo.AgentTrainingCreateReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.training.vo.AgentTrainingPageReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.training.vo.AgentTrainingUpdateReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentTrainingDO;
import cn.iocoder.yudao.module.agent.dal.mysql.AgentTrainingMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.agent.enums.ErrorCodeConstants.TRAINING_NOT_EXISTS;

/**
 * Agent 培训记录 Service 实现类
 */
@Service
@Validated
public class AgentTrainingServiceImpl implements AgentTrainingService {

    @Resource
    private AgentTrainingMapper agentTrainingMapper;

    @Override
    public Long createTraining(AgentTrainingCreateReqVO createReqVO) {
        AgentTrainingDO training = new AgentTrainingDO();
        training.setAgentId(createReqVO.getAgentId());
        training.setTriggerReason(createReqVO.getTriggerReason());
        training.setMaterialPath(createReqVO.getMaterialPath());
        training.setMountRecord(createReqVO.getMountRecord());
        training.setExamResult(createReqVO.getExamResult());
        training.setExamScore(createReqVO.getExamScore());
        training.setEffectiveDate(createReqVO.getEffectiveDate());
        training.setStatus(createReqVO.getStatus() != null ? createReqVO.getStatus() : "PENDING");
        agentTrainingMapper.insert(training);
        return training.getId();
    }

    @Override
    public void updateTraining(AgentTrainingUpdateReqVO updateReqVO) {
        AgentTrainingDO exist = agentTrainingMapper.selectById(updateReqVO.getId());
        if (exist == null) {
            throw exception(TRAINING_NOT_EXISTS);
        }
        AgentTrainingDO updateObj = new AgentTrainingDO();
        updateObj.setId(updateReqVO.getId());
        updateObj.setTriggerReason(updateReqVO.getTriggerReason());
        updateObj.setMaterialPath(updateReqVO.getMaterialPath());
        updateObj.setMountRecord(updateReqVO.getMountRecord());
        updateObj.setExamResult(updateReqVO.getExamResult());
        updateObj.setExamScore(updateReqVO.getExamScore());
        updateObj.setEffectiveDate(updateReqVO.getEffectiveDate());
        updateObj.setStatus(updateReqVO.getStatus());
        agentTrainingMapper.updateById(updateObj);
    }

    @Override
    public AgentTrainingDO getTraining(Long id) {
        return agentTrainingMapper.selectById(id);
    }

    @Override
    public List<AgentTrainingDO> listByAgentId(String agentId) {
        return agentTrainingMapper.selectListByAgentId(agentId);
    }

    @Override
    public PageResult<AgentTrainingDO> pageTraining(AgentTrainingPageReqVO reqVO) {
        return agentTrainingMapper.selectPage(reqVO);
    }

}
