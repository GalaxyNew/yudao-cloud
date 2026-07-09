package cn.iocoder.yudao.module.agent.controller.admin.review.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - Agent 考核创建 Request VO")
@Data
@Accessors(chain = true)
public class AgentReviewCreateReqVO {

    @Schema(description = "Agent ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "Agent ID 不能为空")
    private String agentId;

    @Schema(description = "任务卡 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务卡 ID 不能为空")
    private String taskId;

    @Schema(description = "维度-一次通过（0-100）")
    private Integer dimensionPass;

    @Schema(description = "维度-准时（0-100）")
    private Integer dimensionOntime;

    @Schema(description = "维度-证据完整（0-100）")
    private Integer dimensionEvidence;

    @Schema(description = "维度-合规（0-100）")
    private Integer dimensionCompliance;

    @Schema(description = "维度-协作（0-100）")
    private Integer dimensionCollab;

    @Schema(description = "综合评级")
    private String grade;

    @Schema(description = "评语")
    private String comment;

}
