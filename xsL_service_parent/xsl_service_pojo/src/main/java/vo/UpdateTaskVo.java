package vo;

import java.io.Serializable;

public class UpdateTaskVo implements Serializable {
	private String taskId;

	private Byte state;

	private String updatedate;

	private String name;

	private Short masterlevel;

	private String txUrl;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
