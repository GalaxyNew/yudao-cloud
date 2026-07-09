package cn.iocoder.yudao.module.pm.dal.dataobject;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 里程碑 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pm_milestone")
public class PmMilestoneDO extends TenantBaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 项目 ID */
    private Long projectId;

    /** 里程碑编号（如 M1） */
    private String code;

    /** 里程碑名称 */
    private String name;

    /** 目标 */
    private String objective;

    /** 交付物 */
    private String deliverables;

    /** 验收标准 */
    private String acceptanceCriteria;

    /** 状态 */
    private String status;

    /** 开始日期 */
    private LocalDate startDate;

    /** 结束日期 */
    private LocalDate endDate;

}
