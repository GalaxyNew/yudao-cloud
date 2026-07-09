package cn.iocoder.yudao.module.agent.controller.admin.modelroute.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - Agent 模型路由创建 Request VO")
@Data
@Accessors(chain = true)
public class AgentModelRouteCreateReqVO {

    @Schema(description = "Agent ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "Agent ID 不能为空")
    private String agentId;

    @Schema(description = "任务标签", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务标签不能为空")
    private String taskTag;

    @Schema(description = "推荐模型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "推荐模型不能为空")
    private String model;

    @Schema(description = "模型梯队", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "模型梯队不能为空")
    private String tier;

    @Schema(description = "优先级")
    private Integer priority;

}
