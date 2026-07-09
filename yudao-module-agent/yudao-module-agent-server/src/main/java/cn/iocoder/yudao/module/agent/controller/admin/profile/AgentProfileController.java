package cn.iocoder.yudao.module.agent.controller.admin.profile;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.agent.controller.admin.profile.vo.AgentProfileUpdateReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentProfileDO;
import cn.iocoder.yudao.module.agent.service.profile.AgentProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - Agent 能力档案")
@RestController
@RequestMapping("/agent/profile")
@Validated
public class AgentProfileController {

    @Resource
    private AgentProfileService agentProfileService;

    @GetMapping("/get")
    @Operation(summary = "查询 Agent 档案")
    @Parameter(name = "agentId", description = "Agent ID", required = true)
    @PreAuthorize("@ss.hasPermission('agent:profile:query')")
    public CommonResult<AgentProfileDO> getProfile(@RequestParam("agentId") String agentId) {
        AgentProfileDO profile = agentProfileService.getProfile(agentId);
        return success(profile);
    }

    @PutMapping("/update")
    @Operation(summary = "更新 Agent 档案")
    @PreAuthorize("@ss.hasPermission('agent:profile:update')")
    public CommonResult<Boolean> updateProfile(@Valid @RequestBody AgentProfileUpdateReqVO updateReqVO) {
        agentProfileService.updateProfile(updateReqVO);
        return success(true);
    }

    @GetMapping("/recommend")
    @Operation(summary = "HR 选派推荐")
    @Parameter(name = "skillTags", description = "技能标签（逗号分隔）", required = true)
    @Parameter(name = "limit", description = "推荐数量")
    @PreAuthorize("@ss.hasPermission('agent:profile:query')")
    public CommonResult<List<AgentProfileDO>> recommend(
            @RequestParam("skillTags") String skillTags,
            @RequestParam(value = "limit", required = false, defaultValue = "5") Integer limit) {
        List<AgentProfileDO> list = agentProfileService.recommend(skillTags, limit);
        return success(list);
    }

    @PostMapping("/recalc")
    @Operation(summary = "重算统计（从 agent_review 汇总）")
    @Parameter(name = "agentId", description = "Agent ID", required = true)
    @PreAuthorize("@ss.hasPermission('agent:profile:update')")
    public CommonResult<Boolean> recalc(@RequestParam("agentId") String agentId) {
        agentProfileService.recalc(agentId);
        return success(true);
    }

}
