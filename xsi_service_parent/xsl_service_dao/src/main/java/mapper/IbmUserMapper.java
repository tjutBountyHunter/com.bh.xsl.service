package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.IbmUser;
import pojo.IbmUserExample;

import java.util.List;

public interface IbmUserMapper {
    int countByExample(IbmUserExample example);

    int deleteByExample(IbmUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbmUser record);

    int insertSelective(IbmUser record);

    List<IbmUser> selectByExample(IbmUserExample example);

    IbmUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbmUser record, @Param("example") IbmUserExample example);

    int updateByExample(@Param("record") IbmUser record, @Param("example") IbmUserExample example);

    int updateByPrimaryKeySelective(IbmUser record);

    int updateByPrimaryKey(IbmUser record);
}