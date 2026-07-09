package cn.iocoder.yudao.module.task.api.evidence;

import cn.iocoder.yudao.module.task.api.evidence.dto.TaskEvidenceRespDTO;

import java.util.List;

/**
 * 证据链 RPC 接口
 */
public interface TaskEvidenceApi {

    /**
     * 查询任务的证据链列表
     */
    List<TaskEvidenceRespDTO> listByTaskId(String taskId);
}
