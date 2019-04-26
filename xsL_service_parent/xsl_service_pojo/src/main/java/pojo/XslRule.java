package pojo;

public class XslRule {
    private Integer id;

    private Integer pid;

    private String name;

    private String url;

    private String token;

    private Integer usecount;

    private Integer assgincount;

    private Short priority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Integer getUsecount() {
        return usecount;
    }

    public void setUsecount(Integer usecount) {
        this.usecount = usecount;
    }

    public Integer getAssgincount() {
        return assgincount;
    }

    public void setAssgincount(Integer assgincount) {
        this.assgincount = assgincount;
    }

    public Short getPriority() {
        return priority;
    }

    public void setPriority(Short priority) {
        this.priority = priority;
    }
}