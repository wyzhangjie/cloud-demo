package power.desigh;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 优惠券
 * @Author jie.zhang
 * @create_time 2019/8/12 11:20
 * @updater
 * @update_time
 **/
@Data
public class UserCoupon {

    private int id; // 优惠券 ID
    private int userId; // 领取优惠券用户 ID
    private String sku; // 商品 SKU
    private BigDecimal coupon; // 优惠金额
}