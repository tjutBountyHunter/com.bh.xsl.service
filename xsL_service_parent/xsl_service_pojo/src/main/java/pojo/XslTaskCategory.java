package pojo;

import java.util.Date;

public class XslTaskCategory {
    private Integer id;

    private Integer parentid;

    private String name;

    private Integer tasknum;

    private Integer oknum;

    private Boolean state;

    private String failnum;

    private Date createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTasknum() {
        return tasknum;
    }

    public void setTasknum(Integer tasknum) {
        this.tasknum = tasknum;
    }

    public Integer getOknum() {
        return oknum;
    }

    public void setOknum(Integer oknum) {
        this.oknum = oknum;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getFailnum() {
        return failnum;
    }

    public void setFailnum(String failnum) {
        this.failnum = failnum == null ? null : failnum.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}