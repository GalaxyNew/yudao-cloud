package cn.iocoder.yudao.module.task.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EvidenceTypeEnum {

    URL("链接"),
    COMMAND("命令"),
    FILE_PATH("文件路径"),
    LOG("日志"),
    SCREENSHOT("截图");

    private final String name;
}
