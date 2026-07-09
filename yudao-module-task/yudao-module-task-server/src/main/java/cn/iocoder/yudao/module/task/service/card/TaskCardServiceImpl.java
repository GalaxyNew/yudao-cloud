package cn.iocoder.yudao.module.task.service.card;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.task.controller.admin.card.vo.*;
import cn.iocoder.yudao.module.task.dal.dataobject.TaskCardDO;
import cn.iocoder.yudao.module.task.dal.mysql.TaskCardMapper;
import cn.iocoder.yudao.module.task.dal.mysql.TaskEvidenceMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.task.enums.ErrorCodeConstants.*;

/**
 * 任务卡 Service 实现类
 */
@Service
@Validated
public class TaskCardServiceImpl implements TaskCardService {

    @Resource
    private TaskCardMapper taskCardMapper;

    @Resource
    private TaskEvidenceMapper taskEvidenceMapper;

    @Override
    public String createCard(TaskCardCreateReqVO createReqVO) {
        TaskCardDO card = new TaskCardDO();
        if (createReqVO.getId() != null && !createReqVO.getId().isEmpty()) {
            card.setId(createReqVO.getId());
        } else {
            card.setId(generateCardId());
        }
        card.setTitle(createReqVO.getTitle());
        card.setStatus("DRAFT");
        card.setPriority(createReqVO.getPriority() != null ? createReqVO.getPriority() : "P2");
        card.setLevel(createReqVO.getLevel() != null ? createReqVO.getLevel() : "L2");
        card.setTags(createReqVO.getTags());
        card.setOwner(createReqVO.getOwner());
        card.setCollaborators(createReqVO.getCollaborators());
        card.setParentId(createReqVO.getParentId());
        card.setGithubIssue(createReqVO.getGithubIssue());
        card.setEvidenceLevel(createReqVO.getEvidenceLevel() != null ? createReqVO.getEvidenceLevel() : "E2");
        card.setModel(createReqVO.getModel());
        card.setDeadline(createReqVO.getDeadline());
        card.setRetries(0);
        card.setProcessStage(createReqVO.getProcessStage());
        card.setDocRef(createReqVO.getDocRef());
        card.setSkillTags(createReqVO.getSkillTags());
        card.setDifficulty(createReqVO.getDifficulty());
        card.setScopeSummary(createReqVO.getScopeSummary());
        taskCardMapper.insert(card);
        return card.getId();
    }

    @Override
    public void updateCard(TaskCardUpdateReqVO updateReqVO) {
        TaskCardDO card = getCardRequired(updateReqVO.getId());
        if (!"DRAFT".equals(card.getStatus())
                && !"STAFFING".equals(card.getStatus())
                && !"REJECTED".equals(card.getStatus())) {
            throw exception(CARD_STATUS_NOT_ALLOW_UPDATE);
        }
        card.setTitle(updateReqVO.getTitle());
        card.setPriority(updateReqVO.getPriority());
        card.setLevel(updateReqVO.getLevel());
        card.setTags(updateReqVO.getTags());
        card.setCollaborators(updateReqVO.getCollaborators());
        card.setGithubIssue(updateReqVO.getGithubIssue());
        card.setEvidenceLevel(updateReqVO.getEvidenceLevel());
        card.setModel(updateReqVO.getModel());
        card.setDeadline(updateReqVO.getDeadline());
        card.setProcessStage(updateReqVO.getProcessStage());
        card.setDocRef(updateReqVO.getDocRef());
        card.setSkillTags(updateReqVO.getSkillTags());
        card.setDifficulty(updateReqVO.getDifficulty());
        card.setScopeSummary(updateReqVO.getScopeSummary());
        taskCardMapper.updateById(card);
    }

    @Override
    public void updateStatus(TaskCardUpdateStatusReqVO reqVO) {
        TaskCardDO card = getCardRequired(reqVO.getId());
        String from = card.getStatus();
        String to = reqVO.getStatus();

        TaskStateMachine.validateTransitionOrThrow(from, to);

        if ("REVIEW".equals(to) && Boolean.TRUE.equals(reqVO.getGateCheck())) {
            GateCheckResultVO gateResult = validateGate(reqVO.getId());
            if (!gateResult.isPassed()) {
                throw exception(CARD_GATE_CHECK_FAILED, String.join("、", gateResult.getMissingItems()));
            }
        }

        if ("IN_PROGRESS".equals(to) && "REJECTED".equals(from)) {
            card.setRetries(card.getRetries() + 1);
        }

        card.setStatus(to);
        taskCardMapper.updateById(card);
    }

