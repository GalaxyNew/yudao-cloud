package cn.iocoder.yudao.module.pm.controller.admin.acceptance;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pm.controller.admin.acceptance.vo.PmAcceptanceCreateReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.acceptance.vo.PmAcceptancePageReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.acceptance.vo.PmAcceptanceUpdateReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmAcceptanceDO;
import cn.iocoder.yudao.module.pm.service.acceptance.PmAcceptanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 验收管理")
@RestController
@RequestMapping("/pm/acceptance")
@Validated
public class PmAcceptanceController {

    @Resource
    private PmAcceptanceService pmAcceptanceService;

    @PostMapping("/create")
    @Operation(summary = "创建验收记录")
    @PreAuthorize("@ss.hasPermission('pm:acceptance:create')")
    public CommonResult<Long> createAcceptance(@Valid @RequestBody PmAcceptanceCreateReqVO createReqVO) {
        Long id = pmAcceptanceService.createAcceptance(createReqVO);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新验收记录")
    @PreAuthorize("@ss.hasPermission('pm:acceptance:update')")
    public CommonResult<Boolean> updateAcceptance(@Valid @RequestBody PmAcceptanceUpdateReqVO updateReqVO) {
        pmAcceptanceService.updateAcceptance(updateReqVO);
        return success(true);
    }

    @GetMapping("/get-by-task")
    @Operation(summary = "按任务查询验收结果")
    @Parameter(name = "taskId", description = "任务卡 ID", required = true)
    @PreAuthorize("@ss.hasPermission('pm:acceptance:query')")
    public CommonResult<PmAcceptanceDO> getByTask(@RequestParam("taskId") String taskId) {
        return success(pmAcceptanceService.getByTaskId(taskId));
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询验收")
    @PreAuthorize("@ss.hasPermission('pm:acceptance:query')")
    public CommonResult<PageResult<PmAcceptanceDO>> pageAcceptance(@Valid PmAcceptancePageReqVO reqVO) {
        return success(pmAcceptanceService.pageAcceptance(reqVO));
    }

}
