package pojo;

import java.math.BigDecimal;
import java.util.Date;

public class XslcollectTask {
    private int id;
    private String name;
    private String url;
    private String descr;
    private String deadline;
    private String money;
    private Integer number;
    private BigDecimal money_task;

    private Byte state;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public BigDecimal getMoney_task() {
        return money_task;
    }

    public void setMoney_task(BigDecimal money_task) {
        this.money_task = money_task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
