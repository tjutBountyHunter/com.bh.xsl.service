package vo;

import java.util.Date;

public class HunterInfo {
	private String hunterid;

	private Short level;

	private Integer empirical;

	private Integer taskaccnum;

	private Integer taskfailnum;

	private Short credit;

	private String descr;

	private Date lasttime;

	private Boolean state;

	private String txUrl;

	public String getHunterid() {
		return hunterid;
	}

	public void setHunterid(String hunterid) {
		this.hunterid = hunterid;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public Integer getEmpirical() {
		return empirical;
	}

	public void setEmpirical(Integer empirical) {
		this.empirical = empirical;
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

	public Date getLasttime() {
		return lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getTxUrl() {
		return txUrl;
	}

	public void setTxUrl(String txUrl) {
		this.txUrl = txUrl;
	}
}
