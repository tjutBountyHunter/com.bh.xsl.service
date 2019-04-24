package mapper;

import example.XslUserFileExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslUserFile;

import java.util.List;

public interface XslUserFileMapper {
    int countByExample(XslUserFileExample example);

    int deleteByExample(XslUserFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslUserFile record);

    int insertSelective(XslUserFile record);

    List<XslUserFile> selectByExample(XslUserFileExample example);

    XslUserFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslUserFile record, @Param("example") XslUserFileExample example);

    int updateByExample(@Param("record") XslUserFile record, @Param("example") XslUserFileExample example);

    int updateByPrimaryKeySelective(XslUserFile record);

    int updateByPrimaryKey(XslUserFile record);
}