package power.desigh;

import java.math.BigDecimal;

/**
 * 支付基本类
 * @Author jie.zhang
 * @create_time 2019/8/12 11:22
 * @updater
 * @update_time
 **/
public class BaseCount implements IBaseCount{

    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        orderDetail.setPayMoney(orderDetail.getMerchandise().getPrice());
        System.out.println(" 商品原单价金额为：" +  orderDetail.getPayMoney());

        return orderDetail.getPayMoney();
    }

}
