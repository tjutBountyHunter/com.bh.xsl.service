package pojo;

public class XslOriented {
    private Integer id;

    private String orientedId;

    private String orientedName;

    private String orientedInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrientedId() {
        return orientedId;
    }

    public void setOrientedId(String orientedId) {
        this.orientedId = orientedId == null ? null : orientedId.trim();
    }

    public String getOrientedName() {
        return orientedName;
    }

    public void setOrientedName(String orientedName) {
        this.orientedName = orientedName == null ? null : orientedName.trim();
    }

    public String getOrientedInfo() {
        return orientedInfo;
    }

    public void setOrientedInfo(String orientedInfo) {
        this.orientedInfo = orientedInfo == null ? null : orientedInfo.trim();
    }
}