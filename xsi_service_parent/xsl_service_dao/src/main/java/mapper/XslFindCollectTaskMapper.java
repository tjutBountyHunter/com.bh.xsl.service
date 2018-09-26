package mapper;

import pojo.XslFindCollectTask;

import java.util.List;

public interface XslFindCollectTaskMapper {
    List<XslFindCollectTask> selectByuserId(Integer userId);
}
