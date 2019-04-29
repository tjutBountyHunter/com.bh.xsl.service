package mapper;

import java.util.List;

import example.XslHunterExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslHunter;

public interface XslHunterMapper {
    int countByExample(XslHunterExample example);

    int deleteByExample(XslHunterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslHunter record);

    int insertSelective(XslHunter record);

    List<XslHunter> selectByExample(XslHunterExample example);

    XslHunter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslHunter record, @Param("example") XslHunterExample example);

    int updateByExample(@Param("record") XslHunter record, @Param("example") XslHunterExample example);

    int updateByPrimaryKeySelective(XslHunter record);

    int updateByPrimaryKey(XslHunter record);

//    重写
    int selectMaxLevel();

    int selectMaxTaskAccNum();

    XslHunter selectHunterByHunterId(String hunterId);
}