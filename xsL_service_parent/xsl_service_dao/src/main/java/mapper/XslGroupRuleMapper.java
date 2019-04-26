package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.XslGroupRule;
import example.XslGroupRuleExample;

public interface XslGroupRuleMapper {
    int countByExample(XslGroupRuleExample example);

    int deleteByExample(XslGroupRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslGroupRule record);

    int insertSelective(XslGroupRule record);

    List<XslGroupRule> selectByExample(XslGroupRuleExample example);

    XslGroupRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslGroupRule record, @Param("example") XslGroupRuleExample example);

    int updateByExample(@Param("record") XslGroupRule record, @Param("example") XslGroupRuleExample example);

    int updateByPrimaryKeySelective(XslGroupRule record);

    int updateByPrimaryKey(XslGroupRule record);
}