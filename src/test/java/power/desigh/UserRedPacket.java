package power.desigh;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 红包
 * @Author jie.zhang
 * @create_time 2019/8/12 11:20
 * @updater
 * @update_time
 **/
@Data
public class UserRedPacket {

    private int id; // 红包 ID
    private int userId; // 领取用户 ID
    private String sku; // 商品 SKU
    private BigDecimal redPacket; // 领取红包金额
}
