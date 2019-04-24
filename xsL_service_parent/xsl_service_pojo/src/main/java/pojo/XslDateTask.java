package pojo;

import java.math.BigDecimal;

public class XslDateTask {
    private Integer SendId;
    private String descr;
    private BigDecimal money;
    private String deadline;
    private String tagname;
    private String categoryname;

    public Integer getSendId() {
        return SendId;
    }

    public void setSendId(Integer sendId) {
        SendId = sendId;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }


    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
