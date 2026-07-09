package cn.iocoder.yudao.module.pm.service.decision;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pm.controller.admin.decision.vo.*;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmDecisionDO;

/**
 * 决策队列 Service 接口
 */
public interface PmDecisionService {

    Long createDecision(PmDecisionCreateReqVO createReqVO);

    void updateDecision(PmDecisionUpdateReqVO updateReqVO);

    void decide(PmDecisionDecideReqVO reqVO);

    void deleteDecision(Long id);

    PmDecisionDO getDecision(Long id);

    PageResult<PmDecisionDO> pageDecision(PmDecisionPageReqVO reqVO);

}
