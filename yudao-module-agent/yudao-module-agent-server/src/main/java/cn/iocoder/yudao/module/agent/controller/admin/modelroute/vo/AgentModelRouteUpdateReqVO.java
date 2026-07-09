package cn.iocoder.yudao.module.agent.controller.admin.modelroute.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - Agent 模型路由更新 Request VO")
@Data
@Accessors(chain = true)
public class AgentModelRouteUpdateReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "任务标签")
    private String taskTag;

    @Schema(description = "推荐模型")
    private String model;

    @Schema(description = "模型梯队")
    private String tier;

    @Schema(description = "优先级")
    private Integer priority;

}
