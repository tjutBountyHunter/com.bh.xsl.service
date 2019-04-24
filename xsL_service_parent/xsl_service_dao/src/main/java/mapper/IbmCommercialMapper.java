package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.IbmCommercial;
import example.IbmCommercialExample;

import java.util.List;

public interface IbmCommercialMapper {
    int countByExample(IbmCommercialExample example);

    int deleteByExample(IbmCommercialExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IbmCommercial record);

    int insertSelective(IbmCommercial record);

    List<IbmCommercial> selectByExample(IbmCommercialExample example);

    IbmCommercial selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IbmCommercial record, @Param("example") IbmCommercialExample example);

    int updateByExample(@Param("record") IbmCommercial record, @Param("example") IbmCommercialExample example);

    int updateByPrimaryKeySelective(IbmCommercial record);

    int updateByPrimaryKey(IbmCommercial record);
}