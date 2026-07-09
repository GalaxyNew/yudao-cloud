package cn.iocoder.yudao.module.pm.api.acceptance;

import cn.iocoder.yudao.module.pm.api.acceptance.dto.PmAcceptanceRespDTO;
import cn.iocoder.yudao.module.pm.dal.dataobject.PmAcceptanceDO;
import cn.iocoder.yudao.module.pm.service.acceptance.PmAcceptanceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 验收 RPC 接口实现（单体模式本地 Bean）
 */
@Service
public class PmAcceptanceApiImpl implements PmAcceptanceApi {

    @Resource
    private PmAcceptanceService pmAcceptanceService;

    @Override
    public PmAcceptanceRespDTO getByTaskId(String taskId) {
        PmAcceptanceDO acceptance = pmAcceptanceService.getByTaskId(taskId);
        if (acceptance == null) {
            return null;
        }
        return new PmAcceptanceRespDTO()
                .setId(acceptance.getId())
                .setTaskId(acceptance.getTaskId())
                .setResult(acceptance.getResult())
                .setVerifyCommand(acceptance.getVerifyCommand())
                .setVerifyOutput(acceptance.getVerifyOutput())
                .setReviewer(acceptance.getReviewer())
                .setComment(acceptance.getComment())
                .setCreateTime(acceptance.getCreateTime());
    }

}
