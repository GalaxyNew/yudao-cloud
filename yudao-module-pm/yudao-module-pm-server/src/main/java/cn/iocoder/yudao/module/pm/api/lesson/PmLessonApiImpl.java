package cn.iocoder.yudao.module.pm.api.lesson;

import cn.iocoder.yudao.module.pm.api.lesson.dto.PmLessonRespDTO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmLessonDO;
import cn.iocoder.yudao.module.pm.service.lesson.PmLessonService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 经验库 RPC 接口实现（单体模式本地 Bean）
 */
@Service
public class PmLessonApiImpl implements PmLessonApi {

    @Resource
    private PmLessonService pmLessonService;

    @Override
    public List<PmLessonRespDTO> listByTags(List<String> tags) {
        List<PmLessonDO> list = pmLessonService.listByTags(tags);
        return list.stream().map(d -> new PmLessonRespDTO()
                .setId(d.getId())
                .setTaskId(d.getTaskId())
                .setType(d.getType())
                .setTitle(d.getTitle())
                .setContent(d.getContent())
                .setTags(d.getTags())
                .setReferenceCount(d.getReferenceCount())
        ).toList();
    }

}
