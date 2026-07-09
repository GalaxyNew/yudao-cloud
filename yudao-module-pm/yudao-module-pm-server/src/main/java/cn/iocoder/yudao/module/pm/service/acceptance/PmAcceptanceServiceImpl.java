package cn.iocoder.yudao.module.pm.service.acceptance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pm.controller.admin.acceptance.vo.PmAcceptanceCreateReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.acceptance.vo.PmAcceptancePageReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.acceptance.vo.PmAcceptanceUpdateReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmAcceptanceDO;
import cn.iocoder.yudao.module.pm.dal.mysql.PmAcceptanceMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.pm.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PmAcceptanceServiceImpl implements PmAcceptanceService {

    @Resource
    private PmAcceptanceMapper pmAcceptanceMapper;

    @Override
    public Long createAcceptance(PmAcceptanceCreateReqVO createReqVO) {
        // 校验同一任务不能重复验收
        if (pmAcceptanceMapper.selectByTaskId(createReqVO.getTaskId()) != null) {
            throw exception(ACCEPTANCE_TASK_EXISTS);
        }
        PmAcceptanceDO acceptance = new PmAcceptanceDO();
        acceptance.setTaskId(createReqVO.getTaskId());
        acceptance.setResult(createReqVO.getResult());
        acceptance.setVerifyCommand(createReqVO.getVerifyCommand());
        acceptance.setVerifyOutput(createReqVO.getVerifyOutput());
        acceptance.setReviewer(createReqVO.getReviewer());
        acceptance.setComment(createReqVO.getComment());
        pmAcceptanceMapper.insert(acceptance);
        return acceptance.getId();
    }

    @Override
    public void updateAcceptance(PmAcceptanceUpdateReqVO updateReqVO) {
        validateAcceptanceExists(updateReqVO.getId());
        PmAcceptanceDO update = new PmAcceptanceDO();
        update.setId(updateReqVO.getId());
        update.setTaskId(updateReqVO.getTaskId());
        update.setResult(updateReqVO.getResult());
        update.setVerifyCommand(updateReqVO.getVerifyCommand());
        update.setVerifyOutput(updateReqVO.getVerifyOutput());
        update.setReviewer(updateReqVO.getReviewer());
        update.setComment(updateReqVO.getComment());
        pmAcceptanceMapper.updateById(update);
    }

    @Override
    public PmAcceptanceDO getByTaskId(String taskId) {
        return pmAcceptanceMapper.selectByTaskId(taskId);
    }

    @Override
    public PageResult<PmAcceptanceDO> pageAcceptance(PmAcceptancePageReqVO reqVO) {
        return pmAcceptanceMapper.selectPage(reqVO);
    }

    private void validateAcceptanceExists(Long id) {
        if (pmAcceptanceMapper.selectById(id) == null) {
            throw exception(ACCEPTANCE_NOT_EXISTS);
        }
    }

}
