package pojo;

import java.util.Date;

public class XslHunterLevelRule {
    private Integer id;

    private Integer hunterlevelid;

    private Integer ruleid;

    private Date createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHunterlevelid() {
        return hunterlevelid;
    }

    public void setHunterlevelid(Integer hunterlevelid) {
        this.hunterlevelid = hunterlevelid;
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