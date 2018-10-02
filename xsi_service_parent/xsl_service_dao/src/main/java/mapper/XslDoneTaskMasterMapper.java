package mapper;

import pojo.XslWaitTask;

import java.util.List;

public interface XslDoneTaskMasterMapper {
    List<XslWaitTask> selectByuserId(Integer useId);
}
