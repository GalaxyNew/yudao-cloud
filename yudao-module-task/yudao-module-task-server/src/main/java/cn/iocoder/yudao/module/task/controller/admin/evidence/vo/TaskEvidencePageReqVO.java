package cn.iocoder.yudao.module.task.controller.admin.evidence.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 任务证据分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TaskEvidencePageReqVO extends PageParam {

    @Schema(description = "任务卡 ID")
    private String taskId;

    @Schema(description = "证据类型")
    private String evidenceType;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
