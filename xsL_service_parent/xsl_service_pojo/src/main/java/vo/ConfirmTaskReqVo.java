package vo;

public class ConfirmTaskReqVo {
	private String taskId;
	private String hunterid;
	private Byte nowState;
	private Byte afterState;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getHunterid() {
		return hunterid;
	}

	public void setHunterid(String hunterid) {
		this.hunterid = hunterid;
	}

	public Byte getNowState() {
		return nowState;
	}

	public void setNowState(Byte nowState) {
		this.nowState = nowState;
	}

	public Byte getAfterState() {
		return afterState;
	}

	public void setAfterState(Byte afterState) {
		this.afterState = afterState;
	}
}
