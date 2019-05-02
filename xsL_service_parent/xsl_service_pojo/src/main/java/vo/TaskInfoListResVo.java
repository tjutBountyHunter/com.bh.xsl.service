package vo;

import java.util.List;

public class TaskInfoListResVo {
	private List<TaskInfoVo> taskInfoVos;

	private Integer upFlag;

	private Integer downFlag;

	public List<TaskInfoVo> getTaskInfoVos() {
		return taskInfoVos;
	}

	public void setTaskInfoVos(List<TaskInfoVo> taskInfoVos) {
		this.taskInfoVos = taskInfoVos;
	}

	public Integer getUpFlag() {
		return upFlag;
	}

	public void setUpFlag(Integer upFlag) {
		this.upFlag = upFlag;
	}

	public Integer getDownFlag() {
		return downFlag;
	}

	public void setDownFlag(Integer downFlag) {
		this.downFlag = downFlag;
	}
}
