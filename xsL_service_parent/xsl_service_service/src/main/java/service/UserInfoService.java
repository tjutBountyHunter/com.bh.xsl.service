package service;

import pojo.*;

public interface UserInfoService {
	 XslUser getUserInfo(String useid);

	 XslSchoolinfo getSchoolInfo(String schoolid);

	 XslHunter getHunterInfo(String userid, String hunterid);

	 XslMaster getMasterInfo(String userid, String masterid);

	 XslSchool getSchoolByName(String SchoolName);


}
