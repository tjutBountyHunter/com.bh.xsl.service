package service.impl;

import com.github.pagehelper.PageHelper;
import example.*;
import mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.*;
import example.XslTaskExample;
import service.*;
import util.*;
import vo.*;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private SupplementDataService supplementDataService;
    @Autowired
    private XslHuntershowMapper xslHuntershowMapper;

	@Autowired
	private XslTaskMapper xslTaskMapper;
	@Autowired
	private XslTaskTagMapper xslTaskTagMapper;
	@Autowired
	private XslTaskFileMapper xslTaskFileMapper;
	@Autowired
	private XslTagMapper xslTagMapper;
	@Autowired
	private XslUserMapper xslUserMapper;
	@Autowired
	private XslNetworkMapper xslNetworkMapper;
	@Autowired
	private XslHunterMapper xslHunterMapper;
	@Autowired
	private XslHunterTaskMapper xslHunterTaskMapper;
	@Autowired
	private XslSchoolTaskMapper xslSchoolTaskMapper;
	@Autowired
	private XslSchoolMapper xslSchoolMapper;

	@Autowired
	private HunterRecommend hunterRecommend;
	@Autowired
	private jpushService jpushService;
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${USER_INFO}")
	private String USER_INFO;
	@Value("${USER_HUNTER_INFO}")
	private String USER_HUNTER_INFO;
	@Value("${USER_MASTER_INFO}")
	private String USER_MASTER_INFO;

    /**
     * 分页展示分类猎人
     *
     * @param tagName 任务id
     * @param type    滑动类型
     * @param rows    所需要的条数
     * @return
     */
    @Override
    public XslResult UpCategoryHunter(String tagName, Integer type, Integer rows) {
        try {
            tagName = new String(tagName.getBytes("iso-8859-1"), "utf-8");
            Map<String, Object> map = new HashMap<>(2);
            map.put("tagName", tagName);
            map.put("rows", rows);
            List<XslOneHunter> oneHunterList = null;
            if (type == 0) {
                oneHunterList = xslHuntershowMapper.getXslHunterListfirst(map);
                return XslResult.ok(oneHunterList);
            } else if (type == 1) {
                oneHunterList = xslHuntershowMapper.getXslHunterOld(map);
                return XslResult.ok(oneHunterList);
            } else {
                oneHunterList = xslHuntershowMapper.getXslHunterNew(map);
                return XslResult.ok(oneHunterList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

//    /**
//     * 猎人推优
//     *
//     * @param task_id
//     * @return
//     */
//    @Override
//    public XslResult hunterDire(int task_id) {
//        try {
//            int[] hunterid = hunterRecommend.recommend(task_id);
//            List<XslOneHunter> list = new ArrayList<>();
//            for (int i = 0; i < hunterid.length; i++) {
//                XslOneHunter xslOneHunter = new XslOneHunter();
//                Integer hunterId = hunterid[i];
//                xslOneHunter = xslHunterShopMapper.selectByhunterId(hunterId);
//                list.add(xslOneHunter);
//            }
//            return XslResult.ok(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return XslResult.build(500, "服务器异常");
//        }
//    }

    @Override
    public XslResult UpuseTask(String json) {
        try {
            System.out.println(json);
            XslResult xslResult = null;
            xslResult = supplementDataService.SupplementTaskData(json);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    @Override
    public XslResult sendTask(TaskReqVo taskReqVo) {
		try {
			if(StringUtils.isEmpty(taskReqVo.getContent())){
				return XslResult.build(400, "参数错误");
			}
			//文字扫描屏蔽
			Map<String, String> map = new HashMap<>(1);
			map.put("sentence", taskReqVo.getContent());
			String result = HttpClientUtil.doGet("http://47.93.19.164:8080/xsl-search-service/search/wordcheck", map);
			XslResultOk fcResult = XslResultOk.format(result);
			List<String> data = (List<String>) fcResult.getData();
			if (data != null && data.size() > 0) {
				return XslResult.build(400, "悬赏任务不合法");
			}


			//设置任务分类--默认全种类
			XslTask xslTask = new XslTask();
			xslTask.setCid(1);
            xslTask.setSendid(taskReqVo.getMasterId());
			xslTask.setTaskid(UuidUtil.getUUID());
			xslTask.setContent(taskReqVo.getContent());
			xslTask.setMoney(taskReqVo.getMoney());
			xslTask.setTasktitle(taskReqVo.getTaskTitle());
			xslTask.setCreatedate(taskReqVo.getCreateDate());
			xslTask.setUpdatedate(taskReqVo.getCreateDate());
			xslTask.setDeadline(taskReqVo.getDeadLineDate());
			xslTask.setSourcetype(taskReqVo.getSourceType());
			//未启动推荐
			xslTask.setState((byte) 0);

			if(taskReqVo.getIsRecommend() == null){
				return XslResult.build(400, "参数错误");
			}

			//启动推荐
			if(taskReqVo.getIsRecommend()){
				xslTask.setState((byte) 1);
			}

			//记录任务
			int insert = xslTaskMapper.insertSelective(xslTask);

			if(insert < 1){
				return XslResult.build(500, "服务器异常");
			}

			//发送mq到搜索系统
			searchTaskMQ searchTaskMQ = new searchTaskMQImpl();
			searchTaskMQ.addTaskJson(JsonUtils.objectToJson(xslTask));

			XslResult xslResultTag = addTaskTag(taskReqVo, xslTask.getTaskid());
			XslResult xslResultFile = addTaskFile(taskReqVo, xslTask.getTaskid());
			XslResult xslResultSchool = addSchoolTask(taskReqVo, xslTask.getTaskid());

			if(xslResultFile.isOK() && xslResultTag.isOK() && xslResultSchool.isOK()){
				//异步启动推荐
				if(taskReqVo.getIsRecommend()){
					taskExecutor.execute(() -> hunterRecommendAndPush(xslTask));
				}
				return XslResult.ok(xslTask.getTaskid());
			}

			return XslResult.build(500, "服务器异常");
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}
    }

	private XslResult addSchoolTask(TaskReqVo taskReqVo, String taskid) {
		XslUser user = userInfoService.getUserInfoMasterId(taskReqVo.getMasterId());
		String schoolinfo = user.getSchoolinfo();
		XslSchoolinfo schoolInfo = userInfoService.getSchoolInfo(schoolinfo);
		String schoolName = schoolInfo.getSchool();
		XslSchool school = userInfoService.getSchoolByName(schoolName);

		XslSchoolTask xslSchoolTask = new XslSchoolTask();
		xslSchoolTask.setSchoolid(school.getId());
		xslSchoolTask.setTaskid(taskid);

		int insert = xslSchoolTaskMapper.insertSelective(xslSchoolTask);

		if(insert < 1){
			throw new RuntimeException("任务学校信息关联异常");
		}

		return XslResult.ok();
	}

	@Override
	public XslResult querySendTask(SendAndRecTaskReqVo sendAndRecTaskReqVo) {
		Integer page = sendAndRecTaskReqVo.getPage();
		Integer rows = sendAndRecTaskReqVo.getRows();

		PageHelper.startPage(page,rows);
		List<XslTask> taskList = xslTaskMapper.selectBySendId(sendAndRecTaskReqVo);

		return XslResult.ok(taskList);
	}

	@Override
	public XslResult queryReceiveTask(SendAndRecTaskReqVo sendAndRecTaskReqVo) {
		Integer page = sendAndRecTaskReqVo.getPage();
		Integer rows = sendAndRecTaskReqVo.getRows();

		PageHelper.startPage(page,rows);
		List<String> taskIds = xslHunterTaskMapper.selectByRecId(sendAndRecTaskReqVo);

		XslTaskExample xslTaskExample = new XslTaskExample();
		xslTaskExample.createCriteria().andTaskidIn(taskIds);
		List<XslTask> taskList = xslTaskMapper.selectByExample(xslTaskExample);

		return XslResult.ok(taskList);
	}

	@Override
	public XslResult initTaskInfo(TaskInfoListReqVo taskInfoListReqVo){
		//1.获取学校id
		String schoolName = taskInfoListReqVo.getSchoolName();
		XslSchool school = userInfoService.getSchoolByName(schoolName);
		if(school == null){
			return XslResult.build(403, "请重新选择学校");
		}
		Integer schoolId = school.getId();

		//2.获取学校id对应的任务
		Integer size = taskInfoListReqVo.getSize();
		PageHelper.startPage(1, size);
		List<String> taskIds = xslSchoolTaskMapper.selectTaskIdBySchoolId(schoolId);
		if(taskIds.size() == 0){
			return XslResult.ok();
		}

		TaskInfoListResVo taskInfoListResVo = new TaskInfoListResVo();
		PageHelper.startPage(1, size);
		List<Integer> ids = xslSchoolTaskMapper.selectIdBySchoolId(schoolId);
		Integer max = Collections.max(ids);
		Integer min = Collections.min(ids);
		taskInfoListResVo.setDownFlag(min);
		taskInfoListResVo.setUpFlag(max);

		//3.获取任务信息
		List<TaskInfoVo> taskInfoList = getTaskInfoList(taskIds);
		taskInfoListResVo.setTaskInfoVos(taskInfoList);

		return XslResult.ok(taskInfoListResVo);
	}

	@Override
	public XslResult reloadTaskInfo(TaskInfoListReqVo taskInfoListReqVo) {
		TaskInfoListResVo taskInfoListResVo = new TaskInfoListResVo();
		List<String> taskIds = null;

		//1.获取学校id对应的任务
		if("UP".equals(taskInfoListReqVo.getType())){
			Integer id = taskInfoListReqVo.getUpFlag();
			taskIds = xslSchoolTaskMapper.selectTaskIdByGreaterThanId(id);
		}

		if("DOWN".equals(taskInfoListReqVo.getType())){
			Integer id = taskInfoListReqVo.getDownFlag();
			taskIds = xslSchoolTaskMapper.selectTaskIdByLessThanSchoolId(id);
		}


		if(taskIds == null || taskIds.size() == 0){
			return XslResult.ok(taskInfoListResVo);
		}

		//2.获取学校id
		String schoolName = taskInfoListReqVo.getSchoolName();
		XslSchool school = userInfoService.getSchoolByName(schoolName);
		if(school == null){
			return XslResult.build(403, "请重新选择学校");
		}
		Integer schoolId = school.getId();

		//4.获取id列表
		Integer size = taskInfoListReqVo.getSize();
		PageHelper.startPage(1, size);
		List<Integer> ids = xslSchoolTaskMapper.selectIdBySchoolId(schoolId);

		if("UP".equals(taskInfoListReqVo.getType())){
			Integer max = Collections.max(ids);
			taskInfoListResVo.setUpFlag(max);
		}

		if("DOWN".equals(taskInfoListReqVo.getType())){
			Integer min = Collections.min(ids);
			taskInfoListResVo.setDownFlag(min);
		}


		//3.获取任务信息
		List<TaskInfoVo> taskInfoList = getTaskInfoList(taskIds);
		taskInfoListResVo.setTaskInfoVos(taskInfoList);

		return XslResult.ok(taskInfoListResVo);
	}

	@Override
	public XslResult receiveTask(RecTaskReqVo recTaskReqVo){
		String hunterid = recTaskReqVo.getHunterid();
		String taskid = recTaskReqVo.getTaskId();
		//1.判断用户状态
		XslUser userInfo = userInfoService.getUserInfo(hunterid);
		if(userInfo == null){
			return XslResult.build(403, "您无权操作");
		}
		if(1 != userInfo.getState()){
			return XslResult.build(403, "您无权操作");
		}

		//2.获取任务信息
		XslTaskExample xslTaskExample = new XslTaskExample();
		XslTaskExample.Criteria criteria = xslTaskExample.createCriteria();
		criteria.andTaskidEqualTo(taskid);
		List<XslTask> taskList = xslTaskMapper.selectByExample(xslTaskExample);
		if(taskList == null || taskList.size() < 1){
			return XslResult.build(500, "任务信息异常");
		}

		XslTask xslTask = taskList.get(0);

		String masterid = userInfo.getMasterid();

		if(masterid.equals(xslTask.getSendid())){
			return XslResult.build(403, "请不要接自己发送的任务");
		}
		Byte state = xslTask.getState();
		if(!(0 == state || 1 == state)){
			return XslResult.build(403, "任务已经被抢走");
		}

		xslTask.setState((byte) 2);
		if(state == 0){
			criteria.andStateEqualTo((byte) 0);
		}
		if(state == 1){
			criteria.andStateEqualTo((byte) 1);
		}
		int i = xslTaskMapper.updateByExampleSelective(xslTask, xslTaskExample);

		if(i < 1){
			return XslResult.build(403, "任务接收失败");
		}

		XslHunterTask xslHunterTask = new XslHunterTask();
		xslHunterTask.setHunterid(hunterid);
		xslHunterTask.setTaskid(recTaskReqVo.getTaskId());
		xslHunterTask.setTaskstate((byte) 2);
		int count = xslHunterTaskMapper.insertSelective(xslHunterTask);
		if(count < 1){
			return XslResult.build(403, "请不要接自己发送的任务");
		}

		return XslResult.ok();
	}


	private List<TaskInfoVo> getTaskInfoList(List<String> taskIds) {
		//3.获取任务信息
		XslTaskExample xslTaskExample = new XslTaskExample();
		xslTaskExample.createCriteria().andTaskidIn(taskIds);
		List<XslTask> taskList = xslTaskMapper.selectByExample(xslTaskExample);

		//4.封装返回数据
		List<TaskInfoVo> taskInfoVos = new ArrayList<>();
		for (XslTask xslTask : taskList) {
			TaskInfoVo taskInfoVo = new TaskInfoVo();
			String masterId = xslTask.getSendid();
			XslMaster masterInfo = userInfoService.getMasterInfo(masterId);
			XslUser userInfo = userInfoService.getUserInfoMasterId(masterId);

			//获取任务标签
			String taskid = xslTask.getTaskid();
			XslTaskTagExample xslTaskTagExample = new XslTaskTagExample();
			xslTaskTagExample.createCriteria().andTaskidEqualTo(taskid);
			List<String> tagIds = xslTaskTagMapper.selectTagIdByExample(xslTaskTagExample);

			XslTagExample xslTagExample = new XslTagExample();
			xslTagExample.createCriteria().andTagidIn(tagIds);
			List<XslTag> xslTags = xslTagMapper.selectByExample(xslTagExample);

			BeanUtils.copyProperties(xslTask, taskInfoVo);
			BeanUtils.copyProperties(masterInfo, taskInfoVo);
			taskInfoVo.setMasterlevel(masterInfo.getLevel());
			BeanUtils.copyProperties(userInfo, taskInfoVo);
			taskInfoVo.setTaskId(xslTask.getTaskid());
			taskInfoVo.setTaskTitle(xslTask.getTasktitle());
			taskInfoVo.setCreateDate(xslTask.getCreatedate());
			taskInfoVo.setTxUrl("http://47.93.200.190/images/default.png");
			taskInfoVo.setMasterlevel(masterInfo.getLevel());
			taskInfoVo.setMasterId(xslTask.getSendid());
			taskInfoVo.setDeadLineDate(xslTask.getDeadline());
			taskInfoVo.setTags(xslTags);
			taskInfoVos.add(taskInfoVo);
		}

		return taskInfoVos;
	}

	private XslResult addTaskFile(TaskReqVo taskReqVo, String taskId) {
    	try {
			List<ImageVo> images = taskReqVo.getImages();

			if(images.size() < 1){
				return XslResult.ok();
			}

			List<XslTaskFile> xslTaskFiles = new ArrayList<>();
			for (ImageVo imageVo : images){
				XslTaskFile xslTaskFile = new XslTaskFile();
				xslTaskFile.setTaskid(taskId);
				xslTaskFile.setFileid(imageVo.getImageId());
				xslTaskFiles.add(xslTaskFile);
			}

			int i = xslTaskFileMapper.insertSelectiveBatch(xslTaskFiles);
			if(i < xslTaskFiles.size()){
				throw new RuntimeException();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}

		return XslResult.ok();


	}

	/**
	 * 添加任务标签关系数据
	 */
	private XslResult addTaskTag(TaskReqVo taskReqVo, String taskId) {
		try {
			List<tagVo> tags = taskReqVo.getTags();

			if(tags.size() < 1){
				return XslResult.ok();
			}

			List<XslTaskTag> xslTaskTags = new ArrayList<>();
			for (tagVo tagVo : tags){
				XslTaskTag xslTaskTag = new XslTaskTag();
				xslTaskTag.setTaskid(taskId);
				xslTaskTag.setTagid(tagVo.getTagid());
				xslTaskTags.add(xslTaskTag);
			}

			int i = xslTaskTagMapper.insertSelectiveBatch(xslTaskTags);
			if(i < xslTaskTags.size()){
				throw new RuntimeException();
			}

			//异步去处理标签使用的次数
			taskExecutor.execute(() -> updateTagNum(taskReqVo.getTags()));

		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}

		return XslResult.ok();
	}

	private XslResult updateTagNum(List<tagVo> tags){
		List<String> tagIds = new ArrayList<>(tags.size());
		for (tagVo tagVo : tags){
			tagIds.add(tagVo.getTagid());
		}

		XslTagExample xslTagExample = new XslTagExample();
		XslTagExample.Criteria criteria = xslTagExample.createCriteria();
		criteria.andTagidIn(tagIds);
		int i = xslTagMapper.updateUseNumByExample(xslTagExample);
		if(i < 1){
			throw new RuntimeException();
		}
		return XslResult.ok();
	}


	private XslResult hunterRecommendAndPush(XslTask xslTask){

		List<String> recommend;
		//猎人标签推优算法
		recommend = hunterRecommend.recommend(xslTask.getTaskid(), 10);

		if(recommend == null || recommend.size() == 0){
			//血缘关系推荐算法启动
			Set<String> hunters = networkHunter(xslTask);

			recommend.addAll(hunters);

			if(hunters.size() == 0){
				 recommend = getGoodHunter();
			}

		}

		JPushVo jPushVo = new JPushVo();
		jPushVo.setMsgTitle("悬赏任务推荐");
		jPushVo.setMsgContent("有一个适合你的悬赏任务");
		jPushVo.setNotificationTitle("悬赏任务推荐");
		jPushVo.setExtrasparam("");

		for (String hunterId : recommend){
			//查电话号码
			XslUserExample xslUserExample = new XslUserExample();

			xslUserExample.createCriteria().andHunteridEqualTo(hunterId);
			List<XslUser> xslUsers = xslUserMapper.selectByExample(xslUserExample);
			String phone = xslUsers.get(0).getPhone();
			//获取设备码
			String s = JedisClientUtil.get(REDIS_USER_SESSION_KEY + ":" + phone);
			jPushVo.setRegistrationId(s);
			//发推送
			jpushService.sendToRegistrationId(jPushVo);
		}

		return XslResult.ok();
	}

	private List<String> getGoodHunter() {
		return xslHunterMapper.selectGoodHunter();
	}

	private Set<String> networkHunter(XslTask xslTask) {
		//1.获取用户ID
		XslUserExample xslUserExample = new XslUserExample();
		String masterId = xslTask.getSendid();
		xslUserExample.createCriteria().andMasteridEqualTo(masterId);
		List<XslUser> xslUsers = xslUserMapper.selectByExample(xslUserExample);
		String userId = xslUsers.get(0).getUserid();

		//2.符合条件的用户
		Set<String> hunterIds = new HashSet<>();
		XslNetworkExample xslNetworkExample = new XslNetworkExample();
		xslNetworkExample.createCriteria().andAidEqualTo(userId);
		List<XslNetwork> xslNetworkAs = xslNetworkMapper.selectByExample(xslNetworkExample);
		for(XslNetwork xslNetworkA : xslNetworkAs){
			hunterIds.add(xslNetworkA.getBid());
		}

		xslNetworkExample.createCriteria().andBidEqualTo(userId);
		List<XslNetwork> xslNetworkBs = xslNetworkMapper.selectByExample(xslNetworkExample);

		for(XslNetwork xslNetworkB : xslNetworkBs){
			hunterIds.add(xslNetworkB.getAid());
		}

		return hunterIds;
	}

}
