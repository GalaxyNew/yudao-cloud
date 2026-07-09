package cn.iocoder.yudao.module.agent.api.info.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - Agent 信息 Response DTO")
@Data
@Accessors(chain = true)
public class AgentInfoRespDTO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "Agent 编号")
    private Integer agentNum;

    @Schema(description = "OpenClaw agent_id")
    private String agentId;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "角色描述")
    private String role;

    @Schema(description = "飞书 app_id")
    private String appId;

    @Schema(description = "飞书 open_id")
    private String openId;

    @Schema(description = "状态", example = "STAFF")
    private String status;

    @Schema(description = "备注")
    private String note;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
