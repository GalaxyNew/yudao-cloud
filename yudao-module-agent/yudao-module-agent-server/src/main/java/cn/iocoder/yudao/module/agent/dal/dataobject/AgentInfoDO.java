package cn.iocoder.yudao.module.agent.dal.dataobject;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Agent 名录 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("agent_info")
public class AgentInfoDO extends TenantBaseDO {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * Agent 编号
     */
    private Integer agentNum;

    /**
     * OpenClaw agent_id
     */
    private String agentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String role;

    /**
     * 飞书 app_id
     */
    private String appId;

    /**
     * 飞书 open_id
     */
    private String openId;

    /**
     * 状态：STAFF/EXTERNAL/DISABLED
     */
    private String status;

    /**
     * 备注
     */
    private String note;

}
