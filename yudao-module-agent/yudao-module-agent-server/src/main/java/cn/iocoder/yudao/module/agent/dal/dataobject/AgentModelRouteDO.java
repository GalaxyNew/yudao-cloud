package cn.iocoder.yudao.module.agent.dal.dataobject;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Agent 模型路由 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("agent_model_route")
public class AgentModelRouteDO extends TenantBaseDO {

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
     * 任务标签
     */
    private String taskTag;

    /**
     * 推荐模型
     */
    private String model;

    /**
     * 模型梯队：L（大模型）/T（小模型）
     */
    private String tier;

    /**
     * 优先级（数字越大越优先）
     */
    private Integer priority;

}
