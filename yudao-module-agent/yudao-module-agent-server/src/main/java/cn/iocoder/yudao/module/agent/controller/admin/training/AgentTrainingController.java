package cn.iocoder.yudao.module.agent.controller.admin.training;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.agent.controller.admin.training.vo.AgentTrainingCreateReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.training.vo.AgentTrainingPageReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.training.vo.AgentTrainingUpdateReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentTrainingDO;
import cn.iocoder.yudao.module.agent.service.training.AgentTrainingService;
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

@Tag(name = "管理后台 - Agent 培训管理")
@RestController
@RequestMapping("/agent/training")
@Validated
public class AgentTrainingController {

    @Resource
    private AgentTrainingService agentTrainingService;

    @PostMapping("/create")
    @Operation(summary = "创建培训记录")
    @PreAuthorize("@ss.hasPermission('agent:training:create')")
    public CommonResult<Long> createTraining(@Valid @RequestBody AgentTrainingCreateReqVO createReqVO) {
        Long id = agentTrainingService.createTraining(createReqVO);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新培训记录")
    @PreAuthorize("@ss.hasPermission('agent:training:update')")
    public CommonResult<Boolean> updateTraining(@Valid @RequestBody AgentTrainingUpdateReqVO updateReqVO) {
        agentTrainingService.updateTraining(updateReqVO);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "查询培训记录")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('agent:training:query')")
    public CommonResult<AgentTrainingDO> getTraining(@RequestParam("id") Long id) {
        AgentTrainingDO training = agentTrainingService.getTraining(id);
        return success(training);
    }

    @GetMapping("/list-by-agent")
    @Operation(summary = "按 Agent 查询培训列表")
    @Parameter(name = "agentId", description = "Agent ID", required = true)
    @PreAuthorize("@ss.hasPermission('agent:training:query')")
    public CommonResult<List<AgentTrainingDO>> listByAgent(@RequestParam("agentId") String agentId) {
        List<AgentTrainingDO> list = agentTrainingService.listByAgentId(agentId);
        return success(list);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询培训记录")
    @PreAuthorize("@ss.hasPermission('agent:training:query')")
    public CommonResult<PageResult<AgentTrainingDO>> pageTraining(@Valid AgentTrainingPageReqVO reqVO) {
        PageResult<AgentTrainingDO> result = agentTrainingService.pageTraining(reqVO);
        return success(result);
    }

}
