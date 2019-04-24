package mapper;

import pojo.XslMajor;

import java.util.List;

public interface XslMajorMessageMapper {
    List<String> selectByCollegeId(int collegeId);

    List<XslMajor> selectMajorList(int collegeId);
}
