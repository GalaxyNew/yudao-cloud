package cn.iocoder.yudao.module.pm.service.lesson;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pm.controller.admin.lesson.vo.PmLessonCreateReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.lesson.vo.PmLessonPageReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.lesson.vo.PmLessonUpdateReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmLessonDO;
import cn.iocoder.yudao.module.pm.dal.mysql.PmLessonMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.pm.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PmLessonServiceImpl implements PmLessonService {

    @Resource
    private PmLessonMapper pmLessonMapper;

    @Override
    public Long createLesson(PmLessonCreateReqVO createReqVO) {
        PmLessonDO lesson = new PmLessonDO();
        lesson.setTaskId(createReqVO.getTaskId());
        lesson.setType(createReqVO.getType());
        lesson.setTitle(createReqVO.getTitle());
        lesson.setContent(createReqVO.getContent());
        lesson.setTags(createReqVO.getTags());
        lesson.setReferenceCount(0);
        pmLessonMapper.insert(lesson);
        return lesson.getId();
    }

    @Override
    public void updateLesson(PmLessonUpdateReqVO updateReqVO) {
        validateLessonExists(updateReqVO.getId());
        PmLessonDO update = new PmLessonDO();
        update.setId(updateReqVO.getId());
        update.setTaskId(updateReqVO.getTaskId());
        update.setType(updateReqVO.getType());
        update.setTitle(updateReqVO.getTitle());
        update.setContent(updateReqVO.getContent());
        update.setTags(updateReqVO.getTags());
        pmLessonMapper.updateById(update);
    }

    @Override
    public void deleteLesson(Long id) {
        validateLessonExists(id);
        pmLessonMapper.deleteById(id);
    }

    @Override
    public PmLessonDO getLesson(Long id) {
        return pmLessonMapper.selectById(id);
    }

    @Override
    public PageResult<PmLessonDO> pageLesson(PmLessonPageReqVO reqVO) {
        return pmLessonMapper.selectPage(reqVO);
    }

    @Override
    public List<PmLessonDO> listByTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return List.of();
        }
        return pmLessonMapper.selectListByTags(tags);
    }

    private void validateLessonExists(Long id) {
        if (pmLessonMapper.selectById(id) == null) {
            throw exception(LESSON_NOT_EXISTS);
        }
    }

}
