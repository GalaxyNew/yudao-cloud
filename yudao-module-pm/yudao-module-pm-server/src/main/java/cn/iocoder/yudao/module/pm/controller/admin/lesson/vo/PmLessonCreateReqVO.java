package cn.iocoder.yudao.module.pm.controller.admin.lesson.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 经验卡创建 Request VO")
@Data
@Accessors(chain = true)
public class PmLessonCreateReqVO {

    @Schema(description = "任务卡 ID")
    private String taskId;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "SUCCESS")
    @NotEmpty(message = "类型不能为空")
    private String type;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "标题不能为空")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "标签（逗号分隔）")
    private String tags;

}
