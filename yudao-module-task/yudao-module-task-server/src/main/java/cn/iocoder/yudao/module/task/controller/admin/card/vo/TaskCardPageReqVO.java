package cn.iocoder.yudao.module.task.controller.admin.card.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 任务卡分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TaskCardPageReqVO extends PageParam {

    @Schema(description = "任务标题（模糊）")
    private String title;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "负责 Agent ID")
    private String owner;

    @Schema(description = "优先级")
    private String priority;

    @Schema(description = "父任务卡 ID")
    private String parentId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
