package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.XslNetwork;
import example.XslNetworkExample;

public interface XslNetworkMapper {
    int countByExample(XslNetworkExample example);

    int deleteByExample(XslNetworkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslNetwork record);

    int insertSelective(XslNetwork record);

    List<XslNetwork> selectByExample(XslNetworkExample example);

    XslNetwork selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslNetwork record, @Param("example") XslNetworkExample example);

    int updateByExample(@Param("record") XslNetwork record, @Param("example") XslNetworkExample example);

    int updateByPrimaryKeySelective(XslNetwork record);

    int updateByPrimaryKey(XslNetwork record);


    int updateNum(XslNetwork xslNetwork);

}