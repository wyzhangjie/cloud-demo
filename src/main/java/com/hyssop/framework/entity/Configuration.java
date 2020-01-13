package com.hyssop.framework.entity;

import com.hyssop.framework.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 全局配置表
 * </p>
 *
 * @author lonyee
 * @since 2019-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Configuration extends SuperEntity<Configuration> {

    /**
     * 版本号
     */
    private Long version;

    /**
     * 配置id
     */
    private String configId;

    /**
     * 配置名
     */
    private String name;

    /**
     * 配置值
     */
    private String value;

    /**
     * 配置类型，如 baseFlags，FeatureFlags，otherFlags
     */
    private String configType;


}
