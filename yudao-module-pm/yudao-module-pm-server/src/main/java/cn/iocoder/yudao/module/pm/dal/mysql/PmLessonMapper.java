package cn.iocoder.yudao.module.pm.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.pm.controller.admin.lesson.vo.PmLessonPageReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmLessonDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PmLessonMapper extends BaseMapperX<PmLessonDO> {

    default PageResult<PmLessonDO> selectPage(PmLessonPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PmLessonDO>()
                .eqIfPresent(PmLessonDO::getType, reqVO.getType())
                .likeIfPresent(PmLessonDO::getTags, reqVO.getTags())
                .likeIfPresent(PmLessonDO::getTitle, reqVO.getTitle())
                .betweenIfPresent(PmLessonDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PmLessonDO::getReferenceCount)
                .orderByDesc(PmLessonDO::getId));
    }

    default List<PmLessonDO> selectListByTags(List<String> tags) {
        return selectList(new LambdaQueryWrapperX<PmLessonDO>()
                .and(w -> {
                    for (String tag : tags) {
                        w.like(PmLessonDO::getTags, tag).or();
                    }
                })
                .orderByDesc(PmLessonDO::getReferenceCount));
    }

}
