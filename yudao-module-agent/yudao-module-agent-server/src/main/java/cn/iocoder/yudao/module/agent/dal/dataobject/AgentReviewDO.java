package cn.iocoder.yudao.module.agent.dal.dataobject;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Agent 绩效考核 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("agent_review")
public class AgentReviewDO extends TenantBaseDO {

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
     * 任务卡 ID
     */
    private String taskId;

    /**
     * 维度-一次通过（0-100）
     */
    private Integer dimensionPass;

    /**
     * 维度-准时（0-100）
     */
    private Integer dimensionOntime;

    /**
     * 维度-证据完整（0-100）
     */
    private Integer dimensionEvidence;

    /**
     * 维度-合规（0-100）
     */
    private Integer dimensionCompliance;

    /**
     * 维度-协作（0-100）
     */
    private Integer dimensionCollab;

    /**
     * 综合评级：S/A/B/C/D
     */
    private String grade;

    /**
     * 评语
     */
    private String comment;

}
