package vo;

public class JPushVo {
	/**
	 * 设备标识
	 *
	 */
	private String registrationId;
	/**
	 * 通知内容标题
	 *
	 */
	private String notificationTitle;
	/**
	 * 消息内容标题
	 *
	 */
	private String msgTitle;
	/**
	 * 消息内容
	 *
	 */
	private String msgContent;
	/**
	 * 扩展字段
	 *
	 */
	private String extrasparam;

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getNotificationTitle() {
		return notificationTitle;
	}

	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getExtrasparam() {
		return extrasparam;
	}

	public void setExtrasparam(String extrasparam) {
		this.extrasparam = extrasparam;
	}
}
