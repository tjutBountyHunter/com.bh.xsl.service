package service.impl;

import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.*;
import util.*;
import vo.ImageVo;
import vo.TaskReqVo;
import vo.tagVo;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private SupplementDataService supplementDataService;
    @Autowired
    private TaskTopush taskTopush;
    @Autowired
    private HunterRecommend hunterRecommend;
    @Autowired
    private XslHuntershowMapper xslHuntershowMapper;
    @Autowired
    private XslHunterShopMapper xslHunterShopMapper;

	@Autowired
	private XslTaskMapper xslTaskMapper;
	@Autowired
	private XslTaskTagMapper xslTaskTagMapper;
	@Autowired
	private XslTaskFileMapper xslTaskFileMapper;

	@Autowired
	private XslDateTaskMapper xslDateTaskMapperr;

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

    /**
     * 猎人推优
     *
     * @param task_id
     * @return
     */
    @Override
    public XslResult hunterDire(int task_id) {
        try {
            int[] hunterid = hunterRecommend.recommend(task_id);
            List<XslOneHunter> list = new ArrayList<>();
            for (int i = 0; i < hunterid.length; i++) {
                XslOneHunter xslOneHunter = new XslOneHunter();
                Integer hunterId = hunterid[i];
                xslOneHunter = xslHunterShopMapper.selectByhunterId(hunterId);
                list.add(xslOneHunter);
            }
            return XslResult.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

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
			//文字扫描屏蔽
			Map<String, String> map = new HashMap<>(1);
			map.put("sentence", taskReqVo.getContent());
			String result = HttpClientUtil.doGet("http://47.93.19.164:8080/xsl-search-service/search/wordcheck", map);
			XslResult fcResult = XslResult.format(result);
			List<String> data = (List<String>) fcResult.getData();
			if (data != null && data.size() > 0) {
				return XslResult.build(400, "悬赏任务不合法");
			}


			//设置任务分类--默认全种类
			XslTask xslTask = new XslTask();
			xslTask.setCid(1);
            xslTask.setSendid(taskReqVo.getMasterId());
			xslTask.setTaskid(UuidTaskId.getUUID());
			xslTask.setContent(taskReqVo.getContent());
			xslTask.setMoney(taskReqVo.getMoney());
			xslTask.setTasktitle(taskReqVo.getTaskTitle());
			xslTask.setCreatedate(taskReqVo.getCreateDate());
			xslTask.setUpdatedate(taskReqVo.getCreateDate());
			xslTask.setDeadline(taskReqVo.getDeadLineDate());
			//未启动推荐
			xslTask.setState((byte) 0);
			//启动推荐
			if(taskReqVo.getRecommend()){
				xslTask.setState((byte) 1);
			}

			//记录任务
			int insert = xslTaskMapper.insert(xslTask);

			if(insert < 1){
				return XslResult.build(500, "服务器异常");
			}

			//发送mq到搜索系统
			searchTaskMQ searchTaskMQ = new searchTaskMQImpl();
			searchTaskMQ.addTaskJson(JsonUtils.objectToJson(xslTask));

			XslResult xslResultTag = addTaskTag(taskReqVo, xslTask.getTaskid());
			XslResult xslResultFile = addTaskFile(taskReqVo, xslTask.getTaskid());

			if(xslResultFile.isOK() && xslResultTag.isOK()){
				return XslResult.ok();
			}

			return XslResult.build(500, "服务器异常");
		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}
    }

	private XslResult addTaskFile(TaskReqVo taskReqVo, String taskId) {
		try {
			List<ImageVo> images = taskReqVo.getImages();
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
			List<XslTaskTag> xslTaskTags = new ArrayList<>();
			for (tagVo tagVo : tags){
				XslTaskTag xslTaskTag = new XslTaskTag();
				xslTaskTag.setTaskid(taskId);
				xslTaskTag.setTagid(tagVo.getTagId());
				xslTaskTags.add(xslTaskTag);
			}

			int i = xslTaskTagMapper.insertSelectiveBatch(xslTaskTags);
			if(i < xslTaskTags.size()){
				throw new RuntimeException();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return XslResult.build(500, "服务器异常");
		}

		return XslResult.ok();
	}


}
