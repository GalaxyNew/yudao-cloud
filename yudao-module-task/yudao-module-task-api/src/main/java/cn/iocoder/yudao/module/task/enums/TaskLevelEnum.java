package cn.iocoder.yudao.module.task.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskLevelEnum {

    L1("简单"),
    L2("中等"),
    L3("困难");

    private final String name;
}
