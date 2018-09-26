package service.impl;

import mapper.XslHunterLevelMapper;
import mapper.XslHunterMapper;
import mapper.XslMasterLevelMapper;
import mapper.XslMasterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslHunter;
import pojo.XslHunterLevel;
import pojo.XslMaster;
import pojo.XslMasterLevel;
import service.HunMaster;

import java.util.Date;

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

    @Override
    public void insertPeople() {
        XslHunter xslHunter = new XslHunter();
        XslMaster xslMaster = new XslMaster();
        xslHunter.setLevel((short) 0);
        xslHunter.setTaskaccnum(0);
        xslHunter.setTaskfailnum(0);
        xslHunter.setEmpirical(0);
        xslHunter.setCredit((short) 100);
        xslHunter.setLastaccdate(new Date());
        xslHunter.setDesc("");
        xslMaster.setLevel((short) 0);
        xslMaster.setTaskaccnum(0);
        xslMaster.setTaskrevokenum(0);
        xslMaster.setCredit((short) 100);
        xslMaster.setEmpirical(0);
        xslMaster.setLastaccdate(new Date());
        xslMaster.setDesc("");
        xslHunterMapper.insert(xslHunter);
        xslMasterMapper.insert(xslMaster);
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
