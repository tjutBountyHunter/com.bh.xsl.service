package mapper;

import pojo.XslOneHunter;

import java.util.List;
import java.util.Map;

public interface XslHuntershowMapper {

    /**
     * 分页查询猎人信息
     *
     * @param map
     * @return
     */
    List<XslOneHunter> getXslHunterListfirst(Map<String, Object> map);

    /**
     * 分页查询旧的分页信息
     *
     * @param map
     * @return
     */
    List<XslOneHunter> getXslHunterOld(Map<String, Object> map);

    /**
     * 分页查新的分页信息
     *
     * @param map
     * @return
     */
    List<XslOneHunter> getXslHunterNew(Map<String, Object> map);

}
