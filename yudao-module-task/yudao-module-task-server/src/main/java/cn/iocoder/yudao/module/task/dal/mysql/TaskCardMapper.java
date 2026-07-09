package cn.iocoder.yudao.module.task.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.task.controller.admin.card.vo.TaskCardPageReqVO;
import cn.iocoder.yudao.module.task.dal.dataobject.TaskCardDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 任务卡 Mapper
 */
@Mapper
public interface TaskCardMapper extends BaseMapperX<TaskCardDO> {

    default PageResult<TaskCardDO> selectPage(TaskCardPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskCardDO>()
                .likeIfPresent(TaskCardDO::getTitle, reqVO.getTitle())
                .eqIfPresent(TaskCardDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TaskCardDO::getOwner, reqVO.getOwner())
                .eqIfPresent(TaskCardDO::getPriority, reqVO.getPriority())
                .eqIfPresent(TaskCardDO::getParentId, reqVO.getParentId())
                .betweenIfPresent(TaskCardDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskCardDO::getId));
    }

    default List<TaskCardDO> selectListByParentId(String parentId) {
        return selectList(TaskCardDO::getParentId, parentId);
    }

}
