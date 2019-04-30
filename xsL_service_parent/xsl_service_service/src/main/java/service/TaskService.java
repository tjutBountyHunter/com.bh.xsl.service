package service;

import util.XslResult;
import vo.TaskReqVo;

public interface TaskService {

    XslResult UpuseTask(String json);

    /**
     * 分页展示猎人种类
     *
     * @param tagName
     * @param type
     * @param rows
     * @return
     */
    XslResult UpCategoryHunter(String tagName, Integer type, Integer rows);

//    /**
//     * 猎人推优
//     *
//     * @param task_id
//     * @return
//     */
//    XslResult hunterDire(int task_id);

    /**
     * 发送任务
     *
     * @return
     */
    XslResult sendTask(TaskReqVo taskReqVo);

}
