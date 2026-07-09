package cn.iocoder.yudao.module.pm.service.milestone;

import cn.iocoder.yudao.module.pm.controller.admin.milestone.vo.PmMilestoneCreateReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.milestone.vo.PmMilestoneUpdateReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmMilestoneDO;
import cn.iocoder.yudao.module.pm.dal.mysql.PmMilestoneMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.pm.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PmMilestoneServiceImpl implements PmMilestoneService {

    @Resource
    private PmMilestoneMapper pmMilestoneMapper;

    @Override
    public Long createMilestone(PmMilestoneCreateReqVO createReqVO) {
        PmMilestoneDO milestone = new PmMilestoneDO();
        milestone.setProjectId(createReqVO.getProjectId());
        milestone.setCode(createReqVO.getCode());
        milestone.setName(createReqVO.getName());
        milestone.setObjective(createReqVO.getObjective());
        milestone.setDeliverables(createReqVO.getDeliverables());
        milestone.setAcceptanceCriteria(createReqVO.getAcceptanceCriteria());
        milestone.setStatus(createReqVO.getStatus() != null ? createReqVO.getStatus() : "PLANNED");
        milestone.setStartDate(createReqVO.getStartDate());
        milestone.setEndDate(createReqVO.getEndDate());
        pmMilestoneMapper.insert(milestone);
        return milestone.getId();
    }

    @Override
    public void updateMilestone(PmMilestoneUpdateReqVO updateReqVO) {
        validateMilestoneExists(updateReqVO.getId());
        PmMilestoneDO update = new PmMilestoneDO();
        update.setId(updateReqVO.getId());
        update.setProjectId(updateReqVO.getProjectId());
        update.setCode(updateReqVO.getCode());
        update.setName(updateReqVO.getName());
        update.setObjective(updateReqVO.getObjective());
        update.setDeliverables(updateReqVO.getDeliverables());
        update.setAcceptanceCriteria(updateReqVO.getAcceptanceCriteria());
        update.setStatus(updateReqVO.getStatus());
        update.setStartDate(updateReqVO.getStartDate());
        update.setEndDate(updateReqVO.getEndDate());
        pmMilestoneMapper.updateById(update);
    }

    @Override
    public void updateMilestoneStatus(Long id, String status) {
        validateMilestoneExists(id);
        PmMilestoneDO update = new PmMilestoneDO();
        update.setId(id);
        update.setStatus(status);
        pmMilestoneMapper.updateById(update);
    }

    @Override
    public void deleteMilestone(Long id) {
        validateMilestoneExists(id);
        pmMilestoneMapper.deleteById(id);
    }

    @Override
    public PmMilestoneDO getMilestone(Long id) {
        return pmMilestoneMapper.selectById(id);
    }

    @Override
    public List<PmMilestoneDO> listByProjectId(Long projectId) {
        return pmMilestoneMapper.selectListByProjectId(projectId);
    }

    private void validateMilestoneExists(Long id) {
        if (pmMilestoneMapper.selectById(id) == null) {
            throw exception(MILESTONE_NOT_EXISTS);
        }
    }

}
