package service;

import util.XslResult;

public interface SchoolService {
	/**
	 * 学校，大学，专业种类
	 *
	 * @return
	 */
	String schoolMessage();

	/**
	 * 学院种类
	 *
	 * @param school
	 * @return
	 */
	XslResult collegMessage(String school);

	/**
	 * 专业种类
	 *
	 * @param college
	 * @return
	 */
	XslResult majorMessage(String college,Integer schoolId);



}
