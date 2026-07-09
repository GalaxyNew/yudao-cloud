package cn.iocoder.yudao.module.pm.api.project;

import cn.iocoder.yudao.module.pm.api.project.dto.PmProjectRespDTO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmProjectDO;
import cn.iocoder.yudao.module.pm.service.project.PmProjectService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 项目 RPC 接口实现（单体模式本地 Bean）
 */
@Service
public class PmProjectApiImpl implements PmProjectApi {

    @Resource
    private PmProjectService pmProjectService;

    @Override
    public PmProjectRespDTO getProject(Long id) {
        PmProjectDO project = pmProjectService.getProject(id);
        if (project == null) {
            return null;
        }
        return new PmProjectRespDTO()
                .setId(project.getId())
                .setCode(project.getCode())
                .setName(project.getName())
                .setStatus(project.getStatus())
                .setOwner(project.getOwner())
                .setRepoUrl(project.getRepoUrl())
                .setProgress(project.getProgress())
                .setSummary(project.getSummary())
                .setDocUrl(project.getDocUrl());
    }

}
