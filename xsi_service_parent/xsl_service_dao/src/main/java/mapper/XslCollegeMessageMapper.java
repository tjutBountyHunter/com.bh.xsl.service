package mapper;

import pojo.XslCollege;

import java.util.List;
import java.util.Map;

public interface XslCollegeMessageMapper {
    List<String> selectBySchoolId(int id);

    int selectBycollegeName(Map<String, Object> map);

    List<XslCollege> selectCollegeList(int id);
}
