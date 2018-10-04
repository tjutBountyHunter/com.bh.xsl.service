package pojo;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class XslTaskPosh {
    private String descr;
    private BigDecimal money;
    private Byte state;
    private String createdate;
    private String deadline;
    private String name;
    private String taskId;
    private Short level;
    private String userName;
    private String tag_name;
    private String url;

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        deadline = deadline.substring(0, deadline.indexOf("."));
        DateFormat format4 = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        System.out.println(deadline);
        String dead = null;
        try {
            dead = format4.format(DateFormat.getDateInstance().parse(deadline));
            this.deadline = dead;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
