package mapper;

import java.util.List;

import example.XslCollecttExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslCollectt;

public interface XslCollecttMapper {
    int countByExample(XslCollecttExample example);

    int deleteByExample(XslCollecttExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslCollectt record);

    int insertSelective(XslCollectt record);

    List<XslCollectt> selectByExample(XslCollecttExample example);

    XslCollectt selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslCollectt record, @Param("example") XslCollecttExample example);

    int updateByExample(@Param("record") XslCollectt record, @Param("example") XslCollecttExample example);

    int updateByPrimaryKeySelective(XslCollectt record);

    int updateByPrimaryKey(XslCollectt record);
}