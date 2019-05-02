package service.impl;

import org.springframework.stereotype.Service;
import service.*;
import util.XslResult;

/**
 * 消息接收
 *
 * @author 高山潍
 */
@Service
public class TaskAcceptImpl implements TaskAccept {

	@Override
	public XslResult acceptTask(Integer hunterId, String taskId) {
		return null;
	}

	@Override
	public XslResult decidedTask(Integer hunterId, String taskId) {
		return null;
	}

	@Override
	public String oldTime(Integer hunterId, String taskId) {
		return null;
	}

	@Override
	public String timeDate() {
		return null;
	}
}
