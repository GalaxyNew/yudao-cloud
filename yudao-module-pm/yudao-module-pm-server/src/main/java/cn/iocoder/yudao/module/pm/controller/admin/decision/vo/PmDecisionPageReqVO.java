package cn.iocoder.yudao.module.pm.controller.admin.decision.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 决策分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PmDecisionPageReqVO extends PageParam {

    @Schema(description = "项目 ID")
    private Long projectId;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "决策标题（模糊）")
    private String title;

    @Schema(description = "创建时间")
    private LocalDateTime[] createTime;

}
