package pojo;

import java.util.Date;

public class XslHunterTask {
    private Integer id;

    private String hunterid;

    private String taskid;

    private Date createdate;

    private Date updatedate;

    private Byte taskstate;

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

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid == null ? null : taskid.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Byte getTaskstate() {
        return taskstate;
    }

    public void setTaskstate(Byte taskstate) {
        this.taskstate = taskstate;
    }
}