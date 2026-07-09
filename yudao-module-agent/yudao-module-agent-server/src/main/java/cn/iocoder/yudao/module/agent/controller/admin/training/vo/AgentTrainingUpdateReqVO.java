package cn.iocoder.yudao.module.agent.controller.admin.training.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Schema(description = "管理后台 - Agent 培训更新 Request VO")
@Data
@Accessors(chain = true)
public class AgentTrainingUpdateReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "触发原因")
    private String triggerReason;

    @Schema(description = "培训材料路径")
    private String materialPath;

    @Schema(description = "挂载记录")
    private String mountRecord;

    @Schema(description = "考试结果")
    private String examResult;

    @Schema(description = "考试分数")
    private Integer examScore;

    @Schema(description = "生效日期")
    private LocalDate effectiveDate;

    @Schema(description = "状态")
    private String status;

}
