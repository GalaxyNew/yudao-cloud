package cn.iocoder.yudao.module.task.controller.admin.evidence;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.task.controller.admin.evidence.vo.TaskEvidenceCreateReqVO;
import cn.iocoder.yudao.module.task.controller.admin.evidence.vo.TaskEvidencePageReqVO;
import cn.iocoder.yudao.module.task.dal.dataobject.TaskEvidenceDO;
import cn.iocoder.yudao.module.task.service.evidence.TaskEvidenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 任务证据")
@RestController
@RequestMapping("/task/evidence")
@Validated
public class TaskEvidenceController {

    @Resource
    private TaskEvidenceService taskEvidenceService;

    @PostMapping("/create")
    @Operation(summary = "追加证据")
    @PreAuthorize("@ss.hasPermission('task:evidence:create')")
    public CommonResult<Long> createEvidence(@Valid @RequestBody TaskEvidenceCreateReqVO createReqVO) {
        Long id = taskEvidenceService.createEvidence(createReqVO);
        return success(id);
    }

    @GetMapping("/list-by-task")
    @Operation(summary = "查询任务的证据链")
    @Parameter(name = "taskId", description = "任务卡 ID", required = true)
    @PreAuthorize("@ss.hasPermission('task:evidence:query')")
    public CommonResult<List<TaskEvidenceDO>> listByTask(@RequestParam("taskId") String taskId) {
        List<TaskEvidenceDO> list = taskEvidenceService.listByTaskId(taskId);
        return success(list);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询证据")
    @PreAuthorize("@ss.hasPermission('task:evidence:query')")
    public CommonResult<PageResult<TaskEvidenceDO>> pageEvidence(@Valid TaskEvidencePageReqVO reqVO) {
        PageResult<TaskEvidenceDO> result = taskEvidenceService.pageEvidence(reqVO);
        return success(result);
    }

}
