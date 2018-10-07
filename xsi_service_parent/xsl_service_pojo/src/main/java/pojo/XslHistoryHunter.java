package pojo;

public class XslHistoryHunter {
    private Integer id;

    private String name;

    private String tag_name;

    private Short level;

    private Integer taskaccnum;

    private Integer taskfailnum;

    private Short credit;

    private String descr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTaskaccnum() {
        return taskaccnum;
    }

    public void setTaskaccnum(Integer taskaccnum) {
        this.taskaccnum = taskaccnum;
    }

    public Integer getTaskfailnum() {
        return taskfailnum;
    }

    public void setTaskfailnum(Integer taskfailnum) {
        this.taskfailnum = taskfailnum;
    }

    public Short getCredit() {
        return credit;
    }

    public void setCredit(Short credit) {
        this.credit = credit;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
