package pojo;

import java.util.Date;

public class XslMaster {
    private Integer id;

    private Integer userid;

    private Short level;

    private Integer empirical;

    private Integer taskaccnum;

    private Integer taskrevokenum;

    private Short credit;

    private String desc;

    private Date lastaccdate;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Date getLastaccdate() {
        return lastaccdate;
    }

    public void setLastaccdate(Date lastaccdate) {
        this.lastaccdate = lastaccdate;
    }
}