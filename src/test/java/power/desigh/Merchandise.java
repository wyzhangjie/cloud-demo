package power.desigh;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author jie.zhang
 * @create_time 2019/8/12 11:17
 * @updater
 * @update_time
 **/
@Data
public class Merchandise {

    private String sku;// 商品 SKU
    private String name; // 商品名称
    private BigDecimal price; // 商品单价
    private Map<PromotionType, SupportPromotions> supportPromotions; // 支持促销类型
}
