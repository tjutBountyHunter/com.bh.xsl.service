package pojo;

import java.util.Date;

public class XslCollecth {
    private Integer id;

    private String userid;

    private String hunterid;

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

    public String getHunterid() {
        return hunterid;
    }

    public void setHunterid(String hunterid) {
        this.hunterid = hunterid == null ? null : hunterid.trim();
    }

    public Date getCollectdate() {
        return collectdate;
    }

    public void setCollectdate(Date collectdate) {
        this.collectdate = collectdate;
    }
}