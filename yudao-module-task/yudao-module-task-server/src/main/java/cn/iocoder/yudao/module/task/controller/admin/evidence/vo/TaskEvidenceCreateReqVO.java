package cn.iocoder.yudao.module.task.controller.admin.evidence.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "证据创建 Request VO")
@Data
public class TaskEvidenceCreateReqVO {

    @Schema(description = "任务卡 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String taskId;

    @Schema(description = "动作描述", requiredMode = Schema.RequiredMode.REQUIRED)
    private String action;

    @Schema(description = "证据类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "COMMAND")
    private String evidenceType;

    @Schema(description = "证据内容")
    private String content;

    @Schema(description = "操作者 Agent ID")
    private String operator;
}
