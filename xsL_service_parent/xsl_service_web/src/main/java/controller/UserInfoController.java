package controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.UserService;
import util.XslResult;
import vo.XslUserReqVo;


@Controller
@RequestMapping("/xsl/user")
public class UserInfoController {
    @Autowired
    private UserService userService;


    /**
     * 上传头像
     *
     * @param uploadFile
     * @param userid
     * @return
     */
    @RequestMapping(value = "/userTx", method = RequestMethod.POST)
    @ResponseBody
    public XslResult fileUp(@Param("uploadFile") MultipartFile uploadFile, @Param("userid") String userid) {
        XslResult xslResult = userService.upLoadUserTx(uploadFile, userid);
        return xslResult;
    }


	/**
	 * 获取猎人雇主信息
	 *
	 * @param xslUserReqVo
	 * @return
	 */
	@RequestMapping(value = "/gethminfo")
	@ResponseBody
	public XslResult gethminfo(XslUserReqVo xslUserReqVo) {
		try {
			return userService.getHMinfo(xslUserReqVo);
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}
	}

	/**
	 * 获取猎人雇主信息
	 *
	 * @param xslUserReqVo
	 * @return
	 */
	@RequestMapping(value = "/saveUserInfo")
	@ResponseBody
	public XslResult saveUserInfo(XslUserReqVo xslUserReqVo) {
		try {
			return userService.saveUserInfo(xslUserReqVo);
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}
	}



}