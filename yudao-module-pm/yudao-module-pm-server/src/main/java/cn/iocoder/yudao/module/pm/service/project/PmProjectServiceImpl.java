package cn.iocoder.yudao.module.pm.service.project;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pm.controller.admin.project.vo.*;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmProjectDO;
import cn.iocoder.yudao.module.pm.dal.mysql.PmProjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.pm.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PmProjectServiceImpl implements PmProjectService {

    @Resource
    private PmProjectMapper pmProjectMapper;

    @Override
    public Long createProject(PmProjectCreateReqVO createReqVO) {
        // 校验编号唯一
        if (pmProjectMapper.selectOne(PmProjectDO::getCode, createReqVO.getCode()) != null) {
            throw exception(PROJECT_CODE_EXISTS);
        }
        PmProjectDO project = new PmProjectDO();
        project.setCode(createReqVO.getCode());
        project.setName(createReqVO.getName());
        project.setStatus(createReqVO.getStatus() != null ? createReqVO.getStatus() : "ACTIVE");
        project.setOwner(createReqVO.getOwner());
        project.setRepoUrl(createReqVO.getRepoUrl());
        project.setProgress(createReqVO.getProgress() != null ? createReqVO.getProgress() : 0);
        project.setSummary(createReqVO.getSummary());
        project.setDocUrl(createReqVO.getDocUrl());
        pmProjectMapper.insert(project);
        return project.getId();
    }

    @Override
    public void updateProject(PmProjectUpdateReqVO updateReqVO) {
        validateProjectExists(updateReqVO.getId());
        PmProjectDO update = new PmProjectDO();
        update.setId(updateReqVO.getId());
        update.setCode(updateReqVO.getCode());
        update.setName(updateReqVO.getName());
        update.setStatus(updateReqVO.getStatus());
        update.setOwner(updateReqVO.getOwner());
        update.setRepoUrl(updateReqVO.getRepoUrl());
        update.setProgress(updateReqVO.getProgress());
        update.setSummary(updateReqVO.getSummary());
        update.setDocUrl(updateReqVO.getDocUrl());
        pmProjectMapper.updateById(update);
    }

    @Override
    public void deleteProject(Long id) {
        validateProjectExists(id);
        pmProjectMapper.deleteById(id);
    }

    @Override
    public PmProjectDO getProject(Long id) {
        return pmProjectMapper.selectById(id);
    }

    @Override
    public PageResult<PmProjectDO> pageProject(PmProjectPageReqVO reqVO) {
        return pmProjectMapper.selectPage(reqVO);
    }

    @Override
    public List<PmProjectSimpleRespVO> getSimpleList() {
        List<PmProjectDO> list = pmProjectMapper.selectListSimple();
        return list.stream().map(d -> {
            PmProjectSimpleRespVO vo = new PmProjectSimpleRespVO();
            vo.setId(d.getId());
            vo.setCode(d.getCode());
            vo.setName(d.getName());
            return vo;
        }).toList();
    }

    private void validateProjectExists(Long id) {
        if (pmProjectMapper.selectById(id) == null) {
            throw exception(PROJECT_NOT_EXISTS);
        }
    }

}
