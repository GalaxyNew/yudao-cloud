package cn.iocoder.yudao.module.pm.service.lesson;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pm.controller.admin.lesson.vo.PmLessonCreateReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.lesson.vo.PmLessonPageReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.lesson.vo.PmLessonUpdateReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmLessonDO;

import java.util.List;

/**
 * 经验库 Service 接口
 */
public interface PmLessonService {

    Long createLesson(PmLessonCreateReqVO createReqVO);

    void updateLesson(PmLessonUpdateReqVO updateReqVO);

    void deleteLesson(Long id);

    PmLessonDO getLesson(Long id);

    PageResult<PmLessonDO> pageLesson(PmLessonPageReqVO reqVO);

    List<PmLessonDO> listByTags(List<String> tags);

}
