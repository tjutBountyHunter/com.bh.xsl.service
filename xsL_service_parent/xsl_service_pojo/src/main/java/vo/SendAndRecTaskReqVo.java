package vo;

public class SendAndRecTaskReqVo {
	private String masterid;

	private String hunterid;

	private Byte state;

	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getHunterid() {
		return hunterid;
	}

	public void setHunterid(String hunterid) {
		this.hunterid = hunterid;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}
}
