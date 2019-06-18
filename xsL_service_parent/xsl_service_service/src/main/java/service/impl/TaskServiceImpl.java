package service.impl;

import com.github.pagehelper.PageHelper;
import com.xsl.search.export.SearchResource;
import com.xsl.search.export.vo.TaskInfoVo;
import com.xsl.search.export.vo.TaskSearchReqVo;
import com.xsl.user.LevelResource;
import com.xsl.user.vo.NetworkReqVo;
import com.xsl.user.vo.UserLevelReqVo;
import example.*;
import mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.*;
import service.*;
import util.*;
import vo.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {
	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

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

	@Resource
	private LevelResource levelResource;

	@Autowired
	private HunterRecommend hunterRecommend;
	@Autowired
	private jpushService jpushService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private TaskMqService taskMqService;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Resource
	private SearchResource searchResource;



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

    @Override
    public XslResult sendTask(TaskReqVo taskReqVo) {
		logger.info("sendTask param:{}", GsonSingle.getGson().toJson(taskReqVo));
		try {
			if(StringUtils.isEmpty(taskReqVo.getContent())){
				return XslResult.build(400, "参数错误");
			}
			//文字扫描屏蔽
			Map<String, String> map = new HashMap<>(1);
			map.put("sentence", taskReqVo.getContent());
			if(taskReqVo.getContent().contains("代课")||taskReqVo.getContent().contains("车队")){
				return XslResult.build(403, "悬赏任务不合法");
			}

//			String result = HttpClientUtil.doGet("http://47.93.19.164:8080/xsl-search-service/search/wordcheck", map);
//			XslResultOk fcResult = XslResultOk.format(result);
//			List<String> data = (List<String>) fcResult.getData();
//			if (data != null && data.size() > 0) {
//				return XslResult.build(400, "悬赏任务不合法");
//			}


			//设置任务分类--默认全种类
			XslTask xslTask = new XslTask();
			xslTask.setCid(1);
            xslTask.setSendid(taskReqVo.getMasterId());
			xslTask.setTaskid(UuidUtil.getUUID());
			xslTask.setContent(taskReqVo.getContent());
			xslTask.setMoney(taskReqVo.getMoney());
			xslTask.setTasktitle(taskReqVo.getTaskTitle());
			xslTask.setCreatedate(DateUtil.stringToDate(taskReqVo.getCreateDate()));
			xslTask.setUpdatedate(DateUtil.stringToDate(taskReqVo.getCreateDate()));
			xslTask.setDeadline(DateUtil.stringToDate(taskReqVo.getDeadLineDate()));
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

			XslResult xslResultTag = addTaskTag(taskReqVo, xslTask.getTaskid());
			XslResult xslResultFile = addTaskFile(taskReqVo, xslTask.getTaskid());
			XslResult xslResultSchool = addSchoolTask(taskReqVo, xslTask.getTaskid());

			if(xslResultFile.isOK() && xslResultTag.isOK() && xslResultSchool.isOK()){
				//异步启动推荐
				if(taskReqVo.getIsRecommend()){
					taskExecutor.execute(() -> hunterRecommendAndPush(xslTask));
				}

				UserLevelReqVo userLevelReqVo = new UserLevelReqVo();
				userLevelReqVo.setMasterId(taskReqVo.getMasterId());
				logger.info("UserLevelReqVo:"+GsonSingle.getGson().toJson(userLevelReqVo));
				//异步更新雇主信息
				taskExecutor.execute(() -> levelResource.AddEmpirical(userLevelReqVo));

				//异步封装数据发送mq到搜索系统
				taskExecutor.execute(() -> sendTaskInfoToSearch(xslTask));

				return XslResult.ok(xslTask.getTaskid());
			}

			return XslResult.build(500, "服务器异常");
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}
    }

	private void sendTaskInfoToSearch(XslTask xslTask) {
    	TaskInfo taskInfoVo = initTaskInfo(xslTask);
    	TaskEsInfo taskEsInfo = new TaskEsInfo();
    	BeanUtils.copyProperties(taskInfoVo, taskEsInfo);
    	logger.info("sendTaskInfoToSearch:"+ GsonSingle.getGson().toJson(taskEsInfo));
		taskMqService.addEsTask(taskEsInfo);
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

		if(!ListUtil.isNotEmpty(taskList)){
			return XslResult.ok();
		}

		ArrayList<SendRecTask> sendRecTasks = taskList.stream().collect(ArrayList::new, (res, item) -> res.add(initSendRecVo(item)), ArrayList::addAll);


		return XslResult.ok(sendRecTasks);
	}

	@Override
	public XslResult queryReceiveTask(SendAndRecTaskReqVo sendAndRecTaskReqVo) {
		Integer page = sendAndRecTaskReqVo.getPage();
		Integer rows = sendAndRecTaskReqVo.getRows();

		PageHelper.startPage(page,rows);
		List<String> taskIds = xslHunterTaskMapper.selectByRecId(sendAndRecTaskReqVo);

		if(ListUtil.isNotEmpty(taskIds)){
			XslTaskExample xslTaskExample = new XslTaskExample();
			xslTaskExample.createCriteria().andTaskidIn(taskIds);
			List<XslTask> taskList = xslTaskMapper.selectByExample(xslTaskExample);
			ArrayList<SendRecTask> sendRecTasks = taskList.stream().collect(ArrayList::new, (res, item) -> res.add(initSendRecVo(item)), ArrayList::addAll);
			return XslResult.ok(sendRecTasks);
		}

		return XslResult.ok();
	}

	private SendRecTask initSendRecVo(XslTask xslTask){
		SendRecTask sendRecTask = new SendRecTask();
    	BeanUtils.copyProperties(xslTask, sendRecTask);
		sendRecTask.setCreatedate(DateUtil.dateToString(xslTask.getCreatedate()));
		sendRecTask.setUpdatedate(DateUtil.dateToString(xslTask.getUpdatedate()));
		sendRecTask.setDeadline(DateUtil.dateToString(xslTask.getDeadline()));
    	return sendRecTask;
	}

	@Override
	public XslResult initTaskInfo(TaskInfoListReqVo taskInfoListReqVo){
		//1.获取学校id
		logger.info("initTaskInfo:"+GsonSingle.getGson().toJson(taskInfoListReqVo));
		String schoolName = taskInfoListReqVo.getSchoolName();
		if(StringUtils.isEmpty(schoolName) || "Empty".equals(schoolName)){
			return XslResult.build(403, "请登记学校信息");
		}
		Integer size = taskInfoListReqVo.getSize();
		XslSchool school = userInfoService.getSchoolByName(schoolName);
		if(school == null){
			return XslResult.build(403, "请重新选择学校");
		}
		Integer schoolId = school.getId();

		TaskInfoListResVo taskInfoListResVo = new TaskInfoListResVo();

		PageHelper.startPage(1, size);
		List<Integer> ids = xslSchoolTaskMapper.selectIdBySchoolId(schoolId);

		if(!ListUtil.isNotEmpty(ids)){
			return XslResult.ok();
		}

		Integer	max = Collections.max(ids);
		Integer	min = Collections.min(ids);


		taskInfoListResVo.setDownFlag(min);
		taskInfoListResVo.setUpFlag(max);

		//3.获取任务信息

		SearchTaskReqVo taskSearchVo = new SearchTaskReqVo();
		taskSearchVo.setSize(size);
		taskSearchVo.setKeyword("");
		taskSearchVo.setSchoolName(schoolName);
		XslResult xslResult = searchTask(taskSearchVo);

		taskInfoListResVo.setTaskInfoVos(new ArrayList<>());
		List<TaskInfo> data = (List<TaskInfo>) xslResult.getData();
		if(ListUtil.isNotEmpty(data)){
			taskInfoListResVo.setTaskInfoVos(data);
		}

		logger.info("initTaskInfo.taskInfoListResVo msg:" + GsonSingle.getGson().toJson(taskInfoListResVo));

		return XslResult.ok(taskInfoListResVo);
	}

	private List<String> getSchoolTaskIds(String schoolName, Integer size){
    	XslSchool school = userInfoService.getSchoolByName(schoolName);
		if(school == null){
			return new ArrayList<>();
		}
		Integer schoolId = school.getId();

		//2.获取学校id对应的任务
		PageHelper.startPage(1, size);
		List<String> taskIds = xslSchoolTaskMapper.selectTaskIdBySchoolId(schoolId);
		if(taskIds.size() == 0){
			return new ArrayList<>();
		}

		return taskIds;
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
		List<TaskInfo> taskInfoList = getTaskInfoList(taskIds);
		taskInfoListResVo.setTaskInfoVos(taskInfoList);

		return XslResult.ok(taskInfoListResVo);
	}

	@Override
	public XslResult receiveTask(RecTaskReqVo recTaskReqVo){
		String hunterid = recTaskReqVo.getHunterid();
		String taskid = recTaskReqVo.getTaskId();
		//1.判断用户状态
		XslUser userInfo = userInfoService.getUserInfoByHunterId(hunterid);
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

		//异步生成订单
		taskExecutor.execute(() ->createOrder(hunterid, taskid));

		//异步给雇主发推送
		String masterId = xslTask.getSendid();
		XslUser userInfoMasterId = userInfoService.getUserInfoMasterId(masterId);
		JPushVo jPushVo = new JPushVo();
		jPushVo.setMsgTitle("任务状态提醒");
		jPushVo.setMsgContent("你发布的任务《"+xslTask.getTasktitle()+"》已被接");
		jPushVo.setNotificationTitle("你发布的任务《"+xslTask.getTasktitle()+"》已被接");
		jPushVo.setExtrasparam(xslTask.getTaskid());
		sendToMessage(jPushVo, userInfoMasterId.getPhone());

		taskExecutor.execute(()-> updateEsTaskInfo(xslTask));
		taskExecutor.execute(() -> updateNetwork(hunterid, masterId));

		return XslResult.ok();
	}

	private void createOrder(String hunterId, String taskId){
		CreateOrderReqVo createOrderReqVo=new CreateOrderReqVo();
		createOrderReqVo.setHunterId(hunterId);
		createOrderReqVo.setTaskId(taskId);
		taskMqService.createOrder(createOrderReqVo);
	}

	private void updateEsTaskInfo(XslTask xslTask){
		UpdateTaskVo updateTaskVo = new UpdateTaskVo();
		updateTaskVo.setState(xslTask.getState());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		updateTaskVo.setUpdatedate(sdf.format(new Date()));
		updateTaskVo.setTaskId(xslTask.getTaskid());
		taskMqService.updateEsTask(updateTaskVo);
	}

	private void updateNetwork(String hunterId, String masterId){
		NetworkReqVo networkReqVo = new NetworkReqVo();
		networkReqVo.setHunterId(hunterId);
		networkReqVo.setMasterId(masterId);

		String s = GsonSingle.getGson().toJson(networkReqVo);
		taskMqService.updateNetwork(s);
	}

	@Override
	public XslResult taskInfo(String taskId){
		if(StringUtils.isEmpty(taskId)){
			return XslResult.build(403, "任务不存在");
		}

		//获取任务信息
		XslTaskExample xslTaskExample = new XslTaskExample();
		xslTaskExample.createCriteria().andTaskidEqualTo(taskId);
		List<XslTask> taskList = xslTaskMapper.selectByExample(xslTaskExample);

		if(taskList == null || taskList.size() == 0){
			return XslResult.build(403, "任务不存在");
		}

		TaskInfoResVo taskInfoResVo = new TaskInfoResVo();
		XslTask xslTask = taskList.get(0);
		BeanUtils.copyProperties(xslTask, taskInfoResVo);
		taskInfoResVo.setTaskId(xslTask.getTaskid());
		taskInfoResVo.setTaskTitle(xslTask.getTasktitle());
		taskInfoResVo.setCreateDate(DateUtil.dateToString(xslTask.getCreatedate()));
		taskInfoResVo.setDeadLineDate(DateUtil.dateToString(xslTask.getDeadline()));

		//获取任务标签id
		XslTaskTagExample xslTaskTagExample = new XslTaskTagExample();
		xslTaskTagExample.createCriteria().andTaskidEqualTo(taskId);
		List<String> tagids = xslTaskTagMapper.selectTagidByExample(xslTaskTagExample);
		if(tagids != null && tagids.size() > 0){
			//获取任务标签
			XslTagExample xslTagExample = new XslTagExample();
			xslTagExample.createCriteria().andTagidIn(tagids);
			List<XslTag> xslTags = xslTagMapper.selectByExample(xslTagExample);
			if(xslTags != null && xslTags.size() > 0){
				taskInfoResVo.setTags(xslTags);
			}
		}

		//获取雇主信息
		String masterId = xslTask.getSendid();
		XslMaster master = userInfoService.getMasterInfo(masterId);
		MasterInfo masterInfo = new MasterInfo();
		BeanUtils.copyProperties(master, masterInfo);
		masterInfo.setTxUrl("http://47.93.200.190/images/default.png");
		String userTx = userInfoService.getUserTx(masterInfo.getUserid());
		if(!StringUtils.isEmpty(userTx)){
			masterInfo.setTxUrl(userTx);
		}
		//获取手机号
		XslUser userInfo = userInfoService.getUserInfoMasterId(masterId);
		masterInfo.setName(userInfo.getName());
		masterInfo.setPhone(userInfo.getPhone());
		taskInfoResVo.setMasterInfo(masterInfo);

		//获取猎人信息
		if(2 == xslTask.getState() || 4 == xslTask.getState()){
			//获取猎人id(在高并发环境下，这种代码肯定有问题)
			XslHunterTaskExample xslHunterTaskExample = new XslHunterTaskExample();
			xslHunterTaskExample.createCriteria().andTaskidEqualTo(taskId);
			List<XslHunterTask> xslHunterTasks = xslHunterTaskMapper.selectByExample(xslHunterTaskExample);
			String hunterId = xslHunterTasks.get(0).getHunterid();

			//获取猎人信息
			HunterInfo hunterInfo = getHunterInfo(hunterId);
			taskInfoResVo.setHunterInfo(hunterInfo);
		}

		return XslResult.ok(taskInfoResVo);
	}

	@Override
	public XslResult confirmTask(ConfirmTaskReqVo confirmTaskReqVo) {
		Byte nowState = confirmTaskReqVo.getNowState();
		Byte afterState = confirmTaskReqVo.getAfterState();
		String taskId = confirmTaskReqVo.getTaskId();
    	String hunterId = confirmTaskReqVo.getHunterid();
		//检测任务状态
		XslTaskExample xslTaskExample = new XslTaskExample();
		xslTaskExample.createCriteria().andTaskidEqualTo(taskId);
		List<XslTask> taskList = xslTaskMapper.selectByExample(xslTaskExample);

		if(taskList == null || taskList.size() == 0){
			return XslResult.build(403, "任务不存在");
		}
		XslTask xslTask = taskList.get(0);
		if(nowState != xslTask.getState()){
			return XslResult.build(403, "请勿重复操作");
		}

		//检测连接状态
		XslHunterTaskExample xslHunterTaskExample = new XslHunterTaskExample();
		xslHunterTaskExample.createCriteria().andHunteridEqualTo(hunterId).andTaskidEqualTo(taskId);
		List<XslHunterTask> xslHunterTasks = xslHunterTaskMapper.selectByExample(xslHunterTaskExample);

		if(xslHunterTasks == null || xslHunterTasks.size() == 0){
			return XslResult.build(403, "猎人信息有误");
		}
		XslHunterTask xslHunterTask = xslHunterTasks.get(0);
		if(nowState != xslHunterTask.getTaskstate()){
			return XslResult.build(403, "请勿重复操作");
		}

		//更新任务状态
		xslTask.setState(afterState);
		int i = xslTaskMapper.updateByExampleSelective(xslTask, xslTaskExample);
		if(i < 1){
			return XslResult.build(500, "服务器异常");
		}

		//更新连接状态
		xslHunterTask.setTaskstate(afterState);
		int i1 = xslHunterTaskMapper.updateByExampleSelective(xslHunterTask, xslHunterTaskExample);
		if(i1 < 1){
			return XslResult.build(500, "服务器异常");
		}

		//猎人确认完成
		if(4 == afterState){
			//异步给雇主发推送
			String masterId = xslTask.getSendid();
			XslUser userInfoMasterId = userInfoService.getUserInfoMasterId(masterId);
			JPushVo jPushVo = new JPushVo();
			jPushVo.setMsgTitle("任务完成提醒");
			jPushVo.setMsgContent("你发布的任务《"+xslTask.getTasktitle()+"》已完成");
			jPushVo.setNotificationTitle("你发布的任务《"+xslTask.getTasktitle()+"》已完成");
			jPushVo.setExtrasparam(taskId);
			sendToMessage(jPushVo, userInfoMasterId.getPhone());
		}

		//雇主确认完成
		if(3 == afterState){
			UserLevelReqVo userLevelReqVo = new UserLevelReqVo();
			userLevelReqVo.setMasterId(confirmTaskReqVo.getHunterid());
			//异步增加经验
			taskExecutor.execute(() -> levelResource.AddEmpirical(userLevelReqVo));

			//给猎人发推送
			XslUser userInfoMasterId = userInfoService.getUserInfoByHunterId(hunterId);
			JPushVo jPushVo = new JPushVo();
			jPushVo.setMsgTitle("任务完成提醒");
			jPushVo.setMsgContent("你接收的任务《"+xslTask.getTasktitle()+"》雇主已完成确认");
			jPushVo.setNotificationTitle("你接收的任务《"+xslTask.getTasktitle()+"》雇主已完成确认");
			jPushVo.setExtrasparam(taskId);
			sendToMessage(jPushVo, userInfoMasterId.getPhone());

			//任务终结处理订单
		}
		HunterInfo hunterInfo = getHunterInfo(hunterId);

		//异步更新搜索库状态
		UpdateTaskVo updateTaskVo = new UpdateTaskVo();
		updateTaskVo.setState(nowState);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		updateTaskVo.setUpdatedate(sdf.format(new Date()));
		updateTaskVo.setTaskId(xslTask.getTaskid());
		taskExecutor.execute(()-> taskMqService.updateEsTask(updateTaskVo));

		return XslResult.ok(hunterInfo);
	}

	@Override
	public XslResult searchTask(SearchTaskReqVo taskSearchVo){
    	String schoolName = taskSearchVo.getSchoolName();
		int size = taskSearchVo.getSize();
		int idSize = 1000;
		List<String> schoolTaskIds = getSchoolTaskIds(schoolName, idSize);

		TaskSearchReqVo taskSearchReqVo = new TaskSearchReqVo();
		taskSearchReqVo.setKeyword(taskSearchVo.getKeyword());
		taskSearchReqVo.setSize(size);
		taskSearchReqVo.setTaskIds(schoolTaskIds);
		List<TaskInfoVo> taskInfoVos = searchResource.searchTask(taskSearchReqVo);

		if(!ListUtil.isNotEmpty(taskInfoVos)){
			return XslResult.ok();
		}

		List<TaskInfo> taskInfos = new ArrayList<>();
		for (TaskInfoVo taskInfoVo : taskInfoVos){
			TaskInfo taskInfo = new TaskInfo();
			BeanUtils.copyProperties(taskInfoVo, taskInfo);
			String taskid = taskInfoVo.getTaskId();
			List<XslTag> taskTags = taskInfoService.getTaskTags(taskid);

			List<tagVo> tagVos = new ArrayList<>();
			if(ListUtil.isNotEmpty(taskTags)){
				for (XslTag xslTag : taskTags){
					tagVo tagVo = new tagVo();
					tagVo.setTagName(xslTag.getName());
					tagVo.setTagid(xslTag.getTagid());
					tagVos.add(tagVo);
				}

			}

			taskInfo.setTags(tagVos);
			taskInfos.add(taskInfo);
		}

		return XslResult.ok(taskInfos);
	}

	@Override
	public XslResult cancelTask(String taskId) {
		//检测任务状态
		XslTaskExample xslTaskExample = new XslTaskExample();
		xslTaskExample.createCriteria().andTaskidEqualTo(taskId);
		List<XslTask> taskList = xslTaskMapper.selectByExample(xslTaskExample);

		if(taskList == null || taskList.size() == 0){
			return XslResult.build(403, "任务不存在");
		}
		XslTask xslTask = taskList.get(0);
		if(0 != xslTask.getState() && 1 != xslTask.getState()){
			return XslResult.build(403, "任务已被接，无法取消");
		}

		//更新任务状态
		xslTask.setState((byte) -2);
		int i = xslTaskMapper.updateByExampleSelective(xslTask, xslTaskExample);
		if(i < 1){
			return XslResult.build(500, "服务器异常");
		}

		taskExecutor.execute(()-> updateEsTaskInfo(xslTask));

		return XslResult.ok();
	}

	private HunterInfo getHunterInfo(String hunterId) {
		//获取猎人信息
		XslHunter hunter = userInfoService.getHunterInfo(hunterId);
		HunterInfo hunterInfo = new HunterInfo();
		BeanUtils.copyProperties(hunter, hunterInfo);
		XslUser user = userInfoService.getUserInfoByHunterId(hunterId);
		hunterInfo.setPhone(user.getPhone());
		hunterInfo.setName(user.getName());
		hunterInfo.setTxUrl("http://47.93.200.190/images/default.png");

		String userTx = userInfoService.getUserTx(hunter.getUserid());
		if(!StringUtils.isEmpty(userTx)){
			hunterInfo.setTxUrl(userTx);
		}

		return hunterInfo;
	}

	private List<TaskInfo> getTaskInfoList(List<String> taskIds) {
		//3.获取任务信息
		XslTaskExample xslTaskExample = new XslTaskExample();
		List<Byte> status = new ArrayList<>();
		status.add((byte) 0);
		status.add((byte) 1);
		xslTaskExample.createCriteria().andTaskidIn(taskIds).andStateIn(status);
		List<XslTask> taskList = xslTaskMapper.selectByExample(xslTaskExample);

		//4.封装返回数据
		List<TaskInfo> taskInfoVos = new ArrayList<>();
		for (XslTask xslTask : taskList) {
			TaskInfo taskInfoVo = initTaskInfo(xslTask);
			taskInfoVos.add(taskInfoVo);
		}

		return taskInfoVos;
	}

	private TaskInfo initTaskInfo(XslTask xslTask) {
		TaskInfo taskInfoVo = new TaskInfo();
		String masterId = xslTask.getSendid();
		XslMaster masterInfo = userInfoService.getMasterInfo(masterId);
		XslUser userInfo = userInfoService.getUserInfoMasterId(masterId);

		//获取任务标签
		String taskid = xslTask.getTaskid();
		List taskTags = taskInfoService.getTaskTags(taskid);

		BeanUtils.copyProperties(xslTask, taskInfoVo);
		BeanUtils.copyProperties(masterInfo, taskInfoVo);
		taskInfoVo.setMasterlevel(masterInfo.getLevel());
		BeanUtils.copyProperties(userInfo, taskInfoVo);
		taskInfoVo.setTaskId(xslTask.getTaskid());
		taskInfoVo.setTaskTitle(xslTask.getTasktitle());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		taskInfoVo.setCreateDate(sdf.format(xslTask.getCreatedate()));
		taskInfoVo.setTxUrl("http://47.93.200.190/images/default.png");
		String userTx = userInfoService.getUserTx(masterInfo.getUserid());
		if(!StringUtils.isEmpty(userTx)){
			taskInfoVo.setTxUrl(userTx);
		}
		taskInfoVo.setMasterlevel(masterInfo.getLevel());
		taskInfoVo.setMasterId(xslTask.getSendid());
		taskInfoVo.setDeadLineDate(sdf.format(xslTask.getDeadline()));
		taskInfoVo.setUpdatedate(sdf.format(xslTask.getUpdatedate()));
		taskInfoVo.setTags(taskTags);
		return taskInfoVo;
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
		jPushVo.setNotificationTitle("有一个适合你的悬赏任务");
		jPushVo.setExtrasparam(xslTask.getTaskid());

		String myHunterId = userInfoService.getUserInfoMasterId(xslTask.getSendid()).getHunterid();

		for (String hunterId : recommend){
			if(hunterId.equals(myHunterId)){
				continue;
			}
			//查电话号码
			XslUserExample xslUserExample = new XslUserExample();
			xslUserExample.createCriteria().andHunteridEqualTo(hunterId);
			List<XslUser> xslUsers = xslUserMapper.selectByExample(xslUserExample);
			if(xslTask != null && xslUsers.size() > 0){
				String phone = xslUsers.get(0).getPhone();
				sendToMessage(jPushVo, phone);
			}
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

	private void sendToMessage(JPushVo jPushVo, String phone){
		//获取设备码
		String s = JedisClientUtil.get(REDIS_USER_SESSION_KEY + ":" + phone);
		jPushVo.setRegistrationId(s);
		if(StringUtils.isEmpty(s)){
			return;
		}
		//发推送
		jpushService.sendToRegistrationId(jPushVo);
	}

}
