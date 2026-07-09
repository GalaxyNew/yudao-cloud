package cn.iocoder.yudao.module.pm.dal.mysql;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmDocumentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PmDocumentMapper extends BaseMapperX<PmDocumentDO> {

    default List<PmDocumentDO> selectListByProjectId(Long projectId) {
        return selectList(new LambdaQueryWrapperX<PmDocumentDO>()
                .eq(PmDocumentDO::getProjectId, projectId)
                .orderByDesc(PmDocumentDO::getUpdateTime));
    }

}
