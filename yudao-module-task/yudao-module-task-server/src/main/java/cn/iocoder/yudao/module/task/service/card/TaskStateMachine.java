package cn.iocoder.yudao.module.task.service.card;

import java.util.HashSet;
import java.util.Set;

/**
 * 任务卡状态机
 *
 * 状态流转规则见架构设计附录 A
 */
public class TaskStateMachine {

    /**
     * 合法转换集合：from->to
     */
    private static final Set<String> VALID_TRANSITIONS = new HashSet<>();

    static {
        // DRAFT → STAFFING（提交 HR 配置）
        VALID_TRANSITIONS.add("DRAFT->STAFFING");
        // DRAFT → ASSIGNED（直接派单）
        VALID_TRANSITIONS.add("DRAFT->ASSIGNED");
        // DRAFT → CANCELLED（草稿取消）
        VALID_TRANSITIONS.add("DRAFT->CANCELLED");

        // STAFFING → ASSIGNED（HR 派单完成）
        VALID_TRANSITIONS.add("STAFFING->ASSIGNED");
        // STAFFING → CANCELLED（配置取消）
        VALID_TRANSITIONS.add("STAFFING->CANCELLED");

        // ASSIGNED → IN_PROGRESS（Agent 领单/启动）
        VALID_TRANSITIONS.add("ASSIGNED->IN_PROGRESS");
        // ASSIGNED → CANCELLED（撤销派单）
        VALID_TRANSITIONS.add("ASSIGNED->CANCELLED");

        // IN_PROGRESS → REVIEW（提交验收）
        VALID_TRANSITIONS.add("IN_PROGRESS->REVIEW");
        // IN_PROGRESS → BLOCKED（遇阻）
        VALID_TRANSITIONS.add("IN_PROGRESS->BLOCKED");

        // REVIEW → PASSED（验收通过）
        VALID_TRANSITIONS.add("REVIEW->PASSED");
        // REVIEW → REJECTED（验收驳回）
        VALID_TRANSITIONS.add("REVIEW->REJECTED");

        // REJECTED → IN_PROGRESS（返工）
        VALID_TRANSITIONS.add("REJECTED->IN_PROGRESS");
        // REJECTED → BLOCKED（无法修复）
        VALID_TRANSITIONS.add("REJECTED->BLOCKED");
    }

    /**
     * 校验状态转换是否合法
     *
     * @param from 当前状态
     * @param to   目标状态
     * @return 是否合法
     */
    public static boolean validateTransition(String from, String to) {
        if (from == null || to == null) {
            return false;
        }
        return VALID_TRANSITIONS.contains(from + "->" + to);
    }

    /**
     * 校验并抛出异常
     */
    public static void validateTransitionOrThrow(String from, String to) {
        if (!validateTransition(from, to)) {
            throw new IllegalArgumentException(
                    String.format("非法状态转换: %s → %s，请参考状态机定义", from, to));
        }
    }

}
