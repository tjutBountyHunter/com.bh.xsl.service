package mapper;

import example.XslFileExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslFile;

import java.util.List;

public interface XslFileMapper {
    int countByExample(XslFileExample example);

    int deleteByExample(XslFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslFile record);

    int insertSelective(XslFile record);

    List<XslFile> selectByExample(XslFileExample example);

    XslFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslFile record, @Param("example") XslFileExample example);

    int updateByExample(@Param("record") XslFile record, @Param("example") XslFileExample example);

    int updateByPrimaryKeySelective(XslFile record);

    int updateByPrimaryKey(XslFile record);
}