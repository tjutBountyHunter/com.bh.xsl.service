package pojo;

import java.util.Date;

public class XslHunterTag {
    private Integer id;

    private Integer hunterid;

    private Integer tagid;

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

    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}