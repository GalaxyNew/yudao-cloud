package cn.iocoder.yudao.module.agent.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModelTierEnum {

    L("大模型"),
    T("小模型");

    private final String name;
}
