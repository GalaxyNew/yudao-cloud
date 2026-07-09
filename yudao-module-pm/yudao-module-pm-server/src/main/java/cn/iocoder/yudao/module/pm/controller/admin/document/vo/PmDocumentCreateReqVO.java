package cn.iocoder.yudao.module.pm.controller.admin.document.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 文档创建 Request VO")
@Data
@Accessors(chain = true)
public class PmDocumentCreateReqVO {

    @Schema(description = "项目 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "项目 ID 不能为空")
    private Long projectId;

    @Schema(description = "文档标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "文档标题不能为空")
    private String title;

    @Schema(description = "文档类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "PRD")
    @NotEmpty(message = "文档类型不能为空")
    private String docType;

    @Schema(description = "infra 文件 token")
    private String fileToken;

    @Schema(description = "仓库内路径")
    private String repoPath;

    @Schema(description = "版本号")
    private String version;

    @Schema(description = "审批状态")
    private String approvalStatus;

}
