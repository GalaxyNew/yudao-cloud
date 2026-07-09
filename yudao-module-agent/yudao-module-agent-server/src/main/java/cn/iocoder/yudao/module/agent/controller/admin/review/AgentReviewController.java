package cn.iocoder.yudao.module.agent.controller.admin.review;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.agent.controller.admin.review.vo.AgentReviewCreateReqVO;
import cn.iocoder.yudao.module.agent.controller.admin.review.vo.AgentReviewPageReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentReviewDO;
import cn.iocoder.yudao.module.agent.service.review.AgentReviewService;
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

@Tag(name = "管理后台 - Agent 绩效考核")
@RestController
@RequestMapping("/agent/review")
@Validated
public class AgentReviewController {

    @Resource
    private AgentReviewService agentReviewService;

    @PostMapping("/create")
    @Operation(summary = "创建考核记录")
    @PreAuthorize("@ss.hasPermission('agent:review:create')")
    public CommonResult<Long> createReview(@Valid @RequestBody AgentReviewCreateReqVO createReqVO) {
        Long id = agentReviewService.createReview(createReqVO);
        return success(id);
    }

    @GetMapping("/get")
    @Operation(summary = "查询考核记录")
    @Parameter(name = "id", description = "主键", required = true)
    @PreAuthorize("@ss.hasPermission('agent:review:query')")
    public CommonResult<AgentReviewDO> getReview(@RequestParam("id") Long id) {
        AgentReviewDO review = agentReviewService.getReview(id);
        return success(review);
    }

    @GetMapping("/list-by-agent")
    @Operation(summary = "按 Agent 查询考核列表")
    @Parameter(name = "agentId", description = "Agent ID", required = true)
    @PreAuthorize("@ss.hasPermission('agent:review:query')")
    public CommonResult<List<AgentReviewDO>> listByAgent(@RequestParam("agentId") String agentId) {
        List<AgentReviewDO> list = agentReviewService.listByAgentId(agentId);
        return success(list);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询考核记录")
    @PreAuthorize("@ss.hasPermission('agent:review:query')")
    public CommonResult<PageResult<AgentReviewDO>> pageReview(@Valid AgentReviewPageReqVO reqVO) {
        PageResult<AgentReviewDO> result = agentReviewService.pageReview(reqVO);
        return success(result);
    }

}
