package cn.iocoder.yudao.module.agent.api.profile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Schema(description = "管理后台 - Agent 能力档案 Response DTO")
@Data
@Accessors(chain = true)
public class AgentProfileRespDTO {

    @Schema(description = "Agent ID")
    private String agentId;

    @Schema(description = "技能标签（逗号分隔）")
    private String skillTags;

    @Schema(description = "累计分配数")
    private Integer totalAssigned;

    @Schema(description = "一次通过率")
    private BigDecimal passRateFirst;

    @Schema(description = "返工率")
    private BigDecimal reworkRate;

    @Schema(description = "当前进行中任务数")
    private Integer currentWip;

    @Schema(description = "评级", example = "B")
    private String rating;

    @Schema(description = "模型梯队", example = "L")
    private String modelTier;
}
