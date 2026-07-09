package cn.iocoder.yudao.module.pm.api.lesson.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 经验卡 Response DTO")
@Data
@Accessors(chain = true)
public class PmLessonRespDTO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "任务卡 ID")
    private String taskId;

    @Schema(description = "类型：SUCCESS/PITFALL/PLAYBOOK")
    private String type;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "标签（逗号分隔）")
    private String tags;

    @Schema(description = "被引用次数")
    private Integer referenceCount;

}
