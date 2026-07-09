package cn.iocoder.yudao.module.pm.controller.admin.lesson;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pm.controller.admin.lesson.vo.PmLessonCreateReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.lesson.vo.PmLessonPageReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.lesson.vo.PmLessonUpdateReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmLessonDO;
import cn.iocoder.yudao.module.pm.service.lesson.PmLessonService;
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

@Tag(name = "管理后台 - 经验库")
@RestController
@RequestMapping("/pm/lesson")
@Validated
public class PmLessonController {

    @Resource
    private PmLessonService pmLessonService;

    @PostMapping("/create")
    @Operation(summary = "创建经验卡")
    @PreAuthorize("@ss.hasPermission('pm:lesson:create')")
    public CommonResult<Long> createLesson(@Valid @RequestBody PmLessonCreateReqVO createReqVO) {
        Long id = pmLessonService.createLesson(createReqVO);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新经验卡")
    @PreAuthorize("@ss.hasPermission('pm:lesson:update')")
    public CommonResult<Boolean> updateLesson(@Valid @RequestBody PmLessonUpdateReqVO updateReqVO) {
        pmLessonService.updateLesson(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除经验卡")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('pm:lesson:delete')")
    public CommonResult<Boolean> deleteLesson(@RequestParam("id") Long id) {
        pmLessonService.deleteLesson(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "查询经验卡")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('pm:lesson:query')")
    public CommonResult<PmLessonDO> getLesson(@RequestParam("id") Long id) {
        return success(pmLessonService.getLesson(id));
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询经验卡")
    @PreAuthorize("@ss.hasPermission('pm:lesson:query')")
    public CommonResult<PageResult<PmLessonDO>> pageLesson(@Valid PmLessonPageReqVO reqVO) {
        return success(pmLessonService.pageLesson(reqVO));
    }

    @GetMapping("/search")
    @Operation(summary = "按标签检索经验卡")
    @Parameter(name = "keyword", description = "关键词", required = true)
    @PreAuthorize("@ss.hasPermission('pm:lesson:query')")
    public CommonResult<List<PmLessonDO>> search(@RequestParam("keyword") String keyword) {
        List<String> tags = List.of(keyword.split(","));
        return success(pmLessonService.listByTags(tags));
    }

}
