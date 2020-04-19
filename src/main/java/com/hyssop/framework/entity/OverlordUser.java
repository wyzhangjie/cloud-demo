package com.hyssop.framework.entity;

import com.hyssop.framework.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 运营后台用户表
 * </p>
 *
 * @author lonyee
 * @since 2019-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OverlordUser extends SuperEntity<OverlordUser> {

    /**
     * 版本号
     */
    private Long version;

    /**
     * 运营id
     */
    private String overlordUserId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码hash值
     */
    private String passwordHash;

    /**
     * 密码salt值
     */
    private String passwordSalt;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 邀请码
     */
    private String referralCode;

}
