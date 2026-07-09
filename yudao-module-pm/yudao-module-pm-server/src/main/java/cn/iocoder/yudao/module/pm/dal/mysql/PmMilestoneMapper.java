package cn.iocoder.yudao.module.pm.dal.mysql;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmMilestoneDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PmMilestoneMapper extends BaseMapperX<PmMilestoneDO> {

    default List<PmMilestoneDO> selectListByProjectId(Long projectId) {
        return selectList(new LambdaQueryWrapperX<PmMilestoneDO>()
                .eq(PmMilestoneDO::getProjectId, projectId)
                .orderByAsc(PmMilestoneDO::getStartDate));
    }

}
