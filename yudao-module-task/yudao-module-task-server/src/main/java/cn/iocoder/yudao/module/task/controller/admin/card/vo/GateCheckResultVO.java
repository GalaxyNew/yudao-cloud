package cn.iocoder.yudao.module.task.controller.admin.card.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Schema(description = "管理后台 - 门禁校验结果 VO")
@Data
@Accessors(chain = true)
public class GateCheckResultVO {

    @Schema(description = "校验是否全部通过")
    private boolean passed;

    @Schema(description = "缺失项列表", example = "[\"github_issue 不能为空\"]")
    private List<String> missingItems;

    public static GateCheckResultVO pass() {
        GateCheckResultVO vo = new GateCheckResultVO();
        vo.passed = true;
        vo.missingItems = List.of();
        return vo;
    }

    public static GateCheckResultVO fail(List<String> items) {
        GateCheckResultVO vo = new GateCheckResultVO();
        vo.passed = false;
        vo.missingItems = items;
        return vo;
    }

}
