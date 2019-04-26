package example;

import java.util.Date;

public class XslMasterLevelExperience {
    private Integer id;

    private String masterlevelid;

    private Short level;

    private Short experience;

    private Date createdate;

    private Date updatedate;

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

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Short getExperience() {
        return experience;
    }

    public void setExperience(Short experience) {
        this.experience = experience;
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
}