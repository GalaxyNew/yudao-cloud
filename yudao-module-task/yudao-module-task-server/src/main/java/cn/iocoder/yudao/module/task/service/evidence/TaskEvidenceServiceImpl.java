package cn.iocoder.yudao.module.task.service.evidence;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.task.controller.admin.evidence.vo.TaskEvidenceCreateReqVO;
import cn.iocoder.yudao.module.task.controller.admin.evidence.vo.TaskEvidencePageReqVO;
import cn.iocoder.yudao.module.task.dal.dataobject.TaskEvidenceDO;
import cn.iocoder.yudao.module.task.dal.mysql.TaskEvidenceMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 任务证据 Service 实现类
 */
@Service
@Validated
public class TaskEvidenceServiceImpl implements TaskEvidenceService {

    @Resource
    private TaskEvidenceMapper taskEvidenceMapper;

    @Override
    public Long createEvidence(TaskEvidenceCreateReqVO createReqVO) {
        TaskEvidenceDO evidence = new TaskEvidenceDO();
        evidence.setTaskId(createReqVO.getTaskId());
        evidence.setAction(createReqVO.getAction());
        evidence.setEvidenceType(createReqVO.getEvidenceType());
        evidence.setContent(createReqVO.getContent());
        evidence.setOperator(createReqVO.getOperator());
        // 自动计算 seq
        Integer maxSeq = taskEvidenceMapper.selectMaxSeqByTaskId(createReqVO.getTaskId());
        evidence.setSeq(maxSeq + 1);
        taskEvidenceMapper.insert(evidence);
        return evidence.getId();
    }

    @Override
    public List<TaskEvidenceDO> listByTaskId(String taskId) {
        return taskEvidenceMapper.selectListByTaskId(taskId);
    }

    @Override
    public PageResult<TaskEvidenceDO> pageEvidence(TaskEvidencePageReqVO reqVO) {
        return taskEvidenceMapper.selectPage(reqVO);
    }

}
