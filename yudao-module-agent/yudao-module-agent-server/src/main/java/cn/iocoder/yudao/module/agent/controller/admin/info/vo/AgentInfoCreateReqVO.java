package cn.iocoder.yudao.module.agent.controller.admin.info.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - Agent 创建 Request VO")
@Data
@Accessors(chain = true)
public class AgentInfoCreateReqVO {

    @Schema(description = "Agent 编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Agent 编号不能为空")
    private Integer agentNum;

    @Schema(description = "OpenClaw agent_id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "OpenClaw agent_id 不能为空")
    private String agentId;

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "名称不能为空")
    private String name;

    @Schema(description = "角色描述")
    private String role;

    @Schema(description = "飞书 app_id")
    private String appId;

    @Schema(description = "飞书 open_id")
    private String openId;

    @Schema(description = "状态", example = "STAFF")
    private String status;

    @Schema(description = "备注")
    private String note;

}
