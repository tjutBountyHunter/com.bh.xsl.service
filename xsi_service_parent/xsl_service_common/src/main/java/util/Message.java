package util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class Message {
    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String product = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    private static final String domain = "dysmsapi.aliyuncs.com";
    /**
     * 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
     */
    private static String accessKeyId = "LTAIvRrk3RumBmDU";
    private static String accessKeySecret = "sfSVL4KZy4myiZlOnIKeQpUZuS2Uzv";
    private static String signName = "高山潍";
    private static String identifyingTempleteCode = "SMS_141670053";
    private static String identifyingTempleteMaster = "SMS_144943309";
    private static String identifyingTempleteMHunter = "SMS_144853949";

    public static void init(String accessKeyId, String accessKeySecret, String signName, String identifyingTempleteCode) {
        Message.accessKeyId = accessKeyId;
        Message.accessKeySecret = accessKeySecret;
        Message.signName = signName;
        Message.identifyingTempleteCode = identifyingTempleteCode;
    }

    public static SendSmsResponse sendSms(String mobile, String templateParam, String templateCode) throws ClientException {
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号
        request.setPhoneNumbers(mobile);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        request.setTemplateParam(templateParam);
        // hint 此处可能会抛出异常，注意catch
        try {
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            return sendSmsResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SendSmsResponse sendIdentifyingCode(String mobile, String code) {
        try {
            return sendSms(mobile, "{\"code\":\"" + code + "\"}", identifyingTempleteCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return new SendSmsResponse();
    }

    public static SendSmsResponse sendIdentifyingTempleteMHunter(String mobile) {
        try {
            return sendSms(mobile, "", identifyingTempleteMHunter);
        } catch (ClientException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SendSmsResponse sendIdentifyingTempleteMaster(String mobile) {
        try {
            return sendSms(mobile, "", identifyingTempleteMaster);
        } catch (ClientException e) {
            e.printStackTrace();
            return null;
        }
    }
}