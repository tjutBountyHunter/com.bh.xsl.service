package pojo;

import java.io.Serializable;

public class AlipayPayInfo implements Serializable {

    //交易描述
    private String body;
    //交易标题
    private String subject;
    //唯一订单号
    private String outTradeNo;
    //最晚付款时间，逾期关闭交易。
    // 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（当天0点截止）
    private String timeoutExpress;
    //订单总金额，单位为元，精确到小数点后两位。
    private String totalAmount;
    //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
    private final String productCode = "QUICK_MSECURITY_PAY";
    //商品主类型：0—虚拟类商品，1—实物类商品
    private String goodsType;
    //公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝会在异步通知时将该参数原样返回。
    // 本参数必须进行UrlEncode之后才可以发送给支付宝
    private String passbackParams;
    //优惠参数,注：仅与支付宝协商后可用,格式：{"storeIdType":"1"}
    //true表示有，false表示没有,默认为false
    private String promoParams;
    //业务扩展参数，格式：{"sys_service_provider_id":"2088511833207846"}
    //true表示有，false表示没有,默认为false
    private boolean extendParams = false;
    //可用渠道，用户只能在指定渠道范围内支付
    //注：与disable_pay_channels互斥，逗号分隔
    private String enablePayChannels;
    //禁用渠道，用户不可用指定渠道支付
    //注：与enable_pay_channels互斥，逗号分隔
    private String disablePayChannels;
    //商户门店编号。该参数用于请求参数中以区分各门店
    private String storeId;
    //外部指定买家
    //true表示有，false表示没有,默认为false
    private boolean extUserInfo = false;

    //AlipayTradeAppPayModel包含但是API中没有给出解释的参数
//    private String businessParams;
//    private InvoiceInfo invoiceInfo;
//    private RoyaltyInfo royaltyInfo;
//    private String sellerId;
//    private SettleInfo settleInfo;
//    private String specifiedChannel;
//    private SubMerchant subMerchant;
//    private String timeExpire;


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTimeoutExpress() {
        return timeoutExpress;
    }

    public void setTimeoutExpress(String timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getPassbackParams() {
        return passbackParams;
    }

    public void setPassbackParams(String passbackParams) {
        this.passbackParams = passbackParams;
    }

    public String getPromoParams() {
        return promoParams;
    }

    public void setPromoParams(String promoParams) {
        this.promoParams = promoParams;
    }

    public boolean isExtendParams() {
        return extendParams;
    }

    public void setExtendParams(boolean extendParams) {
        this.extendParams = extendParams;
    }

    public String getEnablePayChannels() {
        return enablePayChannels;
    }

    public void setEnablePayChannels(String enablePayChannels) {
        this.enablePayChannels = enablePayChannels;
    }

    public String getDisablePayChannels() {
        return disablePayChannels;
    }

    public void setDisablePayChannels(String disablePayChannels) {
        this.disablePayChannels = disablePayChannels;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public boolean isExtUserInfo() {
        return extUserInfo;
    }

    public void setExtUserInfo(boolean extUserInfo) {
        this.extUserInfo = extUserInfo;
    }
}
