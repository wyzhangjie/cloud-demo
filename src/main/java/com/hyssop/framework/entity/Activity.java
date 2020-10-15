package com.hyssop.framework.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.hyssop.framework.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 活动记录表
 * </p>
 *
 * @author jie.zhang
 * @since 2019-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Activity extends SuperEntity<Activity> {

    /**
     * 活动id
     */
    private String activityId;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动快照
     */
    private String snapshot;

    /**
     * 版本（乐观锁）
     */
    @TableField
    @Version
    private long version ;

    private String serviceType;

    private String payChannel;

    private String businessType;

    /**
     * 活动类型
     */
    private String activityType;

    /**
     * 活动开始时间
     */
    private LocalDateTime beginTime;

    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;

    /**
     * 创建、操作人
     */
    private String overloadUserId;

    private String merchantId;

    /**
     * 该活动总金额
     */
    private Long totalAmount;

    /**
     * 该活动剩余金额
     */
    private Long leftAmount;
    /**
     * 该活动已用金额
     */
    private Long usedAmount;
    /**
     * 该活动最低的活动剩余金额
     */
    private Long minAmount;

    /**
     * 活动 Y已删除 N删除
     */
    private String deleted;

    /**
     * 活动是否启用 Y启用 N不启用
     */
    private String status;

    /**
     * ramark
     */
    private String remark;

    /**
     * 活动限制的服务商集合 默认是all added 20200116
     */
    private String serviceIds;

}
