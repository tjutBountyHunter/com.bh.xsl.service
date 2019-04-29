package mapper;

import java.util.List;

import example.XslTaskTagExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslTaskTag;

public interface XslTaskTagMapper {
    int countByExample(XslTaskTagExample example);

    int deleteByExample(XslTaskTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslTaskTag record);

    int insertSelective(XslTaskTag record);

    List<XslTaskTag> selectByExample(XslTaskTagExample example);

    XslTaskTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslTaskTag record, @Param("example") XslTaskTagExample example);

    int updateByExample(@Param("record") XslTaskTag record, @Param("example") XslTaskTagExample example);

    int updateByPrimaryKeySelective(XslTaskTag record);

    int updateByPrimaryKey(XslTaskTag record);

    int insertSelectiveBatch(List<XslTaskTag> xslTaskFiles);

//    重写
    List<XslTaskTag> getTagsByTaskId(String taskId);

    List<XslTaskTag> getTasksByTagId(String taskTag);
}