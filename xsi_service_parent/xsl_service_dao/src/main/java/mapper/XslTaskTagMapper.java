package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.XslTaskTag;
import example.XslTaskTagExample;

import java.util.List;

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

    List<XslTaskTag> getTagsByTaskId(Integer task_id);

    List<XslTaskTag> getTasksByTag(Integer tag_id);
}