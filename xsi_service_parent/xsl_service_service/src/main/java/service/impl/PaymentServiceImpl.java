package service.impl;

import com.icbc.api.request.QrcodePayRequestV2;
import com.icbc.api.response.QrcodePayResponseV2;
import contentApi.timeDate;
import contentApi.timeDay;
import dao.JedisClient;
import mapper.IbmCommercialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.IbmCommercial;
import pojo.IbmCommercialExample;
import service.IbmResult;
import service.PaymentService;

import java.util.List;

import static contentApi.appid_key.api_accection;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private IbmCommercialMapper ibmCommercialMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_USER_QRCODE_KEY}")
    private String REDIS_USER_QRCODE_KEY;
    @Value("${Login_QRCODE_EXPIRE}")
    private Integer Login_QRCODE_EXPIRE;

    @Override
    public IbmResult doPay(Integer userId) {
        QrcodePayRequestV2.QrcodePayRequestV2Biz bizContent = new QrcodePayRequestV2.QrcodePayRequestV2Biz();
        QrcodePayRequestV2 request = new QrcodePayRequestV2();
        request.setServiceUrl("https://apisandbox.dccnet.com.cn/api/qrcode/V2/pay");
        IbmCommercialExample example = new IbmCommercialExample();
        IbmCommercialExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);
        criteria.andUseridEqualTo(userId);
        List<IbmCommercial> list = ibmCommercialMapper.selectByExample(example);
        IbmCommercial ibmCommercial = list.get(0);
        //商户扫描上送，客户的付款码
        bizContent.setQrCode(jedisClient.get(REDIS_USER_QRCODE_KEY));
        //商户线下档案编号(特约商户12位，特约部门15位)
        bizContent.setMerId(ibmCommercial.getCommercialcode());
        //商户系统订单号
        bizContent.setOutTradeNo(ibmCommercial.getOrdercode());
        //	订单金额，单位：分
        bizContent.setOrderAmt(ibmCommercial.getMoney());
        bizContent.setTradeDate(timeDate.timedate());
        bizContent.setTradeTime(timeDay.timeDay());
        request.setBizContent(bizContent);
        QrcodePayResponseV2 response;
        try {
            response = api_accection().execute(request, "msgId");
            if (response.isSuccess()) {
                // 业务成功处理
                return IbmResult.ok(response.getReturnCode());
            } else {
                // 失败
                return IbmResult.build(response.getReturnCode(), response.getReturnMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return IbmResult.build(500, "服务器异常");
        }
    }
}
