package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.XslMatchReward;
import example.XslMatchRewardExample;

public interface XslMatchRewardMapper {
    int countByExample(XslMatchRewardExample example);

    int deleteByExample(XslMatchRewardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslMatchReward record);

    int insertSelective(XslMatchReward record);

    List<XslMatchReward> selectByExample(XslMatchRewardExample example);

    XslMatchReward selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslMatchReward record, @Param("example") XslMatchRewardExample example);

    int updateByExample(@Param("record") XslMatchReward record, @Param("example") XslMatchRewardExample example);

    int updateByPrimaryKeySelective(XslMatchReward record);

    int updateByPrimaryKey(XslMatchReward record);
}