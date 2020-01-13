package power.desigh;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author jie.zhang
 * @create_time 2019/8/12 11:17
 * @updater
 * @update_time
 **/
@Data
public class OrderDetail {
    private int id; // 详细订单 ID
    private int orderId;// 主订单 ID
    private Merchandise merchandise; // 商品详情
    private BigDecimal payMoney; // 支付单价
}
