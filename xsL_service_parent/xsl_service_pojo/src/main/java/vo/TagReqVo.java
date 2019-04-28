package vo;

import java.util.List;

public class TagReqVo {
	private Integer tagNum;
	private List<String> obtainedTags;
	private List<String> tags;

	public Integer getTagNum() {
		return tagNum;
	}

	public void setTagNum(Integer tagNum) {
		this.tagNum = tagNum;
	}

	public List<String> getObtainedTags() {
		return obtainedTags;
	}

	public void setObtainedTags(List<String> obtainedTags) {
		this.obtainedTags = obtainedTags;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
