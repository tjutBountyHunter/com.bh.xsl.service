package service;

import com.xsl.user.vo.HunterListReq;
import util.XslResult;



public interface HunterListService {
	XslResult queryHistoryHunter(HunterListReq hunterListReq);

	XslResult queryNBHunter(HunterListReq hunterListReq);
}
