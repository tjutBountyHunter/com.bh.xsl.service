package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import pojo.XslDatetime;
import pojo.XslDatetimeExample;

public interface XslDatetimeMapper {
    int countByExample(XslDatetimeExample example);

    int deleteByExample(XslDatetimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslDatetime record);

    int insertSelective(XslDatetime record);

    List<XslDatetime> selectByExample(XslDatetimeExample example);

    XslDatetime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslDatetime record, @Param("example") XslDatetimeExample example);

    int updateByExample(@Param("record") XslDatetime record, @Param("example") XslDatetimeExample example);

    int updateByPrimaryKeySelective(XslDatetime record);

    int updateByPrimaryKey(XslDatetime record);
}