package cn.iocoder.yudao.module.pm.api.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 项目 Response DTO")
@Data
@Accessors(chain = true)
public class PmProjectRespDTO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "项目编号", example = "M1")
    private String code;

    @Schema(description = "项目名称")
    private String name;

    @Schema(description = "状态", example = "ACTIVE")
    private String status;

    @Schema(description = "负责人 Agent ID")
    private String owner;

    @Schema(description = "仓库地址")
    private String repoUrl;

    @Schema(description = "进度（0-100）")
    private Integer progress;

    @Schema(description = "总纲摘要")
    private String summary;

    @Schema(description = "总纲文档地址")
    private String docUrl;

}
