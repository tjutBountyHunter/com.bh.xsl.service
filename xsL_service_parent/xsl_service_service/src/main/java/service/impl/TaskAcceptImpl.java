package service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import dao.JedisClient;
import example.XslTaskExample;
import example.XslUserExample;
import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.*;
import service.*;
import util.Message;
import util.XslResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
