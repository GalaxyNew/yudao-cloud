package cn.iocoder.yudao.module.pm.controller.admin.project;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pm.controller.admin.project.vo.*;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmProjectDO;
import cn.iocoder.yudao.module.pm.service.project.PmProjectService;
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

@Tag(name = "管理后台 - 项目管理")
@RestController
@RequestMapping("/pm/project")
@Validated
public class PmProjectController {

    @Resource
    private PmProjectService pmProjectService;

    @PostMapping("/create")
    @Operation(summary = "创建项目")
    @PreAuthorize("@ss.hasPermission('pm:project:create')")
    public CommonResult<Long> createProject(@Valid @RequestBody PmProjectCreateReqVO createReqVO) {
        Long id = pmProjectService.createProject(createReqVO);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新项目")
    @PreAuthorize("@ss.hasPermission('pm:project:update')")
    public CommonResult<Boolean> updateProject(@Valid @RequestBody PmProjectUpdateReqVO updateReqVO) {
        pmProjectService.updateProject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除项目")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('pm:project:delete')")
    public CommonResult<Boolean> deleteProject(@RequestParam("id") Long id) {
        pmProjectService.deleteProject(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "查询项目详情")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('pm:project:query')")
    public CommonResult<PmProjectDO> getProject(@RequestParam("id") Long id) {
        return success(pmProjectService.getProject(id));
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询项目")
    @PreAuthorize("@ss.hasPermission('pm:project:query')")
    public CommonResult<PageResult<PmProjectDO>> pageProject(@Valid PmProjectPageReqVO reqVO) {
        return success(pmProjectService.pageProject(reqVO));
    }

    @GetMapping("/list-simple")
    @Operation(summary = "精简列表（下拉选用）")
    @PreAuthorize("@ss.hasPermission('pm:project:query')")
    public CommonResult<List<PmProjectSimpleRespVO>> listSimple() {
        return success(pmProjectService.getSimpleList());
    }

}
