package service.impl;

import mapper.ViewTaskStateMapper;
import mapper.XslTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslTask;
import service.ViewTaskStateService;
import util.XslResult;

import java.util.List;

/**
 * 个人悬赏模块
 */
@Service
public class ViewTaskStateServiceImpl implements ViewTaskStateService {

    @Autowired
    private ViewTaskStateMapper viewTaskStateMapper;

    @Autowired
    private XslTaskMapper xslTaskMapper;

    /**
     * 通过发送者ID来查询已发任务
     *
     * @param sendId
     * @return
     */
    @Override
    public XslResult getIssuedTaskBySendId(Integer sendId) {

        try {
            List<XslTask> taskList = viewTaskStateMapper.getIssuedTaskBySendId(sendId);
            return XslResult.ok(taskList);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 通过发送者ID来查询已接和待完成任务
     *
     * @param sendId
     * @return
     */
    @Override
    public XslResult getAccessTaskBySendId(Integer sendId) {
        try {
            List<XslTask> taskList = viewTaskStateMapper.getAccessTaskBySendId(sendId);
            return XslResult.ok(taskList);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    @Override
    public XslResult getcompleteTaskBySendId(Integer sendId) {
        try {
            List<XslTask> taskList = viewTaskStateMapper.getcompleteTaskBySendId(sendId);
            return XslResult.ok(taskList);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
}
