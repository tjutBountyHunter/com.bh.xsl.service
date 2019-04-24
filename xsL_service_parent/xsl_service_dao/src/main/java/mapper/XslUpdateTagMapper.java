package mapper;

import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface XslUpdateTagMapper {
    @Update("update xsl_tag set useNum=#{useNum} where name=#{name}")
    int updateTag(Map<String, Object> map);
}
