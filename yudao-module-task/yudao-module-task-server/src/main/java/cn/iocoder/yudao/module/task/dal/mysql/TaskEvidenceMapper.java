package cn.iocoder.yudao.module.task.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.task.controller.admin.evidence.vo.TaskEvidencePageReqVO;
import cn.iocoder.yudao.module.task.dal.dataobject.TaskEvidenceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 任务证据 Mapper
 */
@Mapper
public interface TaskEvidenceMapper extends BaseMapperX<TaskEvidenceDO> {

    default PageResult<TaskEvidenceDO> selectPage(TaskEvidencePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskEvidenceDO>()
                .eqIfPresent(TaskEvidenceDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(TaskEvidenceDO::getEvidenceType, reqVO.getEvidenceType())
                .betweenIfPresent(TaskEvidenceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskEvidenceDO::getId));
    }

    default List<TaskEvidenceDO> selectListByTaskId(String taskId) {
        return selectList(new LambdaQueryWrapperX<TaskEvidenceDO>()
                .eq(TaskEvidenceDO::getTaskId, taskId)
                .orderByAsc(TaskEvidenceDO::getSeq));
    }

    default Integer selectMaxSeqByTaskId(String taskId) {
        TaskEvidenceDO last = selectOne(new LambdaQueryWrapperX<TaskEvidenceDO>()
                .eq(TaskEvidenceDO::getTaskId, taskId)
                .orderByDesc(TaskEvidenceDO::getSeq)
                .last("LIMIT 1"));
        return last != null ? last.getSeq() : 0;
    }

}
