package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.XslTaskFile;
import example.XslTaskFileExample;

public interface XslTaskFileMapper {
    int countByExample(XslTaskFileExample example);

    int deleteByExample(XslTaskFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslTaskFile record);

    int insertSelective(XslTaskFile record);

    List<XslTaskFile> selectByExample(XslTaskFileExample example);

    XslTaskFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslTaskFile record, @Param("example") XslTaskFileExample example);

    int updateByExample(@Param("record") XslTaskFile record, @Param("example") XslTaskFileExample example);

    int updateByPrimaryKeySelective(XslTaskFile record);

    int updateByPrimaryKey(XslTaskFile record);

    int insertSelectiveBatch(List<XslTaskFile> xslTaskFiles);
}