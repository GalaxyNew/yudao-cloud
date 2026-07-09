package cn.iocoder.yudao.module.pm.controller.admin.lesson.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 经验卡分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PmLessonPageReqVO extends PageParam {

    @Schema(description = "类型")
    private String type;

    @Schema(description = "标签（模糊）")
    private String tags;

    @Schema(description = "标题（模糊）")
    private String title;

    @Schema(description = "创建时间")
    private LocalDateTime[] createTime;

}
