package mapper;

import pojo.XslTaskCategory;

import java.util.List;

public interface XslTaskClassMapper {
    List<String> selectByXslTaskCategory(XslTaskCategory xslTaskCategory);

    int selectByTaskName(String name);
}
