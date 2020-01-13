package power.desigh;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author jie.zhang
 * @create_time 2019/8/12 11:16
 * @updater
 * @update_time
 **/
@Data
public class Order {

    private int id; // 订单 ID
    private String orderNo; // 订单号
    private BigDecimal totalPayMoney; // 总支付金额
    private List<OrderDetail> list; // 详细订单列表
}
