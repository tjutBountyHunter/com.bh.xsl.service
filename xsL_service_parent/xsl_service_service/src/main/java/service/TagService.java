package service;

import util.XslResult;
import vo.TagReqVo;

public interface TagService {

	XslResult createTags(TagReqVo tagReqVo);

	XslResult queryTag(TagReqVo tagReqVo);

}
