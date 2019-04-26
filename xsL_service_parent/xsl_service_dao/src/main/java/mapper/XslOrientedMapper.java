package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.XslOriented;
import example.XslOrientedExample;

public interface XslOrientedMapper {
    int countByExample(XslOrientedExample example);

    int deleteByExample(XslOrientedExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslOriented record);

    int insertSelective(XslOriented record);

    List<XslOriented> selectByExample(XslOrientedExample example);

    XslOriented selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslOriented record, @Param("example") XslOrientedExample example);

    int updateByExample(@Param("record") XslOriented record, @Param("example") XslOrientedExample example);

    int updateByPrimaryKeySelective(XslOriented record);

    int updateByPrimaryKey(XslOriented record);
}