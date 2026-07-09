package cn.iocoder.yudao.module.agent.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgentRatingEnum {

    S("S 卓越"),
    A("A 优秀"),
    B("B 良好"),
    C("C 一般"),
    D("D 待改进");

    private final String name;
}
