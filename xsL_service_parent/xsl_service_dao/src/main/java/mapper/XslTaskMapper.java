package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.XslTask;
import example.XslTaskExample;
import vo.SendAndRecTaskReqVo;

public interface XslTaskMapper {
    int countByExample(XslTaskExample example);

    int deleteByExample(XslTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslTask record);

    int insertSelective(XslTask record);

    List<XslTask> selectByExample(XslTaskExample example);

    XslTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslTask record, @Param("example") XslTaskExample example);

    int updateByExample(@Param("record") XslTask record, @Param("example") XslTaskExample example);

    int updateByPrimaryKeySelective(XslTask record);

    int updateByPrimaryKey(XslTask record);

    List<XslTask> selectBySendId(SendAndRecTaskReqVo sendAndRecTaskReqVo);
}