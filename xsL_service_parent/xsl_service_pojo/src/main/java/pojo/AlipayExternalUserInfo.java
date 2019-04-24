package pojo;

import java.io.Serializable;

public class AlipayExternalUserInfo implements Serializable {
    //姓名   need_check_info=T时该参数才有效
    private String name;
    //手机号  注：该参数暂不校验
    private String mobile;
    //身份证：IDENTITY_CARD、护照：PASSPORT、军官证：OFFICER_CARD、士兵证：SOLDIER_CARD、户口本：HOKOU等
    private String certType;
    //证件号
    private String certNo;
    //允许的最小买家年龄，买家年龄必须大于等于所传数值
    //1. need_check_info=T时该参数才有效
    //2. min_age为整数，必须大于等于0
    private String minAge;
    //是否强制校验付款人身份信息
    //T:强制校验，F：不强制
    private String fixBuyer;
    //是否强制校验身份信息
    //T:强制校验，F：不强制
    private String needCheckInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public String getFixBuyer() {
        return fixBuyer;
    }

    public void setFixBuyer(String fixBuyer) {
        this.fixBuyer = fixBuyer;
    }

    public String getNeedCheckInfo() {
        return needCheckInfo;
    }

    public void setNeedCheckInfo(String needCheckInfo) {
        this.needCheckInfo = needCheckInfo;
    }
}
