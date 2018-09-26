package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public interface TaskAccept {
    XslResult acceptTask(String json);

    XslResult decidedTask(String json);

    String oldTime(String json);

    Date timeDate();
}
