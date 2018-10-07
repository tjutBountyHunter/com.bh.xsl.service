package service;

import pojo.XslDateTask;

public interface UpTaskService {

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

}
