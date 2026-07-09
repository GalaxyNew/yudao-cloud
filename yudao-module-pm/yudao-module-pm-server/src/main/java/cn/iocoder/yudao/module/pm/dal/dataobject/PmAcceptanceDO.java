package cn.iocoder.yudao.module.pm.dal.dataobject;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 验收记录 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pm_acceptance")
public class PmAcceptanceDO extends TenantBaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 任务卡 ID（FK → task_card） */
    private String taskId;

    /** 验收结果：PASSED/REJECTED */
    private String result;

    /** 验证命令 */
    private String verifyCommand;

    /** 验证输出 */
    private String verifyOutput;

    /** 验收人 Agent ID */
    private String reviewer;

    /** 评语 */
    private String comment;

}
