package cn.iocoder.yudao.module.agent.controller.admin.profile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Schema(description = "管理后台 - Agent 档案更新 Request VO")
@Data
@Accessors(chain = true)
public class AgentProfileUpdateReqVO {

    @Schema(description = "Agent ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String agentId;

    @Schema(description = "技能标签（逗号分隔）")
    private String skillTags;

    @Schema(description = "评级", example = "B")
    private String rating;

    @Schema(description = "模型梯队", example = "L")
    private String modelTier;

}
