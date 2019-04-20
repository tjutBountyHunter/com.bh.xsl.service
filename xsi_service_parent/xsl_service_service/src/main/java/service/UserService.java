package service;

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
     * 忘记密码
     * @return
     */
    XslResult Password(String phone, String password);
}
