package pojo;

import java.util.Date;

public class XslHunter {
    private Integer id;

    private Short level;

    private Integer empirical;

    private Integer taskaccnum;

    private Integer taskfailnum;

    private Short credit;

    private String descr;

    private Date lastaccdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDesc() {
        return descr;
    }

    public void setDesc(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public Date getLastaccdate() {
        return lastaccdate;
    }

    public void setLastaccdate(Date lastaccdate) {
        this.lastaccdate = lastaccdate;
    }
}