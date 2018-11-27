package service.impl;

import com.icbc.api.request.QrcodeGenerateRequestV2;
import com.icbc.api.response.QrcodeGenerateResponseV2;
import contentApi.createImage;
import contentApi.timeDate;
import contentApi.timeDay;
import dao.JedisClient;
import mapper.IbmCommercialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.IbmCommercial;
import pojo.IbmCommercialExample;
import service.JsonUtils;
import service.XslResult;
import service.createQrcode;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static contentApi.appid_key.api_accection;
import static contentApi.appid_key.message;

@Service
public class createQrcodeImpl implements createQrcode {
    @Autowired
    private IbmCommercialMapper ibmCommercialMapper;
    @Value("${QRCODER_OUTDATE_TIME}")
    private String QRCODER_OUTDATE_TIME;
    @Value("${REDIS_USER_QRCODE_KEY}")
    private String REDIS_USER_QRCODE_KEY;
    @Value("${Login_QRCODE_EXPIRE}")
    private int Login_QRCODE_EXPIRE;
    @Autowired
    private JedisClient jedisClient;
    Logger logger = LoggerFactory.getLogger(createQrcodeImpl.class);

    @Override
    public XslResult coderUrl(int userId, HttpServletRequest req) {
        QrcodeGenerateRequestV2.QrcodeGenerateRequestV2Biz bizContent = message();
        QrcodeGenerateRequestV2 request = new QrcodeGenerateRequestV2();
        request.setServiceUrl("https://apisandbox.dccnet.com.cn/api/qrcode/V2/generate");
        IbmCommercialExample example = new IbmCommercialExample();
        IbmCommercialExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);
        List<IbmCommercial> list = ibmCommercialMapper.selectByExample(example);
        IbmCommercial ibmCommercial = list.get(0);
        //特约商户编号
        bizContent.setMerId(ibmCommercial.getCommercialcode());
        //e生活档案编号
        bizContent.setStoreCode(ibmCommercial.getEfilecode());
        //商户系统订单号
        bizContent.setOutTradeNo(ibmCommercial.getOrdercode());
        //订单总金额，单位：分
        bizContent.setOrderAmt(ibmCommercial.getMoney());
        //商户订单生成的机器IP
        bizContent.setTporderCreateIp(ibmCommercial.getRobotip());
        //商户是否开启通知接口 0-否；1-是；非1按0处理
        bizContent.setNotifyFlag(ibmCommercial.getInformant());
        bizContent.setTradeDate(timeDate.timedate());
        bizContent.setTradeTime(timeDay.timeDay());
        bizContent.setPayExpire(QRCODER_OUTDATE_TIME);
        request.setBizContent(bizContent);
        QrcodeGenerateResponseV2 response;
        try {
            //msgId:消息通讯唯一编号，每次调用独立生成，APP级唯一
            response = api_accection().execute(request, "msgId");
            if (response.isSuccess()) {
                // 业务成功处理
                String json = JsonUtils.objectToJson(response.getQrcode());
                jedisClient.set(REDIS_USER_QRCODE_KEY + ":" + ibmCommercial.getId(), response.getQrcode());
                //设置session过期时间
                jedisClient.expire(Login_QRCODE_EXPIRE + ":" + ibmCommercial.getId(), Login_QRCODE_EXPIRE);
                XslResult xslResult = createImage.createImCode(json, ibmCommercial);
                logger.info("QRcode is logger", message());
                return xslResult;
            } else {
                // 失败
                String json = JsonUtils.objectToJson("获取失败");
                logger.info("QRcode is logger", message());
                return XslResult.build(response.getReturnCode(), json);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("QRcode is logger", message());
            return XslResult.build(500, "服务器异常，请联系工作人员！");
        }
    }
}
