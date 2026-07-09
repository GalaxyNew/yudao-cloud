package cn.iocoder.yudao.module.pm.controller.admin.milestone;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.pm.controller.admin.milestone.vo.*;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmMilestoneDO;
import cn.iocoder.yudao.module.pm.service.milestone.PmMilestoneService;
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

@Tag(name = "管理后台 - 里程碑管理")
@RestController
@RequestMapping("/pm/milestone")
@Validated
public class PmMilestoneController {

    @Resource
    private PmMilestoneService pmMilestoneService;

    @PostMapping("/create")
    @Operation(summary = "创建里程碑")
    @PreAuthorize("@ss.hasPermission('pm:milestone:create')")
    public CommonResult<Long> createMilestone(@Valid @RequestBody PmMilestoneCreateReqVO createReqVO) {
        Long id = pmMilestoneService.createMilestone(createReqVO);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新里程碑")
    @PreAuthorize("@ss.hasPermission('pm:milestone:update')")
    public CommonResult<Boolean> updateMilestone(@Valid @RequestBody PmMilestoneUpdateReqVO updateReqVO) {
        pmMilestoneService.updateMilestone(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除里程碑")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('pm:milestone:delete')")
    public CommonResult<Boolean> deleteMilestone(@RequestParam("id") Long id) {
        pmMilestoneService.deleteMilestone(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "查询里程碑")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('pm:milestone:query')")
    public CommonResult<PmMilestoneDO> getMilestone(@RequestParam("id") Long id) {
        return success(pmMilestoneService.getMilestone(id));
    }

    @GetMapping("/list-by-project")
    @Operation(summary = "按项目查询里程碑")
    @Parameter(name = "projectId", description = "项目 ID", required = true)
    @PreAuthorize("@ss.hasPermission('pm:milestone:query')")
    public CommonResult<List<PmMilestoneDO>> listByProject(@RequestParam("projectId") Long projectId) {
        return success(pmMilestoneService.listByProjectId(projectId));
    }

    @PutMapping("/update-status")
    @Operation(summary = "里程碑状态变更")
    @PreAuthorize("@ss.hasPermission('pm:milestone:update')")
    public CommonResult<Boolean> updateStatus(@Valid @RequestBody PmMilestoneUpdateStatusReqVO reqVO) {
        pmMilestoneService.updateMilestoneStatus(reqVO.getId(), reqVO.getStatus());
        return success(true);
    }

}
