package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import pojo.XslScore;
import example.XslScoreExample;

public interface XslScoreMapper {
    int countByExample(XslScoreExample example);

    int deleteByExample(XslScoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslScore record);

    int insertSelective(XslScore record);

    List<XslScore> selectByExample(XslScoreExample example);

    XslScore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslScore record, @Param("example") XslScoreExample example);

    int updateByExample(@Param("record") XslScore record, @Param("example") XslScoreExample example);

    int updateByPrimaryKeySelective(XslScore record);

    int updateByPrimaryKey(XslScore record);
}