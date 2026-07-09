package cn.iocoder.yudao.module.task.api.card;

import cn.iocoder.yudao.module.task.api.card.dto.TaskCardRespDTO;

import java.util.List;

/**
 * 任务卡 RPC 接口 — 供 pm/agent 模块跨模块调用
 */
public interface TaskCardApi {

    /**
     * 查询单张任务卡
     */
    TaskCardRespDTO getCard(String id);

    /**
     * 批量查询任务卡
     */
    List<TaskCardRespDTO> getCardListByIds(List<String> ids);

    /**
     * 校验任务卡是否满足门禁条件
     */
    boolean validateCard(String id);
}
