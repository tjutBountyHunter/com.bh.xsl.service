package mapper;

import pojo.*;

import java.util.List;
import java.util.Map;

public interface XslHunterShopMapper {
    List<XslAllHistoryHunter> selectByThree(Map<String, Object> map);

    List<XslHunterhistoryDefault> selectBydefault(Integer userId);
    Integer getXslHunterCount(Map<String, Object> map);

    List<XslHotHisory> selectByHot(Integer rows);

    List<String> selectHotTag(Integer hunterId);

    XslOneHunter selectByhunterId(Integer hunterId);
}
