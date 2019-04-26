package mapper;

import java.util.List;

import example.XslMasterTagExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslMasterTag;

public interface XslMasterTagMapper {
    int countByExample(XslMasterTagExample example);

    int deleteByExample(XslMasterTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslMasterTag record);

    int insertSelective(XslMasterTag record);

    List<XslMasterTag> selectByExample(XslMasterTagExample example);

    XslMasterTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslMasterTag record, @Param("example") XslMasterTagExample example);

    int updateByExample(@Param("record") XslMasterTag record, @Param("example") XslMasterTagExample example);

    int updateByPrimaryKeySelective(XslMasterTag record);

    int updateByPrimaryKey(XslMasterTag record);
}