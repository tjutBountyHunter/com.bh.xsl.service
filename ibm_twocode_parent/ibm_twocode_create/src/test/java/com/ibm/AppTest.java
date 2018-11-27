package com.ibm;


import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.B2cBiometricbasedPaymentPayRequestV1;
import com.icbc.api.response.B2cBiometricbasedPaymentPayResponseV1;
import contentApi.appid_key;
import org.junit.Test;

public class AppTest {
    @Test
    public void test(){
        DefaultIcbcClient client = new DefaultIcbcClient(appid_key.APP_ID,IcbcConstants.SIGN_TYPE_RSA,appid_key.MY_PRIVATE_KEY,IcbcConstants.CHARSET_UTF8,IcbcConstants.FORMAT_JSON,appid_key.APIGW_PUBLIC_KEY, IcbcConstants.ENCRYPT_TYPE_AES,AES_KEY,null, null);
        B2cBiometricbasedPaymentPayRequestV1 request = new B2cBiometricbasedPaymentPayRequestV1();
        request.setServiceUrl("https://ip:port/api/biometricbased/payment/pay/V1");

        B2cBiometricbasedPaymentPayRequestV1.B2cBiometricbasedPaymentPayRequestV1Biz bizContent = new B2cBiometricbasedPaymentPayRequestV1.B2cBiometricbasedPaymentPayRequestV1Biz();
        bizContent.setConsumerId("XX商城编号YYY售卖机");

       request.setBizContent(bizContent);

        B2cBiometricbasedPaymentPayResponseV1 response;
        try {
              response = client.execute(request, "msgId");
              if (response.isSuccess()) {
                     // 业务成功处理
                  String orderId = response.getOrderId();
                  System.out.println(orderId);
              } else {
                       // 失败
                  System.out.println(response.getOrderId()+"失败");
              }
            } catch (IcbcApiException e) {
                e.printStackTrace();
            }
    }
}
