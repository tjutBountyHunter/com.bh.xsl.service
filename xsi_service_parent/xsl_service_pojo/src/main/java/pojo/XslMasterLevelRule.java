package pojo;

import java.util.Date;

public class XslMasterLevelRule {
    private Integer id;

    private Integer masterlevelid;

    private Integer ruleid;

    private Date createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMasterlevelid() {
        return masterlevelid;
    }

    public void setMasterlevelid(Integer masterlevelid) {
        this.masterlevelid = masterlevelid;
    }

    public Integer getRuleid() {
        return ruleid;
    }

    public void setRuleid(Integer ruleid) {
        this.ruleid = ruleid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}