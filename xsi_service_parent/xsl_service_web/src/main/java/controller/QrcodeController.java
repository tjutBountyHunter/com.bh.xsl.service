package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.XslResult;
import service.createQrcode;

import javax.servlet.http.HttpServletRequest;

/**
 * IBM
 *
 * @author 高山潍
 */
@Controller
@RequestMapping("/ibm")
public class QrcodeController {
//    @Autowired
//    private createQrcode qrcode;
//
//    @RequestMapping("/imageCode")
//    @ResponseBody
//    public XslResult coderUrl(@RequestParam("userId") int userId, HttpServletRequest request) {
//        XslResult ibmResult = null;
//        try {
//            ibmResult = qrcode.coderUrl(userId, request);
//            return ibmResult;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return XslResult.build(500, "服务器异常");
//        }
//    }
}
