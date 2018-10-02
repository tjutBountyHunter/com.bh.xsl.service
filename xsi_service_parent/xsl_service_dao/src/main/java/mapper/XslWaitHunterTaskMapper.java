package mapper;

import pojo.XslWaitTask;

import java.util.List;

public interface XslWaitHunterTaskMapper {
    List<XslWaitTask> selectByHunterId(Integer hunterId);
}
