package mapper;

import pojo.XslMainPageMessage;

import java.util.List;

public interface XslMainPageFindMapper {
    List<XslMainPageMessage> selectByuserId(Integer userId);
}
