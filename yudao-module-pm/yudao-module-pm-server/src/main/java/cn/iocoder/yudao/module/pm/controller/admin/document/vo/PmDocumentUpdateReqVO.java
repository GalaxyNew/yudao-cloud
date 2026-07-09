package cn.iocoder.yudao.module.pm.controller.admin.document.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 文档更新 Request VO")
@Data
@Accessors(chain = true)
public class PmDocumentUpdateReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "项目 ID")
    private Long projectId;

    @Schema(description = "文档标题")
    private String title;

    @Schema(description = "文档类型")
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
