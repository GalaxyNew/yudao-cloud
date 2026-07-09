package cn.iocoder.yudao.module.agent.controller.admin.info.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - Agent 更新 Request VO")
@Data
@Accessors(chain = true)
public class AgentInfoUpdateReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "Agent 编号")
    private Integer agentNum;

    @Schema(description = "OpenClaw agent_id")
    private String agentId;

    @Schema(description = "名称")
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
