package cn.iocoder.yudao.module.pm.dal.dataobject;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 决策队列 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pm_decision")
public class PmDecisionDO extends TenantBaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 项目 ID */
    private Long projectId;

    /** 决策编号（如 UQ-001） */
    private String code;

    /** 决策标题 */
    private String title;

    /** 描述 */
    private String description;

    /** 选项（JSON 数组） */
    private String options;

    /** 触发条件 */
    private String triggerCondition;

    /** 状态：PENDING/DECIDED/DEFERRED */
    private String status;

    /** 决策结果 */
    private String decision;

    /** 决策人 */
    private String decidedBy;

    /** 决策时间 */
    private LocalDateTime decidedAt;

}
