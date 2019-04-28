package service.impl;

import mapper.XslHunterMapper;
import mapper.XslHunterTagMapper;
import mapper.XslTaskMapper;
import mapper.XslTaskTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.HunterRecommend;

import java.util.*;

@Service
public class HunterRecommendImpl implements HunterRecommend {

    @Autowired
    XslTaskTagMapper xslTaskTagMapper;

    @Autowired
    XslHunterTagMapper xslHunterTagMapper;

    @Autowired
    XslHunterMapper xslHunterMapper;

    //猎人集合相似度之和
    private double RecommendHunterSimSum;

    //任务集中有关任务总数
    private int RecommendHunterTaskSum;

    @Override
    public int[] recommend(Integer task_id) {
//        this.RecommendHunterSimSum = 0;
//        this.RecommendHunterTaskSum = 0;
//        Set<Integer> taskTagSet = new HashSet<Integer>();
//        TreeSet<Integer> tagSet = new TreeSet<Integer>();
//        List<Integer> tagList;
//        Map<Integer, RecommendHunter> hunterMap = new HashMap<Integer, RecommendHunter>(100);
//        Map<Integer, RecommendTask> taskMap = new HashMap<Integer, RecommendTask>(100);
//
//        List<XslTaskTag> task_tag_list = xslTaskTagMapper.getTagsByTaskId(task_id);
//        //任务标签list转set
//        for (XslTaskTag xslTaskTag : task_tag_list) {
//            taskTagSet.add(xslTaskTag.getTagid());
//        }
//        //通过set中的标签ID找对应的猎人Map
//        for (Integer task_tag : taskTagSet) {
//            List<XslHunterTag> hunterList = xslHunterTagMapper.getHuntersByTag(task_tag);
//            for (XslHunterTag hunters : hunterList) {
//                System.out.println("53 " + hunterList.size());
//                System.out.println("54 " + hunters.getHunterid());
//                System.out.println("55 " + hunterMap.containsKey(hunters.getHunterid()));
//                if (!hunterMap.containsKey(hunters.getHunterid())) {
//                    hunterMap.put(hunters.getHunterid(), new RecommendHunter());
//                }
//                //拿到所有对应猎人 所拥有的全部标签
//                List<XslHunterTag> tag_list = xslHunterTagMapper.getTagsByHunter(hunters.getHunterid());
//
//                //将猎人标签list转换为Set
//
//                for (XslHunterTag tag : tag_list) {
//                    tagSet.add(tag.getTagid());
//                    System.out.println("67 " + tag.getTagid());
//                }
//            }
//        }
//
//
//        //将tagSet中的标签导进tagList，并清空tag_sets
//        tagList = new ArrayList<Integer>(tagSet);
//        tagSet.clear();
//        //2.1.6对hunterMap进行遍历
//        for (Integer key : hunterMap.keySet()) {
//            //根据tagList生成成同等数量的标签向量(byte[] tagVector)
//            byte[] tagVector = new byte[tagList.size()];
//
//            //hunter实例的属性之一
//            TreeSet<Integer> hunterTagSet = new TreeSet<Integer>();
//
//            //根据key从数据库获取猎人标签
//            List<XslHunterTag> tag_list = xslHunterTagMapper.getTagsByHunter(key);
//            //对每一个标签根据tag_list获取标签对index,在tagVector中相应的位置置1，并将tag加入tagSet
//            for (XslHunterTag tag : tag_list) {
//                int index = tagList.indexOf(tag.getTagid());
//                tagVector[index] = 1;
//                hunterTagSet.add(tag.getTagid());
//            }
//            hunterMap.get(key).setTagSet(hunterTagSet);
//            hunterMap.get(key).setTagVector(tagVector);
//        }
//
//
//        //2.2遍历hunterMap来计算每个猎人的相似度
//        Integer[] key = new Integer[hunterMap.size()];
//        key = hunterMap.keySet().toArray(key);
//        for (Integer integer : key) {
//            computeAndSetSim(integer, hunterMap);
//        }
//
//        //3.1 从数据库中找出和这些标签taskTagSet有联系的任务，用taskMap中的key记录任务id
//        for (Integer task_tag : taskTagSet) {
//            List<XslTaskTag> xsl_tasks = xslTaskTagMapper.getTasksByTag(task_tag);
//            for (XslTaskTag xsl_task : xsl_tasks) {
//                taskMap.put(xsl_task.getTaskid(), new RecommendTask());
//            }
//        }
//
//        //4.1找出任务集中每个任务所拥有的标签
//        //对taskMap进行遍历
//        for (Integer task_key : taskMap.keySet()) {
//
//            //task实例的属性之一
//            HashSet<Integer> task_Tag_Set = new HashSet<Integer>();
//
//            List<XslTaskTag> task_tags = xslTaskTagMapper.getTagsByTaskId(task_key);
//            for (XslTaskTag task_tag : task_tags) {
//                task_Tag_Set.add(task_tag.getTagid());
//            }
//
//            taskMap.get(task_key).setTaskTagSet(task_Tag_Set);
//
//
//        }
//
//        //4.2根据任务集，计算出与猎人集中每个猎人有关系对任务数和任务总数
//        for (Integer integer : taskMap.keySet()) {
//            getTaskCount(integer, taskMap, hunterMap);
//        }
//        //5.2计算猎人集的特征集
////        //对猎人的特征属性进行遍历
//
//        List<XslHunter> hunter_list = new ArrayList<XslHunter>(100);
//
//
//        //利用特征值公式计算hunterMap中猎人的特征值
//        short max_level = 0;
//        short max_credit = 0;
//        int max_empirical = 0;
//        int max_taskAccNum = 0;
//
//
//        for (Integer hunter_id : hunterMap.keySet()) {
//            XslHunter xslHunter = xslHunterMapper.selectByPrimaryKey(hunter_id);
//            if (xslHunter.getCredit() > max_credit) {
//                max_credit = xslHunter.getCredit();
//            }
//            if (xslHunter.getLevel() > max_level) {
//                max_level = xslHunter.getLevel();
//            }
//            if (xslHunter.getEmpirical() > max_empirical) {
//                max_empirical = xslHunter.getEmpirical();
//            }
//            if (xslHunter.getTaskaccnum() > max_taskAccNum) {
//                max_taskAccNum = xslHunter.getTaskaccnum();
//            }
//            hunter_list.add(xslHunter);
//        }
//
//        for (XslHunter xslHunter : hunter_list) {
//            double td = ((((2.0 / Math.PI) * (double) Math.atan((double) xslHunter.getLevel() / max_level)) +
//                    ((2.0 / Math.PI) * (double) Math.atan((double) xslHunter.getCredit() / max_credit)) +
//                    ((2.0 / Math.PI) * (double) Math.atan((double) (xslHunter.getEmpirical() + 1) / (max_empirical + 1))) +
//                    ((2.0 / Math.PI) * (double) Math.atan((double) (xslHunter.getTaskaccnum() + 1) / (max_taskAccNum + 1))))) / 4;
//            hunterMap.get(xslHunter.getId()).setEigenValue(td);
//        }
//
//        //6计算推荐值获取TopN列表
//        //对推荐值进行排序
//        TreeMap<Double, Integer> topHunter = new TreeMap<>();
//        //计算推荐值
//        for (Map.Entry<Integer, RecommendHunter> entry : hunterMap.entrySet()) {
//            RecommendHunter hunter = entry.getValue();
//            double recomLeftValue = 0.4 * ((double) hunter.getTaskCount() / this.RecommendHunterTaskSum);
//            double recomRightValue = (1 - 0.4) * hunter.getEigenValue() * ((double) hunter.getSimDu() / this.RecommendHunterSimSum);
//            double recomValue = recomLeftValue + recomRightValue;
//            hunter.setRecomValue(recomValue);
//            topHunter.put(recomValue, entry.getKey());
//
//        }
//        int[] topN = new int[5];
//        for (int i = 0; i < topHunter.size(); i++) {
//            topN[0] = topHunter.pollLastEntry().getValue();
//        }
//        return topN;
//    }
//
//    private void computeAndSetSim(Integer integer, Map<Integer, RecommendHunter> hunterMap) {
//        byte[] vector1 = hunterMap.get(integer).getTagVector();
//        double sum = 0.0;
//        for (Map.Entry<Integer, RecommendHunter> entry : hunterMap.entrySet()) {
//            if (entry.getKey().equals(integer)) {
//                continue;
//            }
//            byte[] vector2 = entry.getValue().getTagVector();
//
//            //余弦相似度计算
//            int onSum = 0;
//            int underRightSum = 0;
//            int underLeftSum = 0;
//            for (int i = 0; i < vector1.length; i++) {
//                onSum += (vector1[i] * vector2[i]);
//                underLeftSum += vector1[i];
//                underRightSum += vector2[i];
//            }
//            //猎人i与当前猎人的相似度
//            double result = onSum / (Math.sqrt(underLeftSum) * Math.sqrt(underRightSum));
//
//            //猎人i与猎人集的相似度
//            sum += result;
//            //所有猎人的相似度
//            this.RecommendHunterSimSum += result;
//        }
//        RecommendHunter hunter = hunterMap.get(integer);
//        hunter.setSimDu(sum);
//        System.out.println("231 " + sum);
//    }
//
//    private void getTaskCount(Integer integer, Map<Integer, RecommendTask> taskMap, Map<Integer, RecommendHunter> hunterMap) {
//        //获取当前任务的所有标签
//        HashSet<Integer> taskTagSet = taskMap.get(integer).getTaskTagSet();
//        //遍历，计算与当前任务是否有联系，有联系则把猎人中的任务数量和所有猎人的任务数量和+1
//        for (Map.Entry<Integer, RecommendHunter> entry : hunterMap.entrySet()) {
//            RecommendHunter recommendHunter = entry.getValue();
//            for (Integer integer1 : taskTagSet) {
//                if (recommendHunter.getTagSet().contains(integer1)) {
//                    recommendHunter.setTaskCount(recommendHunter.getTaskCount() + 1);
//                    this.RecommendHunterTaskSum++;
//                    break;
//                }
//            }
//        }
		return null;
    }
}
