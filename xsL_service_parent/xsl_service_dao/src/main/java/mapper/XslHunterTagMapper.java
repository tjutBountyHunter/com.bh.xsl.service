package mapper;

import java.util.List;

import example.XslHunterTagExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslHunterTag;
import pojo.XslTaskTag;

public interface XslHunterTagMapper {
    int countByExample(XslHunterTagExample example);

    int deleteByExample(XslHunterTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslHunterTag record);

    int insertSelective(XslHunterTag record);

    List<XslHunterTag> selectByExample(XslHunterTagExample example);

    XslHunterTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslHunterTag record, @Param("example") XslHunterTagExample example);

    int updateByExample(@Param("record") XslHunterTag record, @Param("example") XslHunterTagExample example);

    int updateByPrimaryKeySelective(XslHunterTag record);

    int updateByPrimaryKey(XslHunterTag record);

//    重写
    List<XslHunterTag> getHuntersByTagId(String taskTag);

    List<XslHunterTag> getTagsByHunterId(String hunterId);

    int insertSelectiveBatch(List<XslHunterTag> list);
}