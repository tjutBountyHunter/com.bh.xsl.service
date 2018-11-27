package service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.sun.org.glassfish.external.probe.provider.annotations.ProbeParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import pojo.XslUserRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户注册
 */
public interface UserService {
    /**
     * 注册
     * @param all
     * @return
     * @throws Exception
     */
    XslResult createUser(String all);

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
    XslResult userLogin(@Param("username") String username, @Param("password") String password, @ProbeParam("token") String token
            , HttpServletRequest request, HttpServletResponse response);

    /**
     * 检查Token被更换
     *
     * @param token
     * @return
     */
    XslResult getUserByToken(String token, String phone);

    /**
     * 账号密码核对
     *
     * @param content
     * @return
     */
    XslResult checkData(String content);

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
     * 下一步
     * @param phone
     * @return
     */
    XslResult checkcode(String phone, String code, String password);

    /**
     * 用户数据库表
     *
     * @param xslUserRegister
     * @param schoolId
     * @return
     */
    XslResult createUseruser(XslUserRegister xslUserRegister, Integer schoolId);
    /**
     * 学校种类
     * @return
     */
    XslResult Password(String phone, String password);

    /**
     * 学校表
     *
     * @param xslUserRegister
     * @return
     */
    XslResult createUserSchool(XslUserRegister xslUserRegister);

    /**
     * 学校，大学，专业种类
     *
     * @return
     */
    String schoolMessage();

    XslResult collegMessage(String school);

    XslResult majorMessage(String college,Integer schoolId);
}
