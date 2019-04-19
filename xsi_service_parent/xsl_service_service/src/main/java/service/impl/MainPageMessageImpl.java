package service.impl;

import mapper.XslMainPageFindMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslMainPageMessage;
import util.XslResult;
import service.MainPageMessage;

import java.util.List;
import java.util.Random;

@Service
public class MainPageMessageImpl implements MainPageMessage {
    @Autowired
    private XslMainPageFindMapper xslMainPageFindMapper;

    @Override
    public XslResult mainpageMessage(Integer userId) {
        try {
            List<XslMainPageMessage> list = xslMainPageFindMapper.selectByuserId(userId);
            if (list.size() == 0 && list.isEmpty()) {
                return XslResult.ok("无法显示该用户");
            } else {
                Random rd = new Random();
                int random = rd.nextInt(50) + 50;
                list.get(0).setCredit(random);
            }
            return XslResult.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
}
