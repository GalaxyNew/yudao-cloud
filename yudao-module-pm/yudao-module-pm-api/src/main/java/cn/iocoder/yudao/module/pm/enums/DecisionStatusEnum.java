package cn.iocoder.yudao.module.pm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 决策状态枚举
 */
@Getter
@AllArgsConstructor
public enum DecisionStatusEnum {

    PENDING("待决策"),
    DECIDED("已决策"),
    DEFERRED("已延期");

    private final String name;

}
