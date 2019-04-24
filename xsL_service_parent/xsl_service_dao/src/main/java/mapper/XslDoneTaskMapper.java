package mapper;

import pojo.XslWaitTask;

import java.util.List;

public interface XslDoneTaskMapper {
    List<XslWaitTask> selectByuseId(Integer userId);
}
