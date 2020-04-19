package com.hyssop.framework.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName SuperEntity
 * @Description 实体基础类
 * @Author xiao.chen
 * @Date 2019/8/7
 **/
@Data
public class SuperEntity<T extends Model> extends Model {

    @TableId(value = "id", type = IdType.AUTO)
    private long id;

    /** 创建时间 */
    @TableField
    private LocalDateTime createTime ;

    /** 证书类型*/
    @TableField
    private LocalDateTime  updateTime ;


}
