package service.impl;

import example.XslTagExample;
import example.XslTaskExample;
import mapper.*;
import org.python.core.Py;
import org.python.core.PyByteArray;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;
import service.*;
import util.*;

import java.io.*;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SupplementDataServiceImpl implements SupplementDataService {

    @Autowired
    private XslTaskMapper xslTaskMapper;

    @Autowired
    private XslDateTaskMapper xslDateTaskMapperr;

    @Autowired
    private XslTaskTagMapper xslTaskTagMapper;
    @Autowired
    private XslTagMapper xslTagMapper;
    @Autowired
    private XslUpdateTagMapper xslUpdateTagMapper;
    @Autowired
    private XslTaskCategoryMapper xslTaskCategoryMapper;

    /**
     * 添加任务信息
     *
     * @param json
     */
    @Override
    public XslResult SupplementTaskData(String json) {
        return null;
    }

    public XslResult wordFind(String descr) {
        try {
            PythonInterpreter interpreter = new PythonInterpreter();
            PySystemState sys = Py.getSystemState();
//            E:\ID-workspeace\xsl_service_parent\xsl_service_common\src\main\
            sys.path.add("/home/ftp/www/images/jieba-master-1/build/lib");
            sys.path.add("/home/ftp/www/images/jieba-master-1/build/lib/jieba");
            FileInputStream filepy = new FileInputStream("/home/ftp/www/images/jieba-master-1/demo/demo2.py");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            interpreter.setOut(baos);
            String str = descr;
            str = str.replace("&", "");
            str = str.replace("*", "");
            str = str.replace("$", "");
            str = str.replace("%", "");
            byte[] bytes = str.getBytes();
            interpreter.exec("text=" + new PyByteArray(bytes));
            interpreter.execfile(filepy);
            ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
            BufferedReader in = new BufferedReader(new InputStreamReader(swapStream));
            String line;
            List<String> list = new ArrayList<String>(10);
            while ((line = in.readLine()) != null) {
                String[] strArray = line.split(" ");
                list.add(strArray[0]);
                if (strArray[1].equals("nz")) {
                    return XslResult.build(400, "有违规词组出现");
                }
            }
            return XslResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }

    /**
     * 更新任务种类使用次数
     * @param json
     */
    @Override
    public XslResult SupplementCategoryData(String json) {
        try {
            XslTaskCategoryExample xslTaskCategoryExample = new XslTaskCategoryExample();
            XslTaskCategoryExample.Criteria criteria = xslTaskCategoryExample.createCriteria();
            criteria.andNameEqualTo(json);
            List<XslTaskCategory> list = xslTaskCategoryMapper.selectByExample(xslTaskCategoryExample);
            XslTaskCategory xslTaskCategory = new XslTaskCategory();
            xslTaskCategory.setCreatedate(new Date());
            xslTaskCategory.setName(json);
            int num = xslTaskCategory.getTasknum();
            num++;
            xslTaskCategory.setTasknum(num);
            xslTaskCategoryMapper.updateByPrimaryKeySelective(xslTaskCategory);
            return XslResult.ok(xslTaskCategory.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }



//    /**
//     * 自定义标签
//     *
//     * @param json
//     * @return
//     */
//    @Override
//    public XslResult SupplementTagWrite(String json) {
//        try {
//            XslTag xslTag = new XslTag();
//            XslTagExample xslTagExample = new XslTagExample();
//            XslTagExample.Criteria criteria = xslTagExample.createCriteria();
//            criteria.andNameEqualTo(json);
//            List<XslTag> list = xslTagMapper.selectByExample(xslTagExample);
//            if (list.size() == 0 & list.isEmpty()) {
//                xslTag.setName(json);
//                xslTag.setCreatedate(new Date());
//                xslTag.setUsenum((short) 1);
////                xslTag.setState((Byte)1);
//                xslTagMapper.insertSelective(xslTag);
//            } else {
//                int num = list.get(0).getUsenum();
//                num++;
//                xslTag.setUsenum((short) num);
//                Map<String, Object> map = new HashMap<>();
//                map.put("name", json);
//                map.put("useNum", num);
//                xslUpdateTagMapper.updateTag(map);
//            }
//            return XslResult.ok("插入成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return XslResult.build(500, "服务器异常");
//        }
//    }
}
