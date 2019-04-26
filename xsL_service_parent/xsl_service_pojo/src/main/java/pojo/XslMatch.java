package pojo;

import java.util.Date;

public class XslMatch {
    private Integer id;

    private String matchId;

    private String matchName;

    private String matchRankId;

    private String matchWebsite;

    private String matchOrientedId;

    private Date matchStarttime;

    private Date matchEndtime;

    private Integer matchState;

    private Date matchSignupEndtime;

    private String matchAddress;

    private Integer matchSignupMaxnum;

    private Integer matchForm;

    private Integer matchTeamnum;

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

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName == null ? null : matchName.trim();
    }

    public String getMatchRankId() {
        return matchRankId;
    }

    public void setMatchRankId(String matchRankId) {
        this.matchRankId = matchRankId == null ? null : matchRankId.trim();
    }

    public String getMatchWebsite() {
        return matchWebsite;
    }

    public void setMatchWebsite(String matchWebsite) {
        this.matchWebsite = matchWebsite == null ? null : matchWebsite.trim();
    }

    public String getMatchOrientedId() {
        return matchOrientedId;
    }

    public void setMatchOrientedId(String matchOrientedId) {
        this.matchOrientedId = matchOrientedId == null ? null : matchOrientedId.trim();
    }

    public Date getMatchStarttime() {
        return matchStarttime;
    }

    public void setMatchStarttime(Date matchStarttime) {
        this.matchStarttime = matchStarttime;
    }

    public Date getMatchEndtime() {
        return matchEndtime;
    }

    public void setMatchEndtime(Date matchEndtime) {
        this.matchEndtime = matchEndtime;
    }

    public Integer getMatchState() {
        return matchState;
    }

    public void setMatchState(Integer matchState) {
        this.matchState = matchState;
    }

    public Date getMatchSignupEndtime() {
        return matchSignupEndtime;
    }

    public void setMatchSignupEndtime(Date matchSignupEndtime) {
        this.matchSignupEndtime = matchSignupEndtime;
    }

    public String getMatchAddress() {
        return matchAddress;
    }

    public void setMatchAddress(String matchAddress) {
        this.matchAddress = matchAddress == null ? null : matchAddress.trim();
    }

    public Integer getMatchSignupMaxnum() {
        return matchSignupMaxnum;
    }

    public void setMatchSignupMaxnum(Integer matchSignupMaxnum) {
        this.matchSignupMaxnum = matchSignupMaxnum;
    }

    public Integer getMatchForm() {
        return matchForm;
    }

    public void setMatchForm(Integer matchForm) {
        this.matchForm = matchForm;
    }

    public Integer getMatchTeamnum() {
        return matchTeamnum;
    }

    public void setMatchTeamnum(Integer matchTeamnum) {
        this.matchTeamnum = matchTeamnum;
    }
}