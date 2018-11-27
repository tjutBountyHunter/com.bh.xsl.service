package controller;

import com.sun.org.glassfish.external.probe.provider.annotations.ProbeParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IbmResult;
import service.OrederPay;
import service.PaymentService;
import service.XslResult;

/**
 * 被扫码支付controller
 */
@Controller
@RequestMapping("/ibm")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrederPay orederPay;

    @RequestMapping("/qrcodepay/{userId}")
    @ResponseBody
    public IbmResult doPay(@PathVariable Integer userId) {
        IbmResult ibmResult = null;
        try {
            ibmResult = paymentService.doPay(userId);
            return ibmResult;
        } catch (Exception e) {
            e.printStackTrace();
            return IbmResult.build(500, "服务器异常");
        }
    }

    @RequestMapping("/orderPay")
    @ResponseBody
    public XslResult orderPay(@ProbeParam("userId") Integer userId, @ProbeParam("taskId") Integer taskId, @ProbeParam("password") String password) {
        XslResult xslResult = orederPay.payOrder(userId, taskId, password);
        return xslResult;
    }
}
