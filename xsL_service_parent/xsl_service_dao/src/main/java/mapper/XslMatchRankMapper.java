package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.XslMatchRank;
import example.XslMatchRankExample;

public interface XslMatchRankMapper {
    int countByExample(XslMatchRankExample example);

    int deleteByExample(XslMatchRankExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslMatchRank record);

    int insertSelective(XslMatchRank record);

    List<XslMatchRank> selectByExample(XslMatchRankExample example);

    XslMatchRank selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslMatchRank record, @Param("example") XslMatchRankExample example);

    int updateByExample(@Param("record") XslMatchRank record, @Param("example") XslMatchRankExample example);

    int updateByPrimaryKeySelective(XslMatchRank record);

    int updateByPrimaryKey(XslMatchRank record);
}