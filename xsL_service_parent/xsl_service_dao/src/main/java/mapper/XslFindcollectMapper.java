package mapper;

import pojo.XslHistoryHunter;
import pojo.XslTaskPosh;
import pojo.XslcollectTask;

import java.util.List;

public interface XslFindcollectMapper {
    List<XslHistoryHunter> selectByuserId(int userId);

    List<XslcollectTask> selectByUserIdTask(int userId);
    int selectCountByuserId(int userId);
}
