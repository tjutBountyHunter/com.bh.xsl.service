package mapper;

import pojo.XslSchool;

import java.util.List;

public interface XslSchoolMessageMapper {
    List<String> selectByXslSchool();

    int selectBySchoolName(String schoolName);

    List<XslSchool> selectSchoolList();
}
