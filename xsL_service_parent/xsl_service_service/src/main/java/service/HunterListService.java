package service;

import util.XslResult;
import vo.HunterListReq;


public interface HunterListService {
	XslResult queryHistoryHunter(HunterListReq hunterListReq);

	XslResult queryNBHunter(HunterListReq hunterListReq);
}
