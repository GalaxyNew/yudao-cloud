package cn.iocoder.yudao.module.pm.api.acceptance;

import cn.iocoder.yudao.module.pm.api.acceptance.dto.PmAcceptanceRespDTO;

/**
 * 验收 RPC 接口 — 供 task 等模块跨模块调用
 */
public interface PmAcceptanceApi {

    /**
     * 按任务卡 ID 查询验收结果
     */
    PmAcceptanceRespDTO getByTaskId(String taskId);

}
