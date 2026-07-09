package cn.iocoder.yudao.module.pm.api.acceptance.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 验收记录 Response DTO")
@Data
@Accessors(chain = true)
public class PmAcceptanceRespDTO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "任务卡 ID")
    private String taskId;

    @Schema(description = "验收结果：PASSED/REJECTED")
    private String result;

    @Schema(description = "验证命令")
    private String verifyCommand;

    @Schema(description = "验证输出")
    private String verifyOutput;

    @Schema(description = "验收人 Agent ID")
    private String reviewer;

    @Schema(description = "评语")
    private String comment;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
