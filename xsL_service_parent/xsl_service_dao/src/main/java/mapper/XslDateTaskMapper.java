package mapper;

import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

public interface XslDateTaskMapper {

    @Select("SELECT id from xsl_task_category WHERE name = #{name}")
    Integer getCidByName(String name);

    @Select("SELECT  taskNum from xsl_task_category WHERE name = #{name}")
    Integer getTaskNumByName(String name);

    void UpdateTaskNumByName(HashMap Taskmap);

    @Select("SELECT useNum from xsl_tag where name = #{name};")
    Integer getUseNum(String name);

    void UpdateTagNumByName(HashMap map);

    @Select("SELECT id from xsl_task where taskId = #{staskId}")
    Integer getTaskIdByName(String taskId);

    Integer getTagIdByName(String name);

}
