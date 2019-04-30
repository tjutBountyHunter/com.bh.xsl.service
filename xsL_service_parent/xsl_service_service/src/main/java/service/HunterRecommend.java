package service;

import java.util.List;

public interface HunterRecommend {

    /**
     * 猎人推荐
     *
     * @param taskId
     * @return 猎人id数组
     */
    List<String> recommend(String taskId, Integer recommendNum);

}
