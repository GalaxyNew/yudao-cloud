package cn.iocoder.yudao.module.pm.dal.dataobject;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 项目文档 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pm_document")
public class PmDocumentDO extends TenantBaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 项目 ID */
    private Long projectId;

    /** 文档标题 */
    private String title;

    /** 文档类型：总纲/PRD/架构/开发/PROGRESS */
    private String docType;

    /** infra 文件 token */
    private String fileToken;

    /** 仓库内路径 */
    private String repoPath;

    /** 版本号 */
    private String version;

    /** 审批状态 */
    private String approvalStatus;

}
