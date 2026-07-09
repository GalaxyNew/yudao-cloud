package cn.iocoder.yudao.module.agent.service.profile;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.agent.controller.admin.profile.vo.AgentProfileUpdateReqVO;
import cn.iocoder.yudao.module.agent.dal.dataobject.AgentProfileDO;

import java.util.List;

/**
 * Agent 能力档案 Service 接口
 */
public interface AgentProfileService {

    /**
     * 获取 Agent 档案
     */
    AgentProfileDO getProfile(String agentId);

    /**
     * 更新 Agent 档案
     */
    void updateProfile(AgentProfileUpdateReqVO updateReqVO);

    /**
     * 重算统计（从 agent_review 汇总）
     */
    void recalc(String agentId);

    /**
     * HR 选派推荐
     */
    List<AgentProfileDO> recommend(String skillTags, Integer limit);

}
