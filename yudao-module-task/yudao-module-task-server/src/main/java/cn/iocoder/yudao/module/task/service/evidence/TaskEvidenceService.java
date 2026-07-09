package cn.iocoder.yudao.module.task.service.evidence;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.task.controller.admin.evidence.vo.TaskEvidenceCreateReqVO;
import cn.iocoder.yudao.module.task.controller.admin.evidence.vo.TaskEvidencePageReqVO;
import cn.iocoder.yudao.module.task.dal.dataobject.TaskEvidenceDO;

import java.util.List;

public interface TaskEvidenceService {

    Long createEvidence(TaskEvidenceCreateReqVO createReqVO);

    List<TaskEvidenceDO> listByTaskId(String taskId);

    PageResult<TaskEvidenceDO> pageEvidence(TaskEvidencePageReqVO reqVO);
}
