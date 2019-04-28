package service.impl;

import example.XslCollecthExample;
import example.XslCollecttExample;
import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.Collect;
import util.JsonUtils;
import util.XslResult;

import java.util.*;

/**
 * 收藏历史系列
 */
@Service
public class CollectImpl implements Collect {

	@Override
	public XslResult collectHunter(int hunterId, int userId) {
		return null;
	}

	@Override
	public XslResult collectTask(Integer userId, Integer taskId) {
		return null;
	}

	@Override
	public XslResult findcollectThunter(Integer userId, Integer page, Integer rows) {
		return null;
	}

	@Override
	public XslResult findcollectTask(Integer userId, Integer page, Integer rows) {
		return null;
	}
}
