package cn.iocoder.yudao.module.task.dal.dataobject;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 任务卡 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("task_card")
public class TaskCardDO extends TenantBaseDO {

    /**
     * 卡号（如 T20260709-08）
     */
    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 状态机：DRAFT/STAFFING/ASSIGNED/IN_PROGRESS/REVIEW/PASSED/REJECTED/CANCELLED/BLOCKED
     */
    private String status;

    /**
     * 优先级：P0/P1/P2
     */
    private String priority;

    /**
     * 难度等级：L1/L2/L3
     */
    private String level;

    /**
     * 标签（JSON 数组字符串）
     */
    private String tags;

    /**
     * 负责 Agent ID
     */
    private String owner;

    /**
     * 协作方（JSON 数组）
     */
    private String collaborators;

    /**
     * 父任务卡 ID
     */
    private String parentId;

    /**
     * GitHub Issue 编号
     */
    private String githubIssue;

    /**
     * 证据等级：E1-E4
     */
    private String evidenceLevel;

    /**
     * 下发模型
     */
    private String model;

    /**
     * 截止时间
     */
    private LocalDateTime deadline;

    /**
     * 重试次数
     */
    private Integer retries;

    /**
     * 流程阶段（1-12）
     */
    private Integer processStage;

    /**
     * 开发文档引用（门禁字段）
     */
    private String docRef;

    /**
     * 技能标签
     */
    private String skillTags;

    /**
     * 难度描述
     */
    private String difficulty;

    /**
     * 范围摘要
     */
    private String scopeSummary;

}
