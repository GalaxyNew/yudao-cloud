package cn.iocoder.yudao.module.pm.controller.admin.decision.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 决策执行 Request VO")
@Data
@Accessors(chain = true)
public class PmDecisionDecideReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "决策结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "决策结果不能为空")
    private String decision;

    @Schema(description = "决策人")
    private String decidedBy;

}
