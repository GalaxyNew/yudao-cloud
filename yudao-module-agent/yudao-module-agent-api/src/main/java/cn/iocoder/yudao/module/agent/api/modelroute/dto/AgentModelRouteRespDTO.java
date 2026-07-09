package cn.iocoder.yudao.module.agent.api.modelroute.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - Agent 模型路由 Response DTO")
@Data
@Accessors(chain = true)
public class AgentModelRouteRespDTO {

    @Schema(description = "Agent ID")
    private String agentId;

    @Schema(description = "任务标签")
    private String taskTag;

    @Schema(description = "推荐模型")
    private String model;

    @Schema(description = "模型梯队", example = "L")
    private String tier;

    @Schema(description = "优先级")
    private Integer priority;
}
