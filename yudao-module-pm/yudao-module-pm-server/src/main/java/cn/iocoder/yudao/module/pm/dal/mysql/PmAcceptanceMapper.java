package cn.iocoder.yudao.module.pm.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.pm.controller.admin.acceptance.vo.PmAcceptancePageReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmAcceptanceDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PmAcceptanceMapper extends BaseMapperX<PmAcceptanceDO> {

    default PageResult<PmAcceptanceDO> selectPage(PmAcceptancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PmAcceptanceDO>()
                .eqIfPresent(PmAcceptanceDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(PmAcceptanceDO::getResult, reqVO.getResult())
                .eqIfPresent(PmAcceptanceDO::getReviewer, reqVO.getReviewer())
                .betweenIfPresent(PmAcceptanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PmAcceptanceDO::getId));
    }

    default PmAcceptanceDO selectByTaskId(String taskId) {
        return selectOne(PmAcceptanceDO::getTaskId, taskId);
    }

}
