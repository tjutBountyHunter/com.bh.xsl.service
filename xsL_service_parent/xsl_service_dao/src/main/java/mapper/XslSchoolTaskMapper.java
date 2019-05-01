package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.XslSchoolTask;
import example.XslSchoolTaskExample;

public interface XslSchoolTaskMapper {
    int countByExample(XslSchoolTaskExample example);

    int deleteByExample(XslSchoolTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslSchoolTask record);

    int insertSelective(XslSchoolTask record);

    List<XslSchoolTask> selectByExample(XslSchoolTaskExample example);

    XslSchoolTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslSchoolTask record, @Param("example") XslSchoolTaskExample example);

    int updateByExample(@Param("record") XslSchoolTask record, @Param("example") XslSchoolTaskExample example);

    int updateByPrimaryKeySelective(XslSchoolTask record);

    int updateByPrimaryKey(XslSchoolTask record);


}