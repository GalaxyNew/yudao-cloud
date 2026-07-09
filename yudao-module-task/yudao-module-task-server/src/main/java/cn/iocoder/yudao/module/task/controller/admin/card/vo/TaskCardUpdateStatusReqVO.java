package cn.iocoder.yudao.module.task.controller.admin.card.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 任务卡状态流转 Request VO")
@Data
@Accessors(chain = true)
public class TaskCardUpdateStatusReqVO {

    @Schema(description = "卡号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "卡号不能为空")
    private String id;

    @Schema(description = "目标状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "目标状态不能为空")
    private String status;

    @Schema(description = "是否执行门禁校验（提交验收时为 true）", example = "true")
    private Boolean gateCheck;

}
