package cn.iocoder.yudao.module.pm.controller.admin.document;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.pm.controller.admin.document.vo.PmDocumentCreateReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.document.vo.PmDocumentUpdateReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmDocumentDO;
import cn.iocoder.yudao.module.pm.service.document.PmDocumentService;
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

@Tag(name = "管理后台 - 文档管理")
@RestController
@RequestMapping("/pm/document")
@Validated
public class PmDocumentController {

    @Resource
    private PmDocumentService pmDocumentService;

    @PostMapping("/create")
    @Operation(summary = "创建文档引用")
    @PreAuthorize("@ss.hasPermission('pm:document:create')")
    public CommonResult<Long> createDocument(@Valid @RequestBody PmDocumentCreateReqVO createReqVO) {
        Long id = pmDocumentService.createDocument(createReqVO);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新文档引用")
    @PreAuthorize("@ss.hasPermission('pm:document:update')")
    public CommonResult<Boolean> updateDocument(@Valid @RequestBody PmDocumentUpdateReqVO updateReqVO) {
        pmDocumentService.updateDocument(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文档引用")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('pm:document:delete')")
    public CommonResult<Boolean> deleteDocument(@RequestParam("id") Long id) {
        pmDocumentService.deleteDocument(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "查询文档")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('pm:document:query')")
    public CommonResult<PmDocumentDO> getDocument(@RequestParam("id") Long id) {
        return success(pmDocumentService.getDocument(id));
    }

    @GetMapping("/list-by-project")
    @Operation(summary = "按项目查询文档")
    @Parameter(name = "projectId", description = "项目 ID", required = true)
    @PreAuthorize("@ss.hasPermission('pm:document:query')")
    public CommonResult<List<PmDocumentDO>> listByProject(@RequestParam("projectId") Long projectId) {
        return success(pmDocumentService.listByProjectId(projectId));
    }

}
