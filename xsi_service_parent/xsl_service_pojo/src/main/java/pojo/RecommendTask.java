package pojo;

import java.util.HashSet;

public class RecommendTask {
    private HashSet<Integer> taskTagSet;//标签集合

    public HashSet<Integer> getTaskTagSet() {
        return taskTagSet;
    }

    public void setTaskTagSet(HashSet<Integer> taskTagSet) {
        this.taskTagSet = taskTagSet;
    }
}
