package cn.iocoder.yudao.module.pm.controller.admin.lesson.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 经验卡更新 Request VO")
@Data
@Accessors(chain = true)
public class PmLessonUpdateReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "任务卡 ID")
    private String taskId;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "标签（逗号分隔）")
    private String tags;

}
