package cn.iocoder.yudao.module.pm.controller.admin.project.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 项目精简列表 VO")
@Data
public class PmProjectSimpleRespVO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "项目编号")
    private String code;

    @Schema(description = "项目名称")
    private String name;

}
