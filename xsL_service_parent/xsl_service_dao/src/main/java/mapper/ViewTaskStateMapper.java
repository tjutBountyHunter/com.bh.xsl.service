package mapper;

import org.apache.ibatis.annotations.Select;
import pojo.XslTask;

import java.util.List;

public interface ViewTaskStateMapper {

    /**
     * 通过发送者ID和状态信息来查询已发任务
     *
     * @param sendId
     * @return
     */
    @Select("SELECT id,taskId,cid,descr,sendId money,state,number,createDate,updateDate,deadline from xsl_task where sendId = #{sendId} and state=0")
    List<XslTask> getIssuedTaskBySendId(Integer sendId);

    /**
     * 通过发送者ID和状态信息来查询已接及待完成任务
     *
     * @param sendId
     * @return
     */
    @Select("SELECT id,taskId,cid,descr,sendId money,state,number,createDate,updateDate,deadline from xsl_task where sendId = #{sendId} and state=2")
    List<XslTask> getAccessTaskBySendId(Integer sendId);

    /**
     * 通过发送者ID和状态信息来查询已完成任务
     *
     * @param sendId
     * @return
     */
    @Select("SELECT id,taskId,cid,descr,sendId money,state,number,createDate,updateDate,deadline from xsl_task where sendId = #{sendId} and state=3")
    List<XslTask> getcompleteTaskBySendId(Integer sendId);
}
