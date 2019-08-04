package mapper;

import example.XslHunterTaskExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslHunterTask;
import vo.XslSendAndRecTaskReqVo;

import java.util.List;

public interface XslHunterTaskMapper {
    int countByExample(XslHunterTaskExample example);

    int deleteByExample(XslHunterTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslHunterTask record);

    int insertSelective(XslHunterTask record);

    List<XslHunterTask> selectByExample(XslHunterTaskExample example);

    XslHunterTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslHunterTask record, @Param("example") XslHunterTaskExample example);

    int updateByExample(@Param("record") XslHunterTask record, @Param("example") XslHunterTaskExample example);

    int updateByPrimaryKeySelective(XslHunterTask record);

    int updateByPrimaryKey(XslHunterTask record);

    List<String> selectByRecId(XslSendAndRecTaskReqVo xslSendAndRecTaskReqVo);
}