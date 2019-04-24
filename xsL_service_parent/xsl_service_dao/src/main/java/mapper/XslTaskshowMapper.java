package mapper;

import pojo.XslTask;
import pojo.XslTaskPosh;

import java.util.List;
import java.util.Map;

public interface XslTaskshowMapper {
    List<XslTask> getXslTaskList(Map<String, Object> map);

    List<XslTaskPosh> getXslTaskListfirst(Integer rows);

    List<XslTaskPosh> getXslTaskOld(Map<String, Object> map);

    List<XslTaskPosh> getXslTaskNew(Map<String, Object> map);
}
