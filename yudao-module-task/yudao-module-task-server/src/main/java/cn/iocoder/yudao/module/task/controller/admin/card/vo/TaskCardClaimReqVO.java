package cn.iocoder.yudao.module.task.controller.admin.card.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 任务卡领单 Request VO")
@Data
@Accessors(chain = true)
public class TaskCardClaimReqVO {

    @Schema(description = "卡号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "卡号不能为空")
    private String id;

    @Schema(description = "Agent ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "Agent ID 不能为空")
    private String agentId;

}
