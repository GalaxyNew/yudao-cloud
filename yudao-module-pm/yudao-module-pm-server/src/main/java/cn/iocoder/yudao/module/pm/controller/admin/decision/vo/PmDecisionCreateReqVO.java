package cn.iocoder.yudao.module.pm.controller.admin.decision.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 决策创建 Request VO")
@Data
@Accessors(chain = true)
public class PmDecisionCreateReqVO {

    @Schema(description = "项目 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "项目 ID 不能为空")
    private Long projectId;

    @Schema(description = "决策编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "UQ-001")
    @NotEmpty(message = "决策编号不能为空")
    private String code;

    @Schema(description = "决策标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "决策标题不能为空")
    private String title;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "选项（JSON 数组）")
    private String options;

    @Schema(description = "触发条件")
    private String triggerCondition;

}
