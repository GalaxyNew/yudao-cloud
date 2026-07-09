package cn.iocoder.yudao.module.pm.dal.dataobject;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 经验库 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pm_lesson")
public class PmLessonDO extends TenantBaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 任务卡 ID */
    private String taskId;

    /** 类型：SUCCESS/PITFALL/PLAYBOOK */
    private String type;

    /** 标题 */
    private String title;

    /** 内容 */
    private String content;

    /** 标签（逗号分隔） */
    private String tags;

    /** 被引用次数 */
    private Integer referenceCount;

}
