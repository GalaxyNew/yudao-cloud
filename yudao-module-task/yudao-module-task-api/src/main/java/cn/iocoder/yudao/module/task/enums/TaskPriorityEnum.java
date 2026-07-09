package cn.iocoder.yudao.module.task.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskPriorityEnum {

    P0("紧急"),
    P1("高"),
    P2("普通");

    private final String name;
}
