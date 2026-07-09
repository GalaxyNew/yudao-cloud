package cn.iocoder.yudao.module.pm.controller.admin.milestone.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 里程碑状态变更 Request VO")
@Data
@Accessors(chain = true)
public class PmMilestoneUpdateStatusReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "目标状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "IN_PROGRESS")
    @NotNull(message = "目标状态不能为空")
    private String status;

}
