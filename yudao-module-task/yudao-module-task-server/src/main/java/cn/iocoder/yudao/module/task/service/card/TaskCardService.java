package cn.iocoder.yudao.module.task.service.card;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.task.controller.admin.card.vo.*;
import cn.iocoder.yudao.module.task.dal.dataobject.TaskCardDO;

import java.util.List;

/**
 * 任务卡 Service 接口
 */
public interface TaskCardService {

    /**
     * 创建任务卡
     */
    String createCard(TaskCardCreateReqVO createReqVO);

    /**
     * 更新任务卡
     */
    void updateCard(TaskCardUpdateReqVO updateReqVO);

    /**
     * 状态流转
     */
    void updateStatus(TaskCardUpdateStatusReqVO reqVO);

    /**
     * 删除任务卡（软删除）
     */
    void deleteCard(String id);

    /**
     * 获取任务卡
     */
    TaskCardDO getCard(String id);

    /**
     * 分页查询
     */
    PageResult<TaskCardDO> pageCard(TaskCardPageReqVO reqVO);

    /**
     * 查询子卡列表
     */
    List<TaskCardDO> listByParent(String parentId);

    /**
     * 派单
     */
    void assign(TaskCardAssignReqVO reqVO);

    /**
     * 领单
     */
    void claim(TaskCardClaimReqVO reqVO);

    /**
     * 提交验收
     */
    void submitReview(String id);

    /**
     * 重试（retries+1，状态回退）
     */
    void retry(String id);

    /**
     * 校验门禁条件
     */
    GateCheckResultVO validateGate(String id);

}
