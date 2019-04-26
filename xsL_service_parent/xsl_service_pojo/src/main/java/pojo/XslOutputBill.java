package pojo;

import java.util.Date;

public class XslOutputBill {
    private Integer id;

    private String outputid;

    private Double outputMoney;

    private Date tradetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutputid() {
        return outputid;
    }

    public void setOutputid(String outputid) {
        this.outputid = outputid == null ? null : outputid.trim();
    }

    public Double getOutputMoney() {
        return outputMoney;
    }

    public void setOutputMoney(Double outputMoney) {
        this.outputMoney = outputMoney;
    }

    public Date getTradetime() {
        return tradetime;
    }

    public void setTradetime(Date tradetime) {
        this.tradetime = tradetime;
    }
}