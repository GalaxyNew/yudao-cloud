package cn.iocoder.yudao.module.pm.service.acceptance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pm.controller.admin.acceptance.vo.PmAcceptanceCreateReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.acceptance.vo.PmAcceptanceUpdateReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.acceptance.vo.PmAcceptancePageReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmAcceptanceDO;

/**
 * 验收 Service 接口
 */
public interface PmAcceptanceService {

    Long createAcceptance(PmAcceptanceCreateReqVO createReqVO);

    void updateAcceptance(PmAcceptanceUpdateReqVO updateReqVO);

    PmAcceptanceDO getByTaskId(String taskId);

    PageResult<PmAcceptanceDO> pageAcceptance(PmAcceptancePageReqVO reqVO);

}
