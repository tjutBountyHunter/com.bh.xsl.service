package service.impl;

import com.xsl.user.HunterListResource;
import org.springframework.stereotype.Service;
import service.HunterListService;
import util.XslResult;
import vo.HunterInfoVo;
import vo.HunterListReq;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HunterListServiceImpl implements HunterListService {
	@Resource
	HunterListResource hunterListResource;


	@Override
	public XslResult queryHistoryHunter(HunterListReq hunterListReq) {
		try {
			List<HunterInfoVo> hunterInfoVos = hunterListResource.queryHistoryHunter(hunterListReq);
			return XslResult.ok(hunterInfoVos);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public XslResult queryNBHunter(HunterListReq hunterListReq) {
		try {
			List<HunterInfoVo> hunterInfoVos = hunterListResource.queryNBHunter(hunterListReq);
			return XslResult.ok(hunterInfoVos);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
}
