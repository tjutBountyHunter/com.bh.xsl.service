package pojo;

import java.util.Date;

public class XslHunter {
    private Integer id;

    private String hunterid;

    private String userid;

    private Short level;

    private Integer empirical;

    private Integer taskaccnum;

    private Integer taskfailnum;

    private Short credit;

    private String descr;

    private Date lasttime;

    private Boolean state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHunterid() {
        return hunterid;
    }

    public void setHunterid(String hunterid) {
        this.hunterid = hunterid == null ? null : hunterid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
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

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}