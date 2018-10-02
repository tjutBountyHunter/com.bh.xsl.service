package mapper;

import pojo.XslWaitTask;

import java.util.List;

public interface XslWaitTaskMapper {
    List<XslWaitTask> selectByuserId(Integer userId);
}
