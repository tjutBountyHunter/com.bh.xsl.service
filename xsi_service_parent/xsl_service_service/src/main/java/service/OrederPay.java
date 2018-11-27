package service;

public interface OrederPay {
    XslResult payOrder(Integer userId, Integer taskId, String password);
}
