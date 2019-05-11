package service.impl;

import mapper.XslNetworkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslNetwork;
import service.NetworkService;
import util.XslResult;
@Service
public class NetworkServiceImpl implements NetworkService {

    @Autowired
    private XslNetworkMapper xslNetworkMapper;


    @Override
    public XslResult updateNetwork(XslNetwork xslNetwork) {
        try {
            //

            XslNetwork xslNetwork1=new XslNetwork();
            xslNetwork1.setAid(xslNetwork.getBid());
            xslNetwork1.setBid(xslNetwork.getAid());
            if(xslNetworkMapper.updateNum(xslNetwork)!=1&&xslNetworkMapper.updateNum(xslNetwork1)!=1){
                //更新两种顺序都失败  插入新的关系记录；
                xslNetworkMapper.insert(xslNetwork);
            }
            return XslResult.build(200,"成功");



        }
       catch (Exception e){
           throw new RuntimeException(e);
       }
    }


}
