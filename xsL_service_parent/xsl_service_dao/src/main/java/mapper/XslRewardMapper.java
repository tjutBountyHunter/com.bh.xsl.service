package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.XslReward;
import example.XslRewardExample;

public interface XslRewardMapper {
    int countByExample(XslRewardExample example);

    int deleteByExample(XslRewardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslReward record);

    int insertSelective(XslReward record);

    List<XslReward> selectByExample(XslRewardExample example);

    XslReward selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslReward record, @Param("example") XslRewardExample example);

    int updateByExample(@Param("record") XslReward record, @Param("example") XslRewardExample example);

    int updateByPrimaryKeySelective(XslReward record);

    int updateByPrimaryKey(XslReward record);
}