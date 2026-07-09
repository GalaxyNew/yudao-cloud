package cn.iocoder.yudao.module.pm.service.milestone;

import cn.iocoder.yudao.module.pm.controller.admin.milestone.vo.PmMilestoneCreateReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.milestone.vo.PmMilestoneUpdateReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmMilestoneDO;

import java.util.List;

/**
 * 里程碑 Service 接口
 */
public interface PmMilestoneService {

    Long createMilestone(PmMilestoneCreateReqVO createReqVO);

    void updateMilestone(PmMilestoneUpdateReqVO updateReqVO);

    void updateMilestoneStatus(Long id, String status);

    void deleteMilestone(Long id);

    PmMilestoneDO getMilestone(Long id);

    List<PmMilestoneDO> listByProjectId(Long projectId);

}
