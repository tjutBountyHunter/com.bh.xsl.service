package vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TaskReqVo {
	private String sourceType;
	private String taskTitle;
	private String content;
	private List<ImageVo> images;
	private List<tagVo> tags;
	private Boolean isRecommend;
	private String masterId;
	private BigDecimal money;
	private String createDate;
	private String deadLineDate;

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public Boolean getRecommend() {
		return isRecommend;
	}

	public void setRecommend(Boolean recommend) {
		isRecommend = recommend;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<ImageVo> getImages() {
		return images;
	}

	public void setImages(List<ImageVo> images) {
		this.images = images;
	}

	public List<tagVo> getTags() {
		return tags;
	}

	public void setTags(List<tagVo> tags) {
		this.tags = tags;
	}

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean recommend) {
		isRecommend = recommend;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getDeadLineDate() {
		return deadLineDate;
	}

	public void setDeadLineDate(String deadLineDate) {
		this.deadLineDate = deadLineDate;
	}
}
