package controller;

import com.xsl.user.vo.HunterListReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.HunterListService;
import util.XslResult;

/**
 * 猎人市场
 */
@Controller
@RequestMapping("/xsl/hunter")
public class HunterListController {
	@Autowired
	private HunterListService hunterListService;

	/**
	 * 历史猎人
	 * @param hunterListReq
	 * @return
	 */
	@RequestMapping("/queryHistoryHunter")
	@ResponseBody
	XslResult queryHistoryHunter(HunterListReq hunterListReq){
		return hunterListService.queryHistoryHunter(hunterListReq);
	}

	/**
	 * NB猎人
	 * @param hunterListReq
	 * @return
	 */
	@RequestMapping("/queryNBHunter")
	@ResponseBody
	XslResult queryNBHunter(HunterListReq hunterListReq){
		return hunterListService.queryNBHunter(hunterListReq);
	}




}
