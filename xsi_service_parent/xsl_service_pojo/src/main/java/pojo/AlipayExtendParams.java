package pojo;

import java.io.Serializable;

public class AlipayExtendParams implements Serializable {
    //    id控制，接收方和发送方的serialVersionUID必须一致
    //    private static final long serialVersionUID = 1L;
    //系统商编号，该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID
    private String sysServiceProviderId;
    // 是否发起实名校验 T：发起 F：不发起
    // private String needBuyerRealnamed;
    // 账务备注,该字段显示在离线账单的账务备注中
    //private String TRANS_MEMO;
    //花呗分期数（目前仅支持3、6、12）
    private String hbFqNum;
    //卖家承担收费比例，商家承担手续费传入100，用户承担手续费传入0
    // 仅支持传入100、0两种
    private String hbFqSellerPercent;
    //不清楚是什么参数，API没有给
//    private String cardType;
//    private String industryRefluxInfo;

    public String getSysServiceProviderId() {
        return sysServiceProviderId;
    }

    public void setSysServiceProviderId(String sysServiceProviderId) {
        this.sysServiceProviderId = sysServiceProviderId;
    }

    public String getHbFqNum() {
        return hbFqNum;
    }

    public void setHbFqNum(String hbFqNum) {
        this.hbFqNum = hbFqNum;
    }

    public String getHbFqSellerPercent() {
        return hbFqSellerPercent;
    }

    public void setHbFqSellerPercent(String hbFqSellerPercent) {
        this.hbFqSellerPercent = hbFqSellerPercent;
    }


}
