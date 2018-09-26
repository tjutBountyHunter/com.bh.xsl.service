package mapper;

import pojo.XslTask;
import pojo.XslTaskPosh;

import java.util.List;
import java.util.Map;

public interface XslTaskshowMapper {
    List<XslTask> getXslTaskList(Map<String, Object> map);

    Integer getXslTaskCount(Map<String, Object> map);

    List<XslTaskPosh> getXslTaskListC(Map<String, Object> map);

    Integer getXslTaskCountC(Map<String, Object> map);
}
