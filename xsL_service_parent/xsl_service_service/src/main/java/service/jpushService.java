package service;

import vo.JPushVo;

public interface jpushService {

	int sendToRegistrationId(JPushVo jPushVo);

	int sendToAll(JPushVo jPushVo);
}
