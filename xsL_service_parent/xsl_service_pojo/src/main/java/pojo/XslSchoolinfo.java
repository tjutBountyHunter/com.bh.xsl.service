package pojo;

public class XslSchoolinfo {
    private Integer id;

    private String schoolid;

    private String sno;

    private String major;

    private String college;

    private String school;

    private String startdate;

    private Byte degree;

    private Byte schoolhours;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid == null ? null : schoolid.trim();
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate == null ? null : startdate.trim();
    }

    public Byte getDegree() {
        return degree;
    }

    public void setDegree(Byte degree) {
        this.degree = degree;
    }

    public Byte getSchoolhours() {
        return schoolhours;
    }

    public void setSchoolhours(Byte schoolhours) {
        this.schoolhours = schoolhours;
    }
}