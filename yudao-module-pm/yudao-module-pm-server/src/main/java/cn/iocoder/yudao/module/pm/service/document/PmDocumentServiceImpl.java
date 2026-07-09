package cn.iocoder.yudao.module.pm.service.document;

import cn.iocoder.yudao.module.pm.controller.admin.document.vo.PmDocumentCreateReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.document.vo.PmDocumentUpdateReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmDocumentDO;
import cn.iocoder.yudao.module.pm.dal.mysql.PmDocumentMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.pm.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PmDocumentServiceImpl implements PmDocumentService {

    @Resource
    private PmDocumentMapper pmDocumentMapper;

    @Override
    public Long createDocument(PmDocumentCreateReqVO createReqVO) {
        PmDocumentDO document = new PmDocumentDO();
        document.setProjectId(createReqVO.getProjectId());
        document.setTitle(createReqVO.getTitle());
        document.setDocType(createReqVO.getDocType());
        document.setFileToken(createReqVO.getFileToken());
        document.setRepoPath(createReqVO.getRepoPath());
        document.setVersion(createReqVO.getVersion());
        document.setApprovalStatus(createReqVO.getApprovalStatus() != null ? createReqVO.getApprovalStatus() : "DRAFT");
        pmDocumentMapper.insert(document);
        return document.getId();
    }

    @Override
    public void updateDocument(PmDocumentUpdateReqVO updateReqVO) {
        validateDocumentExists(updateReqVO.getId());
        PmDocumentDO update = new PmDocumentDO();
        update.setId(updateReqVO.getId());
        update.setProjectId(updateReqVO.getProjectId());
        update.setTitle(updateReqVO.getTitle());
        update.setDocType(updateReqVO.getDocType());
        update.setFileToken(updateReqVO.getFileToken());
        update.setRepoPath(updateReqVO.getRepoPath());
        update.setVersion(updateReqVO.getVersion());
        update.setApprovalStatus(updateReqVO.getApprovalStatus());
        pmDocumentMapper.updateById(update);
    }

    @Override
    public void deleteDocument(Long id) {
        validateDocumentExists(id);
        pmDocumentMapper.deleteById(id);
    }

    @Override
    public PmDocumentDO getDocument(Long id) {
        return pmDocumentMapper.selectById(id);
    }

    @Override
    public List<PmDocumentDO> listByProjectId(Long projectId) {
        return pmDocumentMapper.selectListByProjectId(projectId);
    }

    private void validateDocumentExists(Long id) {
        if (pmDocumentMapper.selectById(id) == null) {
            throw exception(DOCUMENT_NOT_EXISTS);
        }
    }

}
