package cn.iocoder.yudao.module.pm.api.lesson;

import cn.iocoder.yudao.module.pm.api.lesson.dto.PmLessonRespDTO;

import java.util.List;

/**
 * 经验库 RPC 接口 — 供 agent 等模块跨模块调用
 */
public interface PmLessonApi {

    /**
     * 按标签检索经验卡
     */
    List<PmLessonRespDTO> listByTags(List<String> tags);

}
