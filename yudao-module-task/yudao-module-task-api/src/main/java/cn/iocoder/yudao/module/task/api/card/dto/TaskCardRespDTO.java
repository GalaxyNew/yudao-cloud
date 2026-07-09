package cn.iocoder.yudao.module.task.api.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 任务卡 Response DTO")
@Data
@Accessors(chain = true)
public class TaskCardRespDTO {

    @Schema(description = "卡号", example = "T20260709-08")
    private String id;

    @Schema(description = "任务标题")
    private String title;

    @Schema(description = "状态", example = "DRAFT")
    private String status;

    @Schema(description = "优先级", example = "P2")
    private String priority;

    @Schema(description = "难度等级", example = "L2")
    private String level;

    @Schema(description = "标签（JSON 数组）")
    private String tags;

    @Schema(description = "负责 Agent ID")
    private String owner;

    @Schema(description = "协作方（JSON 数组）")
    private String collaborators;

    @Schema(description = "父任务卡 ID")
    private String parentId;

    @Schema(description = "GitHub Issue 编号")
    private String githubIssue;

    @Schema(description = "证据等级", example = "E2")
    private String evidenceLevel;

    @Schema(description = "下发模型")
    private String model;

    @Schema(description = "截止时间")
    private LocalDateTime deadline;

    @Schema(description = "重试次数")
    private Integer retries;

    @Schema(description = "流程阶段（1-12）")
    private Integer processStage;

    @Schema(description = "开发文档引用")
    private String docRef;

    @Schema(description = "技能标签")
    private String skillTags;

    @Schema(description = "难度描述")
    private String difficulty;

    @Schema(description = "范围摘要")
    private String scopeSummary;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
