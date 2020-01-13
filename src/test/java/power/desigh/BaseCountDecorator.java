package power.desigh;

import java.math.BigDecimal;

/**
 * @Author jie.zhang
 * @create_time 2019/8/12 11:31
 * @updater
 * @update_time
 **/
public abstract class BaseCountDecorator implements IBaseCount {
    private IBaseCount count;

    public BaseCountDecorator(IBaseCount count) {
        this.count = count;
    }

    public BigDecimal countPayMoney(OrderDetail orderDetail) {


        BigDecimal payTotalMoney = new BigDecimal(0);

        if(count!=null) {
            payTotalMoney = count.countPayMoney(orderDetail);
        }
        return payTotalMoney;
    }
}