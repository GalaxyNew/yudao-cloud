package cn.iocoder.yudao.module.task.controller.admin.card;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.task.controller.admin.card.vo.*;
import cn.iocoder.yudao.module.task.dal.dataobject.TaskCardDO;
import cn.iocoder.yudao.module.task.service.card.TaskCardService;
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

@Tag(name = "管理后台 - 任务卡")
@RestController
@RequestMapping("/task/card")
@Validated
public class TaskCardController {

    @Resource
    private TaskCardService taskCardService;

    @PostMapping("/create")
    @Operation(summary = "创建任务卡")
    @PreAuthorize("@ss.hasPermission('task:card:create')")
    public CommonResult<String> createCard(@Valid @RequestBody TaskCardCreateReqVO createReqVO) {
        String id = taskCardService.createCard(createReqVO);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务卡")
    @PreAuthorize("@ss.hasPermission('task:card:update')")
    public CommonResult<Boolean> updateCard(@Valid @RequestBody TaskCardUpdateReqVO updateReqVO) {
        taskCardService.updateCard(updateReqVO);
        return success(true);
    }

    @PutMapping("/update-status")
    @Operation(summary = "状态流转（含门禁校验）")
    @PreAuthorize("@ss.hasPermission('task:card:update-status')")
    public CommonResult<Boolean> updateStatus(@Valid @RequestBody TaskCardUpdateStatusReqVO reqVO) {
        taskCardService.updateStatus(reqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务卡")
    @Parameter(name = "id", description = "卡号", required = true)
    @PreAuthorize("@ss.hasPermission('task:card:delete')")
    public CommonResult<Boolean> deleteCard(@RequestParam("id") String id) {
        taskCardService.deleteCard(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "查询单张任务卡")
    @Parameter(name = "id", description = "卡号", required = true)
    @PreAuthorize("@ss.hasPermission('task:card:query')")
    public CommonResult<TaskCardDO> getCard(@RequestParam("id") String id) {
        TaskCardDO card = taskCardService.getCard(id);
        return success(card);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询任务卡")
    @PreAuthorize("@ss.hasPermission('task:card:query')")
    public CommonResult<PageResult<TaskCardDO>> pageCard(@Valid TaskCardPageReqVO reqVO) {
        PageResult<TaskCardDO> result = taskCardService.pageCard(reqVO);
        return success(result);
    }

    @GetMapping("/list-by-parent")
    @Operation(summary = "查询子卡列表")
    @Parameter(name = "parentId", description = "父任务卡 ID", required = true)
    @PreAuthorize("@ss.hasPermission('task:card:query')")
    public CommonResult<List<TaskCardDO>> listByParent(@RequestParam("parentId") String parentId) {
        List<TaskCardDO> list = taskCardService.listByParent(parentId);
        return success(list);
    }

    @PostMapping("/assign")
    @Operation(summary = "派单")
    @PreAuthorize("@ss.hasPermission('task:card:assign')")
    public CommonResult<Boolean> assign(@Valid @RequestBody TaskCardAssignReqVO reqVO) {
        taskCardService.assign(reqVO);
        return success(true);
    }

    @PostMapping("/claim")
    @Operation(summary = "领单")
    @PreAuthorize("@ss.hasPermission('task:card:claim')")
    public CommonResult<Boolean> claim(@Valid @RequestBody TaskCardClaimReqVO reqVO) {
        taskCardService.claim(reqVO);
        return success(true);
    }

    @PostMapping("/submit-review")
    @Operation(summary = "提交验收")
    @Parameter(name = "id", description = "卡号", required = true)
    @PreAuthorize("@ss.hasPermission('task:card:submit-review')")
    public CommonResult<Boolean> submitReview(@RequestParam("id") String id) {
        taskCardService.submitReview(id);
        return success(true);
    }

    @PostMapping("/retry")
    @Operation(summary = "重试")
    @Parameter(name = "id", description = "卡号", required = true)
    @PreAuthorize("@ss.hasPermission('task:card:retry')")
    public CommonResult<Boolean> retry(@RequestParam("id") String id) {
        taskCardService.retry(id);
        return success(true);
    }

    @GetMapping("/validate-gate")
    @Operation(summary = "校验门禁条件")
    @Parameter(name = "id", description = "卡号", required = true)
    @PreAuthorize("@ss.hasPermission('task:card:query')")
    public CommonResult<GateCheckResultVO> validateGate(@RequestParam("id") String id) {
        GateCheckResultVO result = taskCardService.validateGate(id);
        return success(result);
    }

}
