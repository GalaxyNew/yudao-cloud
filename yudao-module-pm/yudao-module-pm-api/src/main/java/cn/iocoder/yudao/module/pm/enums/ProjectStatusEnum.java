package cn.iocoder.yudao.module.pm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 项目状态枚举
 */
@Getter
@AllArgsConstructor
public enum ProjectStatusEnum {

    ACTIVE("进行中"),
    PAUSED("暂停"),
    WARNING("预警"),
    FROZEN("冻结"),
    COMPLETED("已完成"),
    RED("红色警报");

    private final String name;

}
