package pojo;

import java.util.Date;

public class XslMasterLevelPrivilege {
    private Integer id;

    private String hunterlevelid;

    private String privilegeid;

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

    public String getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(String privilegeid) {
        this.privilegeid = privilegeid == null ? null : privilegeid.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}