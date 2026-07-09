package cn.iocoder.yudao.module.pm.controller.admin.project.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 项目创建 Request VO")
@Data
@Accessors(chain = true)
public class PmProjectCreateReqVO {

    @Schema(description = "项目编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "M1")
    @NotEmpty(message = "项目编号不能为空")
    private String code;

    @Schema(description = "项目名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "项目名称不能为空")
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
