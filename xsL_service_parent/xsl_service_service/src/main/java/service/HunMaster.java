package service;

import pojo.XslHunter;
import pojo.XslMaster;

import java.util.Map;

public interface HunMaster {
    Map<String, Integer> insertPeople(Integer userId);

    void insertLevel();
}
