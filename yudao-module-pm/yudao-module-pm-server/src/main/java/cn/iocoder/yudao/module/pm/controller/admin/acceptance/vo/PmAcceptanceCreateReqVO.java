package cn.iocoder.yudao.module.pm.controller.admin.acceptance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 验收创建 Request VO")
@Data
@Accessors(chain = true)
public class PmAcceptanceCreateReqVO {

    @Schema(description = "任务卡 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务卡 ID 不能为空")
    private String taskId;

    @Schema(description = "验收结果", requiredMode = Schema.RequiredMode.REQUIRED, example = "PASSED")
    @NotEmpty(message = "验收结果不能为空")
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
