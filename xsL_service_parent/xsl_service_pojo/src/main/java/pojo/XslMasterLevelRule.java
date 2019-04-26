package pojo;

import java.util.Date;

public class XslMasterLevelRule {
    private Integer id;

    private String masterlevelid;

    private String ruleid;

    private Date createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMasterlevelid() {
        return masterlevelid;
    }

    public void setMasterlevelid(String masterlevelid) {
        this.masterlevelid = masterlevelid == null ? null : masterlevelid.trim();
    }

    public String getRuleid() {
        return ruleid;
    }

    public void setRuleid(String ruleid) {
        this.ruleid = ruleid == null ? null : ruleid.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}