package mapper;

import java.util.List;

public interface XslCollegeMessageMapper {
    List<String> selectBySchoolId(int id);

    int selectBycollegeName(String collegeName);
}
