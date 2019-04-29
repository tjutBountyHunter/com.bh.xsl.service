package service;

public interface HunterRecommend {

    /**
     * 猎人推荐
     *
     * @param taskId
     * @return 猎人id数组
     */
    String[] recommend(String taskId);

}
