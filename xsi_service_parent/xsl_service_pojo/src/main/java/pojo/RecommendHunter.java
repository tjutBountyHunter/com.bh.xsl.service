package pojo;

import java.util.TreeSet;

public class RecommendHunter {
    private TreeSet<Integer> tagSet;//标签集
    private byte[] tagVector;//标签向量
    private double simDu;//相似度
    private int taskCount;//任务集中有关的任务数量
    private double eigenValue;//特征值
    private double recomValue;//推荐值

    public byte[] getTagVector() {
        return tagVector;
    }

    public double getSimDu() {
        return simDu;
    }


    public TreeSet<Integer> getTagSet() {
        return tagSet;
    }

    public void setSimDu(double simDu) {
        this.simDu = simDu;
    }


    public double getEigenValue() {
        return eigenValue;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTagSet(TreeSet<Integer> tagSet) {
        this.tagSet = tagSet;
    }

    public void setEigenValue(double eigenValue) {
        this.eigenValue = eigenValue;
    }

    public double getRecomValue() {
        return recomValue;
    }

    public void setTagVector(byte[] tagVector) {
        this.tagVector = tagVector;
    }

    public void setRecomValue(double recomValue) {
        this.recomValue = recomValue;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

}

