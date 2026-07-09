package cn.iocoder.yudao.module.pm.service.project;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pm.controller.admin.project.vo.*;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmProjectDO;

import java.util.List;

/**
 * 项目 Service 接口
 */
public interface PmProjectService {

    Long createProject(PmProjectCreateReqVO createReqVO);

    void updateProject(PmProjectUpdateReqVO updateReqVO);

    void deleteProject(Long id);

    PmProjectDO getProject(Long id);

    PageResult<PmProjectDO> pageProject(PmProjectPageReqVO reqVO);

    List<PmProjectSimpleRespVO> getSimpleList();

}
