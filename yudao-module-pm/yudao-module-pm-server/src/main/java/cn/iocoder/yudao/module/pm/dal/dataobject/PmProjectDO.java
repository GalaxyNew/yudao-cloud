package cn.iocoder.yudao.module.pm.dal.dataobject;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 项目 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pm_project")
public class PmProjectDO extends TenantBaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 项目编号（如 M1） */
    private String code;

    /** 项目名称 */
    private String name;

    /** 状态：ACTIVE/PAUSED/WARNING/FROZEN/COMPLETED/RED */
    private String status;

    /** 负责人 Agent ID */
    private String owner;

    /** 仓库地址 */
    private String repoUrl;

    /** 进度（0-100） */
    private Integer progress;

    /** 总纲摘要 */
    private String summary;

    /** 总纲文档地址 */
    private String docUrl;

}
