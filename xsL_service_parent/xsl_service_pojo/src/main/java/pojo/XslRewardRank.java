package pojo;

public class XslRewardRank {
    private Integer id;

    private String rewardRankId;

    private String rewardRankName;

    private String rewardRankInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRewardRankId() {
        return rewardRankId;
    }

    public void setRewardRankId(String rewardRankId) {
        this.rewardRankId = rewardRankId == null ? null : rewardRankId.trim();
    }

    public String getRewardRankName() {
        return rewardRankName;
    }

    public void setRewardRankName(String rewardRankName) {
        this.rewardRankName = rewardRankName == null ? null : rewardRankName.trim();
    }

    public String getRewardRankInfo() {
        return rewardRankInfo;
    }

    public void setRewardRankInfo(String rewardRankInfo) {
        this.rewardRankInfo = rewardRankInfo == null ? null : rewardRankInfo.trim();
    }
}