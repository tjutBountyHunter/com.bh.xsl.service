package mapper;

import java.util.List;

import example.XslMasterLevelExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslMasterLevel;

public interface XslMasterLevelMapper {
    int countByExample(XslMasterLevelExample example);

    int deleteByExample(XslMasterLevelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslMasterLevel record);

    int insertSelective(XslMasterLevel record);

    List<XslMasterLevel> selectByExample(XslMasterLevelExample example);

    XslMasterLevel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslMasterLevel record, @Param("example") XslMasterLevelExample example);

    int updateByExample(@Param("record") XslMasterLevel record, @Param("example") XslMasterLevelExample example);

    int updateByPrimaryKeySelective(XslMasterLevel record);

    int updateByPrimaryKey(XslMasterLevel record);
}