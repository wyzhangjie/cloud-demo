package com.hyssop.framework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.hyssop.framework.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 机具信息表
 * </p>
 *
 * @author jie.zhang
 * @since 2019-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Terminal extends SuperEntity<Terminal> {

    private static final long serialVersionUID=1L;


    /**
     * 提供者id
     */
    private Long terminalProviderId;

    /**
     * 机具序列号的16进制前6位
     */
    private String posId;

    /**
     * 机具序列号
     */
    private String terminalId;

    /**
     * Y/N绑定状态
     */
    private String bindStatus;

    /**
     * 用户类型（user,business）
     */
    private String userType;

    /**
     * 用户或者商户与id
     */
    private String userId;



}
