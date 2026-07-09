package cn.iocoder.yudao.module.agent.dal.dataobject;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Agent 能力档案 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("agent_profile")
public class AgentProfileDO extends TenantBaseDO {

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
     * 技能标签（逗号分隔）
     */
    private String skillTags;

    /**
     * 累计分配数
     */
    private Integer totalAssigned;

    /**
     * 一次通过率
     */
    private BigDecimal passRateFirst;

    /**
     * 返工率
     */
    private BigDecimal reworkRate;

    /**
     * 当前进行中任务数
     */
    private Integer currentWip;

    /**
     * 评级：S/A/B/C/D
     */
    private String rating;

    /**
     * 模型梯队：L（大模型）/T（小模型）
     */
    private String modelTier;

}
