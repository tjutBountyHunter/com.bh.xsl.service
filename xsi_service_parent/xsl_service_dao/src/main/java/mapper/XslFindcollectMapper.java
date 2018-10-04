package mapper;

import pojo.XslHistoryHunter;

import java.util.List;

public interface XslFindcollectMapper {
    List<XslHistoryHunter> selectByuserId(int userId);

    int selectCountByuserId(int userId);
}
