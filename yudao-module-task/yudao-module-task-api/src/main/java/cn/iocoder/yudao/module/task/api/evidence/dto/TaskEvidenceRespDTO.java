package cn.iocoder.yudao.module.task.api.evidence.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 任务证据 Response DTO")
@Data
@Accessors(chain = true)
public class TaskEvidenceRespDTO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "任务卡 ID")
    private String taskId;

    @Schema(description = "顺序号")
    private Integer seq;

    @Schema(description = "记录时间")
    private LocalDateTime timestamp;

    @Schema(description = "动作描述")
    private String action;

    @Schema(description = "证据类型")
    private String evidenceType;

    @Schema(description = "证据内容")
    private String content;

    @Schema(description = "操作者 Agent ID")
    private String operator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
