package pojo;

import java.util.Date;

public class XslMaster {
    private Integer id;

    private String masterid;

    private String userid;

    private Short level;

    private Integer empirical;

    private Integer taskaccnum;

    private Integer taskrevokenum;

    private Short credit;

    private String descr;

    private Date lastaccdate;

    private Boolean state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMasterid() {
        return masterid;
    }

    public void setMasterid(String masterid) {
        this.masterid = masterid == null ? null : masterid.trim();
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

    public Integer getTaskrevokenum() {
        return taskrevokenum;
    }

    public void setTaskrevokenum(Integer taskrevokenum) {
        this.taskrevokenum = taskrevokenum;
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

    public Date getLastaccdate() {
        return lastaccdate;
    }

    public void setLastaccdate(Date lastaccdate) {
        this.lastaccdate = lastaccdate;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}