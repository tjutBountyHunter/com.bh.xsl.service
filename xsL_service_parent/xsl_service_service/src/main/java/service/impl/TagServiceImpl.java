package service.impl;

import example.XslTagExample;
import mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.XslTag;
import service.TagService;
import util.GsonSingle;
import util.XslResult;
import vo.TagReqVo;
import vo.tagVo;

import javax.enterprise.inject.New;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TagServiceImpl implements TagService {
	@Autowired
	private XslTagMapper xslTagMapper;

	private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);


	@Override
	public XslResult createTags(TagReqVo tagReqVo) {
		String tagName = tagReqVo.getTagName();

		if(StringUtils.isEmpty(tagName)){
			return XslResult.build(400, "参数错误");
		}

		try {
				XslTagExample xslTagExample = new XslTagExample();
				XslTagExample.Criteria criteria = xslTagExample.createCriteria();
				criteria.andNameEqualTo(tagName);
				List<XslTag> list = xslTagMapper.selectByExample(xslTagExample);
				if(list != null && list.size()>0){
					return XslResult.build(200, "标签创建成功");
				}

				XslTag xslTag = new XslTag();
				xslTag.setTagid(UUID.randomUUID().toString().substring(0,6));
				xslTag.setName(tagName);
				xslTag.setCreatedate(new Date());
				xslTagMapper.insertSelective(xslTag);

			return XslResult.ok(xslTag.getTagid());
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}
	}

	@Override
	public XslResult queryTag(TagReqVo tagReqVo) {
		Integer tagNum = tagReqVo.getTagNum();
		List<String> obtainedTags = tagReqVo.getObtainedTags();
		XslTagExample xslTagExample = new XslTagExample();
		XslTagExample.Criteria criteria = xslTagExample.createCriteria();

		if(obtainedTags != null && obtainedTags.size() > 0){
			criteria.andTagidNotIn(obtainedTags);
		}

		List<XslTag> list = xslTagMapper.selectByExampleLimit(xslTagExample, tagNum);
		List<tagVo> tagVos = new ArrayList<>();
		for (XslTag xslTag : list){
			tagVo tagVo = new tagVo();
			tagVo.setTagid(xslTag.getTagid());
			tagVo.setTagName(xslTag.getName());
			tagVos.add(tagVo);
		}

		logger.info("queryTag.list msg:" + GsonSingle.getGson().toJson(tagVos));
		return XslResult.ok(tagVos);
	}
}
