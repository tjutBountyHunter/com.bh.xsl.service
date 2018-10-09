package pojo;

import java.util.Date;

public class XslUser {
    private Integer id;

    private Integer hunterid;

    private Integer masterid;

    private Integer schoolinfo;

    private String name;

    private String password;

    private String sex;

    private String phone;

    private String email;

    private Byte state;

    private String signature;

    private Date createdate;

    private Date updatedate;

    private String url;

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

    public Integer getMasterid() {
        return masterid;
    }

    public void setMasterid(Integer masterid) {
        this.masterid = masterid;
    }

    public Integer getSchoolinfo() {
        return schoolinfo;
    }

    public void setSchoolinfo(Integer schoolinfo) {
        this.schoolinfo = schoolinfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}