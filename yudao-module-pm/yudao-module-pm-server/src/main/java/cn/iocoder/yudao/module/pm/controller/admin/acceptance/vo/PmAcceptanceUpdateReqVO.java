package cn.iocoder.yudao.module.pm.controller.admin.acceptance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 验收更新 Request VO")
@Data
@Accessors(chain = true)
public class PmAcceptanceUpdateReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "任务卡 ID")
    private String taskId;

    @Schema(description = "验收结果")
    private String result;

    @Schema(description = "验证命令")
    private String verifyCommand;

    @Schema(description = "验证输出")
    private String verifyOutput;

    @Schema(description = "验收人 Agent ID")
    private String reviewer;

    @Schema(description = "评语")
    private String comment;

}
