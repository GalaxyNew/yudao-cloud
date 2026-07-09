package cn.iocoder.yudao.module.agent.dal.dataobject;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Agent 培训记录 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("agent_training")
public class AgentTrainingDO extends TenantBaseDO {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * Agent ID
     */
    private String agentId;

    /**
     * 触发原因
     */
    private String triggerReason;

    /**
     * 培训材料路径
     */
    private String materialPath;

    /**
     * 挂载记录
     */
    private String mountRecord;

    /**
     * 考试结果
     */
    private String examResult;

    /**
     * 考试分数
     */
    private Integer examScore;

    /**
     * 生效日期
     */
    private LocalDate effectiveDate;

    /**
     * 状态
     */
    private String status;

}
