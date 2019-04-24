package mapper;

import pojo.XslHistoryHunter;

import java.util.List;
import java.util.Map;

public interface XslHistoryHunterMap {
    List<XslHistoryHunter> selectByHunterid(Map<String, Object> map);
}
