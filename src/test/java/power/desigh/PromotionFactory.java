package power.desigh;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author jie.zhang
 * @create_time 2019/8/12 11:35
 * @updater
 * @update_time
 **/
public class PromotionFactory {
    public static BigDecimal getPayMoney(OrderDetail orderDetail) {

        //获取给商品设定的促销类型
        Map<PromotionType, SupportPromotions> supportPromotionslist = orderDetail.getMerchandise().getSupportPromotions();

        //初始化计算类
        IBaseCount baseCount = new BaseCount();
        if (supportPromotionslist != null && supportPromotionslist.size() > 0) {
            for (PromotionType promotionType : supportPromotionslist.keySet()) {//遍历设置的促销类型，通过装饰器组合促销类型
                baseCount = protmotion(supportPromotionslist.get(promotionType), baseCount);
            }
        }
        return baseCount.countPayMoney(orderDetail);
    }

    /**
     * 组合促销类型
     *
     * @param supportPromotions
     * @param baseCount
     * @return
     */
    private static IBaseCount protmotion(SupportPromotions supportPromotions, IBaseCount baseCount) {
        if (supportPromotions.getPromotionType() == PromotionType.COUPON) {
            baseCount = new CouponDecorator(baseCount);
        } else if (supportPromotions.getPromotionType() == PromotionType.REDPACKED) {
            baseCount = new RedPacketDecorator(baseCount);
        }
        return baseCount;
    }
}