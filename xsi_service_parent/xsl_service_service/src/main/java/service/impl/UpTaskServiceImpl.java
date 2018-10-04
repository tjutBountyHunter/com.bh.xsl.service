package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.TaskTopush;
import service.UpTaskService;
import service.SupplementDataService;
import service.XslResult;

@Service
public class UpTaskServiceImpl implements UpTaskService {

    @Autowired
    private SupplementDataService supplementDataService;
    @Autowired
    private TaskTopush taskTopush;

    @Override
    public XslResult UpuseTask(String json) {
        try {
            json = new String(json.getBytes("iso-8859-1"), "utf-8");
            XslResult xslResult = null;
            xslResult = supplementDataService.SupplementTaskTagData(json);
            return xslResult;
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
}
