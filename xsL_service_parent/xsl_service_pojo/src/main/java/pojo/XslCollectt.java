package pojo;

import java.util.Date;

public class XslCollectt {
    private Integer id;

    private String userid;

    private String taskid;

    private Date collectdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid == null ? null : taskid.trim();
    }

    public Date getCollectdate() {
        return collectdate;
    }

    public void setCollectdate(Date collectdate) {
        this.collectdate = collectdate;
    }
}