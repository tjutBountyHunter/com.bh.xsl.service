package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.XslRole;
import example.XslRoleExample;

public interface XslRoleMapper {
    int countByExample(XslRoleExample example);

    int deleteByExample(XslRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslRole record);

    int insertSelective(XslRole record);

    List<XslRole> selectByExample(XslRoleExample example);

    XslRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslRole record, @Param("example") XslRoleExample example);

    int updateByExample(@Param("record") XslRole record, @Param("example") XslRoleExample example);

    int updateByPrimaryKeySelective(XslRole record);

    int updateByPrimaryKey(XslRole record);
}