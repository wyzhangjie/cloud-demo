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
 * 机具提供者
 * </p>
 *
 * @author jie.zhang
 * @since 2019-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TerminalProvider extends SuperEntity<TerminalProvider> {

    private static final long serialVersionUID=1L;

    /**
     * 提供者名称
     */
    private String name;

    /**
     * 授权码
     */
    private String authorization;

    /**
     * 授权码hash 
     */
    private String authorizationHash;

    /**
     * 盐
     */
    private String salt;

    /**
     * 国家编号
     */
    private String country;



}
