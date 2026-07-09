package cn.iocoder.yudao.module.pm.service.decision;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pm.controller.admin.decision.vo.*;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmDecisionDO;
import cn.iocoder.yudao.module.pm.dal.mysql.PmDecisionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.pm.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PmDecisionServiceImpl implements PmDecisionService {

    @Resource
    private PmDecisionMapper pmDecisionMapper;

    @Override
    public Long createDecision(PmDecisionCreateReqVO createReqVO) {
        // 校验编号唯一
        if (pmDecisionMapper.selectCount(
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<PmDecisionDO>()
                        .eq(PmDecisionDO::getProjectId, createReqVO.getProjectId())
                        .eq(PmDecisionDO::getCode, createReqVO.getCode())) > 0) {
            throw exception(DECISION_CODE_EXISTS);
        }
        PmDecisionDO decision = new PmDecisionDO();
        decision.setProjectId(createReqVO.getProjectId());
        decision.setCode(createReqVO.getCode());
        decision.setTitle(createReqVO.getTitle());
        decision.setDescription(createReqVO.getDescription());
        decision.setOptions(createReqVO.getOptions());
        decision.setTriggerCondition(createReqVO.getTriggerCondition());
        decision.setStatus("PENDING");
        pmDecisionMapper.insert(decision);
        return decision.getId();
    }

    @Override
    public void updateDecision(PmDecisionUpdateReqVO updateReqVO) {
        validateDecisionExists(updateReqVO.getId());
        PmDecisionDO update = new PmDecisionDO();
        update.setId(updateReqVO.getId());
        update.setProjectId(updateReqVO.getProjectId());
        update.setCode(updateReqVO.getCode());
        update.setTitle(updateReqVO.getTitle());
        update.setDescription(updateReqVO.getDescription());
        update.setOptions(updateReqVO.getOptions());
        update.setTriggerCondition(updateReqVO.getTriggerCondition());
        update.setStatus(updateReqVO.getStatus());
        update.setDecision(updateReqVO.getDecision());
        update.setDecidedBy(updateReqVO.getDecidedBy());
        pmDecisionMapper.updateById(update);
    }

    @Override
    public void decide(PmDecisionDecideReqVO reqVO) {
        PmDecisionDO decision = validateDecisionExists(reqVO.getId());
        if (!"PENDING".equals(decision.getStatus())) {
            throw exception(DECISION_NOT_ALLOW_DECIDE);
        }
        PmDecisionDO update = new PmDecisionDO();
        update.setId(reqVO.getId());
        update.setStatus("DECIDED");
        update.setDecision(reqVO.getDecision());
        update.setDecidedBy(reqVO.getDecidedBy());
        update.setDecidedAt(LocalDateTime.now());
        pmDecisionMapper.updateById(update);
    }

    @Override
    public void deleteDecision(Long id) {
        validateDecisionExists(id);
        pmDecisionMapper.deleteById(id);
    }

    @Override
    public PmDecisionDO getDecision(Long id) {
        return pmDecisionMapper.selectById(id);
    }

    @Override
    public PageResult<PmDecisionDO> pageDecision(PmDecisionPageReqVO reqVO) {
        return pmDecisionMapper.selectPage(reqVO);
    }

    private PmDecisionDO validateDecisionExists(Long id) {
        PmDecisionDO decision = pmDecisionMapper.selectById(id);
        if (decision == null) {
            throw exception(DECISION_NOT_EXISTS);
        }
        return decision;
    }

}
