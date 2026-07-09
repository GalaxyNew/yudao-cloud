package cn.iocoder.yudao.module.pm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 经验类型枚举
 */
@Getter
@AllArgsConstructor
public enum LessonTypeEnum {

    SUCCESS("成功经验"),
    PITFALL("踩坑记录"),
    PLAYBOOK("操作手册");

    private final String name;

}
