package cn.iocoder.yudao.module.pm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 验收结果枚举
 */
@Getter
@AllArgsConstructor
public enum AcceptanceResultEnum {

    PASSED("通过"),
    REJECTED("驳回");

    private final String name;

}
