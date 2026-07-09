package cn.iocoder.yudao.module.pm.api.project;

import cn.iocoder.yudao.module.pm.api.project.dto.PmProjectRespDTO;

/**
 * 项目 RPC 接口 — 供其他模块跨模块调用
 */
public interface PmProjectApi {

    /**
     * 查询项目基本信息
     */
    PmProjectRespDTO getProject(Long id);

}
