package service;

import util.XslResult;
import vo.SchoolReqVo;

public interface SchoolService {
	/**
	 * 学校，大学，专业种类
	 *
	 * @return
	 */
	XslResult schoolMessage();

	/**
	 * 学院种类
	 *
	 * @param schoolReqVo
	 * @return
	 */
	XslResult collegMessage(SchoolReqVo schoolReqVo);

	/**
	 * 专业种类
	 *
	 * @param schoolReqVo
	 * @return
	 */
	XslResult majorMessage(SchoolReqVo schoolReqVo);


	XslResult delCache(SchoolReqVo schoolReqVo);



}
