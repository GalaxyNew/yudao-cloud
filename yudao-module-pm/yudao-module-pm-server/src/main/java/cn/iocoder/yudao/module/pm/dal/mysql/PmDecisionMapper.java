package cn.iocoder.yudao.module.pm.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.pm.controller.admin.decision.vo.PmDecisionPageReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmDecisionDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PmDecisionMapper extends BaseMapperX<PmDecisionDO> {

    default PageResult<PmDecisionDO> selectPage(PmDecisionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PmDecisionDO>()
                .eqIfPresent(PmDecisionDO::getProjectId, reqVO.getProjectId())
                .eqIfPresent(PmDecisionDO::getStatus, reqVO.getStatus())
                .likeIfPresent(PmDecisionDO::getTitle, reqVO.getTitle())
                .betweenIfPresent(PmDecisionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PmDecisionDO::getId));
    }

}
