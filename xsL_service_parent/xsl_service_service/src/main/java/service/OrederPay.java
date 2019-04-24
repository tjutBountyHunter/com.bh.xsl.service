package service;

import util.XslResult;

public interface OrederPay {
    XslResult payOrder(Integer userId, Integer taskId, String password);
}
