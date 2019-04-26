package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.XslTaskCategory;
import pojo.XslTaskCategoryExample;

public interface XslTaskCategoryMapper {
    int countByExample(XslTaskCategoryExample example);

    int deleteByExample(XslTaskCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslTaskCategory record);

    int insertSelective(XslTaskCategory record);

    List<XslTaskCategory> selectByExample(XslTaskCategoryExample example);

    XslTaskCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslTaskCategory record, @Param("example") XslTaskCategoryExample example);

    int updateByExample(@Param("record") XslTaskCategory record, @Param("example") XslTaskCategoryExample example);

    int updateByPrimaryKeySelective(XslTaskCategory record);

    int updateByPrimaryKey(XslTaskCategory record);
}