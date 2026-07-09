package cn.iocoder.yudao.module.pm.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.pm.controller.admin.project.vo.PmProjectPageReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmProjectDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PmProjectMapper extends BaseMapperX<PmProjectDO> {

    default PageResult<PmProjectDO> selectPage(PmProjectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PmProjectDO>()
                .likeIfPresent(PmProjectDO::getCode, reqVO.getCode())
                .likeIfPresent(PmProjectDO::getName, reqVO.getName())
                .eqIfPresent(PmProjectDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PmProjectDO::getOwner, reqVO.getOwner())
                .betweenIfPresent(PmProjectDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PmProjectDO::getId));
    }

    default List<PmProjectDO> selectListSimple() {
        return selectList(new LambdaQueryWrapperX<PmProjectDO>()
                .select(PmProjectDO::getId, PmProjectDO::getCode, PmProjectDO::getName));
    }

}
