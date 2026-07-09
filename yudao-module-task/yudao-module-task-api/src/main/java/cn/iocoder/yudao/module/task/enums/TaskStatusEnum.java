package cn.iocoder.yudao.module.task.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatusEnum {

    DRAFT("草稿"),
    STAFFING("人员配置中"),
    ASSIGNED("已派单"),
    IN_PROGRESS("进行中"),
    REVIEW("待验收"),
    PASSED("已通过"),
    REJECTED("已驳回"),
    CANCELLED("已取消"),
    BLOCKED("已阻塞");

    private final String name;
}
