package service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * 用户注册
 */
public interface UserService {
    /**
     * 注册
     *
     * @param user
     * @param schoolUser
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    XslResult createUser(String user, String schoolUser, MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    XslResult userLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 检查Token被更换
     *
     * @param token
     * @return
     */
    XslResult getUserByToken(String token);

    /**
     * 账号密码核对
     *
     * @param content
     * @param type
     * @return
     */
    XslResult checkData(String content, String type);

    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    SendSmsResponse excute(@Param("phone") String phone);

    /**
     * 短信验证码验证
     *
     * @param phone
     * @return
     */
    String checkcode(String phone);

    /**
     * 学校种类
     *
     * @return
     */
    XslResult Password(String phone, String password);

    /**
     * 学校，大学，专业种类
     *
     * @return
     */
    String schoolMessage();

    String collegMessage(String school);

    String majorMessage(String college);
}
