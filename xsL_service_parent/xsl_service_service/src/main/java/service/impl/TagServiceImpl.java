package service.impl;

import com.xsl.task.TagResource;
import com.xsl.task.vo.TagReqVo;
import com.xsl.task.vo.TagResVo;
import com.xsl.task.vo.TagVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import service.TagService;
import util.GsonSingle;
import util.XslResult;
import vo.XslTagReqVo;
import vo.XslTagResVo;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

	@Resource
	private TagResource tagResource;

	private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);


	@Override
	public XslResult createTags(XslTagReqVo xslTagReqVo) {
		logger.info("createTags InParam:{}",GsonSingle.getGson().toJson(xslTagReqVo));
		TagReqVo tagReqVo = new TagReqVo();
		BeanUtils.copyProperties(xslTagReqVo,tagReqVo);
		try {
			TagResVo tagResVo = tagResource.createTags(tagReqVo);
			if(tagResVo.getStatus()==200){
				XslTagResVo xslTagResVo = new XslTagResVo();
				BeanUtils.copyProperties(tagResVo,xslTagResVo);
				return XslResult.ok(xslTagResVo);
			}
			logger.info("创建标签失败：tagResVo is {}",GsonSingle.getGson().toJson(tagResVo));
			return XslResult.build(tagResVo.getStatus(),tagResVo.getMsg());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult queryTag(XslTagReqVo xslTagReqVo) {
		logger.info("queryTag InParam:{}",GsonSingle.getGson().toJson(xslTagReqVo));
		TagReqVo tagReqVo = new TagReqVo();
		BeanUtils.copyProperties(xslTagReqVo,tagReqVo);
		try {
			List<TagVo> tagVos = tagResource.queryTag(tagReqVo);
			if(tagVos==null||tagVos.size()<1){
				return XslResult.build(403,"未查到可用标签");
			}
			return XslResult.ok(tagVos);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
