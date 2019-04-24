package service;

import util.XslResult;

public interface TaskAccept {
    XslResult acceptTask(Integer hunterId, String taskId);

    XslResult decidedTask(Integer hunterId, String taskId);

    String oldTime(Integer hunterId, String taskId);

    String timeDate();
}
