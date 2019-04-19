package service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import pojo.XslUserRegister;
import util.XslResult;


/**
 * 用户注册
 */
public interface UserService {
    /**
     * 注册
     * @param xslUserRegister
     * @return
     * @throws Exception
     */
    XslResult createUser(XslUserRegister xslUserRegister);

    /**
     * 快速注册
     * @param xslUserRegister
     * @return
     * @throws Exception
     */
    XslResult quickCreateUser(XslUserRegister xslUserRegister);

    /**
     * 上传图片
     *
     * @param uploadFile
     * @param phone
     * @return
     */
    XslResult createFile(MultipartFile uploadFile, String phone);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    XslResult userLogin(String username,  String password,  String token);

    /**
     * 检查Token被更换
     *
     * @param token
     * @return
     */
    XslResult getUserByToken(String token, String phone);

    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    SendSmsResponse verifyCode(@Param("phone") String phone);

    /**
     * 传递验证码
     *
     * @param phone
     * @return
     */
    XslResult sendMessageCode(String phone);
    /**
     * 检验手机验证码
     * @param phone
     * @return
     */
    XslResult checkcode(String phone, String code, String password);

    /**
     * 忘记密码
     * @return
     */
    XslResult Password(String phone, String password);


    /**
     * 学校，大学，专业种类
     *
     * @return
     */
    String schoolMessage();

    XslResult collegMessage(String school);

    XslResult majorMessage(String college,Integer schoolId);
}
