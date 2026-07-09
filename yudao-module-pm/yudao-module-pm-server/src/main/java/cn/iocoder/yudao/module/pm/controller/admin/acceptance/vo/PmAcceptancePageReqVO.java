package cn.iocoder.yudao.module.pm.controller.admin.acceptance.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 验收分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PmAcceptancePageReqVO extends PageParam {

    @Schema(description = "任务卡 ID")
    private String taskId;

    @Schema(description = "验收结果")
    private String result;

    @Schema(description = "验收人 Agent ID")
    private String reviewer;

    @Schema(description = "创建时间")
    private LocalDateTime[] createTime;

}
