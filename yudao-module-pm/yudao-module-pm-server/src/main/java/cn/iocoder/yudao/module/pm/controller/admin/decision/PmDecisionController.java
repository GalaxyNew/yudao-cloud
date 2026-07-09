package cn.iocoder.yudao.module.pm.controller.admin.decision;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pm.controller.admin.decision.vo.*;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmDecisionDO;
import cn.iocoder.yudao.module.pm.service.decision.PmDecisionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 决策队列")
@RestController
@RequestMapping("/pm/decision")
@Validated
public class PmDecisionController {

    @Resource
    private PmDecisionService pmDecisionService;

    @PostMapping("/create")
    @Operation(summary = "创建决策项")
    @PreAuthorize("@ss.hasPermission('pm:decision:create')")
    public CommonResult<Long> createDecision(@Valid @RequestBody PmDecisionCreateReqVO createReqVO) {
        Long id = pmDecisionService.createDecision(createReqVO);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新决策项")
    @PreAuthorize("@ss.hasPermission('pm:decision:update')")
    public CommonResult<Boolean> updateDecision(@Valid @RequestBody PmDecisionUpdateReqVO updateReqVO) {
        pmDecisionService.updateDecision(updateReqVO);
        return success(true);
    }

    @PutMapping("/decide")
    @Operation(summary = "执行决策")
    @PreAuthorize("@ss.hasPermission('pm:decision:decide')")
    public CommonResult<Boolean> decide(@Valid @RequestBody PmDecisionDecideReqVO reqVO) {
        pmDecisionService.decide(reqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除决策项")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('pm:decision:delete')")
    public CommonResult<Boolean> deleteDecision(@RequestParam("id") Long id) {
        pmDecisionService.deleteDecision(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "查询决策项")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('pm:decision:query')")
    public CommonResult<PmDecisionDO> getDecision(@RequestParam("id") Long id) {
        return success(pmDecisionService.getDecision(id));
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询决策")
    @PreAuthorize("@ss.hasPermission('pm:decision:query')")
    public CommonResult<PageResult<PmDecisionDO>> pageDecision(@Valid PmDecisionPageReqVO reqVO) {
        return success(pmDecisionService.pageDecision(reqVO));
    }

}
