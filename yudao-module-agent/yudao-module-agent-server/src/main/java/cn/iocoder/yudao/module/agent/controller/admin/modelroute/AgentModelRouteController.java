package cn.iocoder.yudao.module.agent.controller.admin.modelroute;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.agent.controller.admin.modelroute.vo.AgentModelRouteCreateReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.modelroute.vo.AgentModelRouteUpdateReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentModelRouteDO;
import cn.iocoder.yudao.module.agent.service.modelroute.AgentModelRouteService;
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

@Tag(name = "管理后台 - Agent 模型路由")
@RestController
@RequestMapping("/agent/model-route")
@Validated
public class AgentModelRouteController {

    @Resource
    private AgentModelRouteService agentModelRouteService;

    @PostMapping("/create")
    @Operation(summary = "创建路由规则")
    @PreAuthorize("@ss.hasPermission('agent:model-route:create')")
    public CommonResult<Long> createRoute(@Valid @RequestBody AgentModelRouteCreateReqVO createReqVO) {
        Long id = agentModelRouteService.createRoute(createReqVO);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新路由规则")
    @PreAuthorize("@ss.hasPermission('agent:model-route:update')")
    public CommonResult<Boolean> updateRoute(@Valid @RequestBody AgentModelRouteUpdateReqVO updateReqVO) {
        agentModelRouteService.updateRoute(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除路由规则")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('agent:model-route:delete')")
    public CommonResult<Boolean> deleteRoute(@RequestParam("id") Long id) {
        agentModelRouteService.deleteRoute(id);
        return success(true);
    }

    @GetMapping("/list-by-agent")
    @Operation(summary = "按 Agent 查询路由列表")
    @Parameter(name = "agentId", description = "Agent ID", required = true)
    @PreAuthorize("@ss.hasPermission('agent:model-route:query')")
    public CommonResult<List<AgentModelRouteDO>> listByAgent(@RequestParam("agentId") String agentId) {
        List<AgentModelRouteDO> list = agentModelRouteService.listByAgentId(agentId);
        return success(list);
    }

    @GetMapping("/route")
    @Operation(summary = "查询推荐模型")
    @Parameter(name = "agentId", description = "Agent ID", required = true)
    @Parameter(name = "taskTag", description = "任务标签", required = true)
    @PreAuthorize("@ss.hasPermission('agent:model-route:query')")
    public CommonResult<AgentModelRouteDO> route(
            @RequestParam("agentId") String agentId,
            @RequestParam("taskTag") String taskTag) {
        AgentModelRouteDO route = agentModelRouteService.routeModel(agentId, taskTag);
        return success(route);
    }

}
