package cn.iocoder.yudao.module.pm.controller.admin.decision.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 决策更新 Request VO")
@Data
@Accessors(chain = true)
public class PmDecisionUpdateReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "项目 ID")
    private Long projectId;

    @Schema(description = "决策编号")
    private String code;

    @Schema(description = "决策标题")
    private String title;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "选项（JSON 数组）")
    private String options;

    @Schema(description = "触发条件")
    private String triggerCondition;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "决策结果")
    private String decision;

    @Schema(description = "决策人")
    private String decidedBy;

}
