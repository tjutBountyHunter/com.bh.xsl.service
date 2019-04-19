package enums;

public enum  UserStateEnum {
	NA((byte)0, "用户还未审核"),
	NORMAL((byte)1, "正常"),
	FREEZE((byte)-1, "用户冻结"),
	NOAC((byte)-2, "用户审核未通过"),
	DEL((byte)-3, "用户已被删除");
	private Byte code;
	private String info;

	UserStateEnum(Byte code, String info) {
		this.code = code;
		this.info = info;
	}

	public Byte getCode() {
		return code;
	}

	public String getInfo() {
		return info;
	}

}
