package service.impl;

import mapper.XslHunterLevelMapper;
import mapper.XslHunterMapper;
import mapper.XslMasterLevelMapper;
import mapper.XslMasterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.HunMaster;
import service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 猎人初始化
 */
@Service
public class HunMasterImpl implements HunMaster {
    @Autowired
    private XslHunterLevelMapper xslHunterLevelMapper;
    @Autowired
    private XslMasterLevelMapper xslMasterLevelMapper;
    @Autowired
    private XslHunterMapper xslHunterMapper;
    @Autowired
    private XslMasterMapper xslMasterMapper;
    @Autowired
    private UserService userService;
    @Override
    public Map<String, Integer> insertPeople(Integer userId) {
        XslHunter xslHunter = new XslHunter();
        XslMaster xslMaster = new XslMaster();
        xslHunter.setLevel((short) 1);
        xslHunter.setUserid(userId);
        xslHunter.setTaskaccnum(0);
        xslHunter.setTaskfailnum(0);
        xslHunter.setEmpirical(0);
        xslHunter.setCredit((short) 100);
        xslHunter.setLastaccdate(new Date());
        xslHunter.setDescr("");
        xslMaster.setLevel((short) 1);
        xslMaster.setUserid(userId);
        xslMaster.setTaskaccnum(0);
        xslMaster.setTaskrevokenum(0);
        xslMaster.setCredit((short) 100);
        xslMaster.setEmpirical(0);
        xslMaster.setLastaccdate(new Date());
        xslMaster.setDesc("");
        xslHunterMapper.insert(xslHunter);
        xslMasterMapper.insert(xslMaster);
        XslHunterExample xslHunterExample = new XslHunterExample();
        XslHunterExample.Criteria criteria = xslHunterExample.createCriteria();
        criteria.andUseridEqualTo(userId);
        List<XslHunter> list = xslHunterMapper.selectByExample(xslHunterExample);
        XslMasterExample xslMasterExample = new XslMasterExample();
        XslMasterExample.Criteria criteria1 = xslMasterExample.createCriteria();
        criteria1.andUseridEqualTo(userId);
        List<XslHunter> list1 = xslHunterMapper.selectByExample(xslHunterExample);
        Map<String, Integer> map = new HashMap<>();
        map.put("masterId", list1.get(0).getId());
        map.put("hunterId", list.get(0).getId());
        return map;
    }

    @Override
    public void insertLevel() {
        XslHunterLevel xslHunterLevel = new XslHunterLevel();
        XslMasterLevel xslMasterLevel = new XslMasterLevel();
        xslHunterLevel.setName("铜牌猎人");
        xslHunterLevel.setDesc("");
        xslHunterLevel.setCreatedate(new Date());
        xslHunterLevel.setUpdatedate(new Date());
        xslMasterLevel.setName("铜牌雇主");
        xslMasterLevel.setDesc("");
        xslMasterLevel.setCreatedate(new Date());
        xslMasterLevel.setUpdatedate(new Date());
        xslHunterLevelMapper.insert(xslHunterLevel);
        xslMasterLevelMapper.insert(xslMasterLevel);
    }
}
