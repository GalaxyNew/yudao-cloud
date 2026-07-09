package cn.iocoder.yudao.module.agent.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgentStatusEnum {

    STAFF("正式"),
    EXTERNAL("外部"),
    DISABLED("禁用");

    private final String name;
}
