package cn.iocoder.yudao.module.agent.controller.admin.info;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.agent.controller.admin.info.vo.AgentInfoCreateReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.info.vo.AgentInfoPageReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.info.vo.AgentInfoUpdateReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentInfoDO;
import cn.iocoder.yudao.module.agent.service.info.AgentInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - Agent 名录")
@RestController
@RequestMapping("/agent/info")
@Validated
public class AgentInfoController {

    @Resource
    private AgentInfoService agentInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建 Agent")
    @PreAuthorize("@ss.hasPermission('agent:info:create')")
    public CommonResult<Long> createAgent(@Valid @RequestBody AgentInfoCreateReqVO createReqVO) {
        Long id = agentInfoService.createAgent(createReqVO);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新 Agent")
    @PreAuthorize("@ss.hasPermission('agent:info:update')")
    public CommonResult<Boolean> updateAgent(@Valid @RequestBody AgentInfoUpdateReqVO updateReqVO) {
        agentInfoService.updateAgent(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除 Agent")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('agent:info:delete')")
    public CommonResult<Boolean> deleteAgent(@RequestParam("id") Long id) {
        agentInfoService.deleteAgent(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "查询 Agent 详情")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('agent:info:query')")
    public CommonResult<AgentInfoDO> getAgent(@RequestParam("id") Long id) {
        AgentInfoDO agent = agentInfoService.getAgent(id);
        return success(agent);
    }

    @GetMapping("/get-by-agent-id")
    @Operation(summary = "按 OpenClaw agent_id 查询")
    @Parameter(name = "agentId", description = "OpenClaw agent_id", required = true)
    @PreAuthorize("@ss.hasPermission('agent:info:query')")
    public CommonResult<AgentInfoDO> getAgentByAgentId(@RequestParam("agentId") String agentId) {
        AgentInfoDO agent = agentInfoService.getAgentByAgentId(agentId);
        return success(agent);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询 Agent")
    @PreAuthorize("@ss.hasPermission('agent:info:query')")
    public CommonResult<PageResult<AgentInfoDO>> pageAgent(@Valid AgentInfoPageReqVO reqVO) {
        PageResult<AgentInfoDO> result = agentInfoService.pageAgent(reqVO);
        return success(result);
    }

}
