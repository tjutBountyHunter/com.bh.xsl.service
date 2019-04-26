package pojo;

import java.util.Date;

public class XslHunterLevelRule {
    private Integer id;

    private String hunterlevelid;

    private String ruleid;

    private Date createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHunterlevelid() {
        return hunterlevelid;
    }

    public void setHunterlevelid(String hunterlevelid) {
        this.hunterlevelid = hunterlevelid == null ? null : hunterlevelid.trim();
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