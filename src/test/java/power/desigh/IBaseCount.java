package power.desigh;

import java.math.BigDecimal;

/**
 * 计算支付金额接口类
 * @Author jie.zhang
 * @create_time 2019/8/12 11:22
 * @updater
 * @update_time
 **/
public interface IBaseCount {

    BigDecimal countPayMoney(OrderDetail orderDetail);

}
