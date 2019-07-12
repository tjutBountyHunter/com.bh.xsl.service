package service;

import util.XslResult;
import vo.XslTagReqVo;

public interface TagService {

	XslResult createTags(XslTagReqVo xslTagReqVo);

	XslResult queryTag(XslTagReqVo xslTagReqVo);

}
