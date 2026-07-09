package cn.iocoder.yudao.module.pm.controller.admin.milestone.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Schema(description = "管理后台 - 里程碑更新 Request VO")
@Data
@Accessors(chain = true)
public class PmMilestoneUpdateReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "项目 ID")
    private Long projectId;

    @Schema(description = "里程碑编号")
    private String code;

    @Schema(description = "里程碑名称")
    private String name;

    @Schema(description = "目标")
    private String objective;

    @Schema(description = "交付物")
    private String deliverables;

    @Schema(description = "验收标准")
    private String acceptanceCriteria;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "开始日期")
    private LocalDate startDate;

    @Schema(description = "结束日期")
    private LocalDate endDate;

}
