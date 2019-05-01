package vo;

public class SendAndRecTaskReqVo {
	private String masterid;

	private String hunterid;

	private Integer page;

	private Integer rows;

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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}
}
