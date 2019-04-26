package pojo;

public class XslMatchReward {
    private Integer id;

    private String matchId;

    private String remardId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId == null ? null : matchId.trim();
    }

    public String getRemardId() {
        return remardId;
    }

    public void setRemardId(String remardId) {
        this.remardId = remardId == null ? null : remardId.trim();
    }
}