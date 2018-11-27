package service;

import java.math.BigDecimal;

public interface OrderPayMq {
    public void orderPay(BigDecimal money);

    public void orderOut(BigDecimal money);
}
