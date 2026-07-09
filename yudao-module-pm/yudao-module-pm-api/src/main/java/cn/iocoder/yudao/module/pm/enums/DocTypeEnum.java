package cn.iocoder.yudao.module.pm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文档类型枚举
 */
@Getter
@AllArgsConstructor
public enum DocTypeEnum {

    MASTER("总纲"),
    PRD("PRD"),
    ARCH("架构"),
    DEV("开发"),
    PROGRESS("PROGRESS");

    private final String name;

}
