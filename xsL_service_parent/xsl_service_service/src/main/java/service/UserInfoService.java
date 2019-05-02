package service;

import pojo.*;

public interface UserInfoService {
	 XslUser getUserInfo(String useid);

	 XslUser getUserInfoByHunterId(String hunterid);

	 XslUser getUserInfoMasterId(String masterid);

	 XslSchoolinfo getSchoolInfo(String schoolid);

	 XslHunter getHunterInfo(String hunterid);

	 XslMaster getMasterInfo(String masterid);

	 XslSchool getSchoolByName(String SchoolName);


}
