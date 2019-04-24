package pojo;

import java.util.Date;

public class IbmCommercial {
    private Integer id;

    private Integer userid;

    private String commercialcode;

    private String efilecode;

    private String ordercode;

    private String money;

    private String robotip;

    private String informant;

    private Date createdate;

    private Date updatedate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCommercialcode() {
        return commercialcode;
    }

    public void setCommercialcode(String commercialcode) {
        this.commercialcode = commercialcode == null ? null : commercialcode.trim();
    }

    public String getEfilecode() {
        return efilecode;
    }

    public void setEfilecode(String efilecode) {
        this.efilecode = efilecode == null ? null : efilecode.trim();
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public String getRobotip() {
        return robotip;
    }

    public void setRobotip(String robotip) {
        this.robotip = robotip == null ? null : robotip.trim();
    }

    public String getInformant() {
        return informant;
    }

    public void setInformant(String informant) {
        this.informant = informant == null ? null : informant.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
}