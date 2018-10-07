package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public interface TaskAccept {
    XslResult acceptTask(Integer hunterId, String taskId);

    XslResult decidedTask(Integer hunterId, String taskId);

    String oldTime(Integer hunterId, String taskId);

    String timeDate();
}
