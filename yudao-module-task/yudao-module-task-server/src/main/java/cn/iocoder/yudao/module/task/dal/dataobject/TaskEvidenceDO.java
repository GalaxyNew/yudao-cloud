package cn.iocoder.yudao.module.task.dal.dataobject;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 任务证据 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("task_evidence")
public class TaskEvidenceDO extends TenantBaseDO {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 任务卡 ID
     */
    private String taskId;

    /**
     * 顺序号（同一任务内自增）
     */
    private Integer seq;

    /**
     * 记录时间
     */
    private LocalDateTime timestamp;

    /**
     * 动作描述
     */
    private String action;

    /**
     * 证据类型：URL/COMMAND/FILE_PATH/LOG/SCREENSHOT
     */
    private String evidenceType;

    /**
     * 证据内容
     */
    private String content;

    /**
     * 操作者 Agent ID
     */
    private String operator;

}
