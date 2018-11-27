package service;


public interface PaymentService {

    /**
     * 二维码被扫支付
     *
     * @return
     */
    IbmResult doPay(Integer userId);

}
