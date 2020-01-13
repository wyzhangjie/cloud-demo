package com.hyssop.framework.entity;

import com.hyssop.framework.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 运营邀请用户表
 * </p>
 *
 * @author lonyee
 * @since 2019-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OverlordInvites extends SuperEntity<OverlordInvites> {

    /**
     * 版本号
     */
    private Long version;

    /**
     * 运营id(邀请人id)
     */
    private String overlordUserId;

    /**
     * 被邀请人邮箱
     */
    private String invitedEmail;

    /**
     * 邀请token
     */
    private String token;

    /**
     * 邀请是否过期 Y/N 
     */
    private String expired;

}
