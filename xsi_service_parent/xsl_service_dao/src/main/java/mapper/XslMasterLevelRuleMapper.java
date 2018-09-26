package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.XslMasterLevelRule;
import pojo.XslMasterLevelRuleExample;

import java.util.List;

public interface XslMasterLevelRuleMapper {
    int countByExample(XslMasterLevelRuleExample example);

    int deleteByExample(XslMasterLevelRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslMasterLevelRule record);

    int insertSelective(XslMasterLevelRule record);

    List<XslMasterLevelRule> selectByExample(XslMasterLevelRuleExample example);

    XslMasterLevelRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslMasterLevelRule record, @Param("example") XslMasterLevelRuleExample example);

    int updateByExample(@Param("record") XslMasterLevelRule record, @Param("example") XslMasterLevelRuleExample example);

    int updateByPrimaryKeySelective(XslMasterLevelRule record);

    int updateByPrimaryKey(XslMasterLevelRule record);
}