package vo;

import java.math.BigDecimal;
import java.util.Date;

public class TaskInfoVo {
	private Integer cid;

	private String taskid;

	private String tasktitle;

	private String content;

	private BigDecimal money;

	private String masterid;

	private Byte state;

	private Date createdate;

	private Date updatedate;

	private Date deadline;

	private String sourcetype;

	private String name;

	private String sex;

	private Short masterlevel;

	private String txUrl;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getTasktitle() {
		return tasktitle;
	}

	public void setTasktitle(String tasktitle) {
		this.tasktitle = tasktitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
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

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Short getMasterlevel() {
		return masterlevel;
	}

	public void setMasterlevel(Short masterlevel) {
		this.masterlevel = masterlevel;
	}

	public String getTxUrl() {
		return txUrl;
	}

	public void setTxUrl(String txUrl) {
		this.txUrl = txUrl;
	}
}
