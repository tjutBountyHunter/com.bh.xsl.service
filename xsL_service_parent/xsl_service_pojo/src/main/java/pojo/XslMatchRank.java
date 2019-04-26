package pojo;

public class XslMatchRank {
    private Integer id;

    private String matchRankId;

    private String mankName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatchRankId() {
        return matchRankId;
    }

    public void setMatchRankId(String matchRankId) {
        this.matchRankId = matchRankId == null ? null : matchRankId.trim();
    }

    public String getMankName() {
        return mankName;
    }

    public void setMankName(String mankName) {
        this.mankName = mankName == null ? null : mankName.trim();
    }
}