    @Override
    public void deleteCard(String id) {
        TaskCardDO card = getCardRequired(id);
        String status = card.getStatus();
        if (!("DRAFT".equals(status)
                || "CANCELLED".equals(status)
                || "BLOCKED".equals(status))) {
            throw exception(CARD_STATUS_NOT_ALLOW_DELETE);
        }
        taskCardMapper.deleteById(id);
    }

    @Override
    public TaskCardDO getCard(String id) {
        return taskCardMapper.selectById(id);
    }

    @Override
    public PageResult<TaskCardDO> pageCard(TaskCardPageReqVO reqVO) {
        return taskCardMapper.selectPage(reqVO);
    }

    @Override
    public List<TaskCardDO> listByParent(String parentId) {
        return taskCardMapper.selectListByParentId(parentId);
    }

    @Override
    public void assign(TaskCardAssignReqVO reqVO) {
        TaskCardDO card = getCardRequired(reqVO.getId());
        String currentStatus = card.getStatus();
        if (!"DRAFT".equals(currentStatus) && !"STAFFING".equals(currentStatus)) {
            throw exception(CARD_STATUS_NOT_ALLOW_ASSIGN);
        }
        card.setOwner(reqVO.getOwner());
        card.setModel(reqVO.getModel());
        card.setStatus("ASSIGNED");
        taskCardMapper.updateById(card);
    }

    @Override
    public void claim(TaskCardClaimReqVO reqVO) {
        TaskCardDO card = getCardRequired(reqVO.getId());
        if (!"ASSIGNED".equals(card.getStatus())) {
            throw exception(CARD_STATUS_NOT_ALLOW_CLAIM);
        }
        card.setOwner(reqVO.getAgentId());
        card.setStatus("IN_PROGRESS");
        taskCardMapper.updateById(card);
    }

    @Override
    public void submitReview(String id) {
        TaskCardDO card = getCardRequired(id);
        if (!"IN_PROGRESS".equals(card.getStatus())) {
            throw exception(CARD_STATUS_NOT_ALLOW_REVIEW);
        }
        GateCheckResultVO gateResult = validateGate(id);
        if (!gateResult.isPassed()) {
            throw exception(CARD_GATE_CHECK_FAILED, String.join("、", gateResult.getMissingItems()));
        }
        card.setStatus("REVIEW");
        taskCardMapper.updateById(card);
    }

    @Override
    public void retry(String id) {
        TaskCardDO card = getCardRequired(id);
        if (!"REJECTED".equals(card.getStatus())) {
            throw exception(CARD_STATUS_NOT_ALLOW_RETRY);
        }
        card.setRetries(card.getRetries() + 1);
        card.setStatus("IN_PROGRESS");
        taskCardMapper.updateById(card);
    }

    @Override
    public GateCheckResultVO validateGate(String id) {
        TaskCardDO card = getCardRequired(id);
        List<String> missingItems = new ArrayList<>();

        if (card.getGithubIssue() == null || card.getGithubIssue().isEmpty()) {
            missingItems.add("github_issue 不能为空");
        }
        if (card.getDocRef() == null || card.getDocRef().isEmpty()) {
            missingItems.add("doc_ref 不能为空");
        }
        if (card.getProcessStage() == null || card.getProcessStage() < 1) {
            missingItems.add("process_stage 不能为空");
        }
        if (card.getOwner() == null || card.getOwner().isEmpty()) {
            missingItems.add("owner 不能为空");
        }

        if (missingItems.isEmpty()) {
            return GateCheckResultVO.pass();
        }
        return GateCheckResultVO.fail(missingItems);
    }

    private TaskCardDO getCardRequired(String id) {
        TaskCardDO card = taskCardMapper.selectById(id);
        if (card == null) {
            throw exception(CARD_NOT_EXISTS);
        }
        return card;
    }

    private String generateCardId() {
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<TaskCardDO> todayCards = taskCardMapper.selectList(
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<TaskCardDO>()
                        .likeRight(TaskCardDO::getId, "T" + dateStr)
                        .orderByDesc(TaskCardDO::getId)
                        .last("LIMIT 1"));
        int seq = 1;
        if (todayCards != null && !todayCards.isEmpty()) {
            String lastId = todayCards.get(0).getId();
            try {
                String[] parts = lastId.split("-");
                if (parts.length >= 2) {
                    seq = Integer.parseInt(parts[parts.length - 1]) + 1;
                }
            } catch (NumberFormatException ignored) {
            }
        }
        return "T" + dateStr + "-" + String.format("%02d", seq);
    }

}
