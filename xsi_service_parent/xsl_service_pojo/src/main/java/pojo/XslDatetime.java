package pojo;

import java.util.Date;

public class XslDatetime {
    private Integer id;

    private Integer hunterid;

    private String taskid;

    private Date createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHunterid() {
        return hunterid;
    }

    public void setHunterid(Integer hunterid) {
        this.hunterid = hunterid;
    }


    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }
}