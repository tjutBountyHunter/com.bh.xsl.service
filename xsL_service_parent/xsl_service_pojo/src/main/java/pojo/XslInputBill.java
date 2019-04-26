package pojo;

import java.util.Date;

public class XslInputBill {
    private Integer id;

    private String inputid;

    private Double inputMoney;

    private Date tradetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInputid() {
        return inputid;
    }

    public void setInputid(String inputid) {
        this.inputid = inputid == null ? null : inputid.trim();
    }

    public Double getInputMoney() {
        return inputMoney;
    }

    public void setInputMoney(Double inputMoney) {
        this.inputMoney = inputMoney;
    }

    public Date getTradetime() {
        return tradetime;
    }

    public void setTradetime(Date tradetime) {
        this.tradetime = tradetime;
    }
}