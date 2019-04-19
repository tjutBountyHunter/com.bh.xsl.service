package pojo;

import java.util.Date;

public class XslHunter {
    private Integer id;

    private String hunterId;

    private String userid;

    private Short level;

    private Integer empirical;

    private Integer taskaccnum;

    private Integer taskfailnum;

    private Short credit;

    private String descr;

    private Date lastTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHunterId() {
        return hunterId;
    }

    public void setHunterId(String hunterId) {
        this.hunterId = hunterId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Integer getEmpirical() {
        return empirical;
    }

    public void setEmpirical(Integer empirical) {
        this.empirical = empirical;
    }

    public Integer getTaskaccnum() {
        return taskaccnum;
    }

    public void setTaskaccnum(Integer taskaccnum) {
        this.taskaccnum = taskaccnum;
    }

    public Integer getTaskfailnum() {
        return taskfailnum;
    }

    public void setTaskfailnum(Integer taskfailnum) {
        this.taskfailnum = taskfailnum;
    }

    public Short getCredit() {
        return credit;
    }

    public void setCredit(Short credit) {
        this.credit = credit;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}