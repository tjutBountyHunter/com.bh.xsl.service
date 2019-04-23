package service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import service.VersionServie;
import util.JedisClientUtil;
import util.XslResult;

@Service
public class VersionServiceImpl implements VersionServie {
	@Override
	public XslResult getVersion(String key) {
		String version = JedisClientUtil.get(key);

		if(StringUtils.isEmpty(version)){
			XslResult.build(403, "参数错误");
		}

		if(StringUtils.isEmpty(version)){
			return XslResult.ok("1");
		}
		return XslResult.ok(version);
	}
}
