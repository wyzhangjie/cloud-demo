package algorithm.wrong100;

import com.hyssop.framework.exception.ArgumentsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
public class Exceptions {
    public static ArgumentsException ORDEREXISTS = new ArgumentsException("订单已经存在");
    public static ArgumentsException ORDERHASCANCELED = new ArgumentsException("订单已被取消");

    @GetMapping("wrong")
    public void wrong() {
        try {
            createOrderWrong();
        } catch (Exception ex) {
            log.error("createOrder got error", ex);
        }
        try {
            cancelOrderWrong();
        } catch (Exception ex) {
            log.error("cancelOrder got error", ex);
        }
    }

    private void createOrderWrong() {
        //这里有问题
        throw Exceptions.ORDEREXISTS;
    }

    private void cancelOrderWrong() {
        //这里有问题
        throw Exceptions.ORDEREXISTS;
    }

    public static void main(String[] args) {
        Exceptions exceptions = new Exceptions();
        exceptions.wrong();
     //   System.out.println();
    }
}
