package vo;

public class XslTaskInfoListReqVo{
	private String userid;
	private Integer size;
	private String taskid;
	private String schoolName;
	private Integer upFlag;
	private Integer downFlag;
	private String type;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
