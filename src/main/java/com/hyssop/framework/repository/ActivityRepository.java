package com.hyssop.framework.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyssop.framework.entity.Activity;

/**
 * <p>
 * 活动记录表 Mapper 接口
 * </p>
 *
 * @author jie.zhang
 * @since 2019-10-19
 */
public interface ActivityRepository extends BaseMapper<Activity> {


    Activity getActivityByActivityIdAndSnapshot(Activity activity);



    /**
     * 扣减活动优惠总金额
     * @param id 活动主键id
     * @param discountAmount 该笔订单优惠金额
     * @return int
     * @Author xiao.chen
     * @create_time 2019/10/21 21:10
     *
     **/
    int deductActivityAmount(long id, long discountAmount);

    /**
     * 增加活动优惠总金额
     * @param id 活动主键id
     * @param discountAmount 该笔订单优惠金额
     * @return int
     * @Author xiao.chen
     * @create_time 2019/10/21 21:10
     *
     **/
    int increaseActivityAmount(long id, long discountAmount);

}
