package cn.iocoder.yudao.module.pm.service.document;

import cn.iocoder.yudao.module.pm.controller.admin.document.vo.PmDocumentCreateReqVO;
import cn.iocoder.yudao.module.pm.controller.admin.document.vo.PmDocumentUpdateReqVO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmDocumentDO;

import java.util.List;

/**
 * 项目文档 Service 接口
 */
public interface PmDocumentService {

    Long createDocument(PmDocumentCreateReqVO createReqVO);

    void updateDocument(PmDocumentUpdateReqVO updateReqVO);

    void deleteDocument(Long id);

    PmDocumentDO getDocument(Long id);

    List<PmDocumentDO> listByProjectId(Long projectId);

}
