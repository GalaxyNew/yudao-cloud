package cn.iocoder.yudao.module.pm.controller.admin.project.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 项目分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PmProjectPageReqVO extends PageParam {

    @Schema(description = "项目编号（模糊）")
    private String code;

    @Schema(description = "项目名称（模糊）")
    private String name;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "负责人 Agent ID")
    private String owner;

    @Schema(description = "创建时间")
    private LocalDateTime[] createTime;

}
