package mapper;

import pojo.XslHistoryHunter;

import java.util.List;
import java.util.Map;

public interface XslHunterShopMapper {
    List<XslHistoryHunter> selectByThree(Map<String, Object> map);

    Integer getXslHunterCount(Map<String, Object> map);
}
