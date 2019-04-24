package pojo;

import java.io.Serializable;

/**
 * @Auther: 11432_000
 * @Date: 2018/9/11 13:10
 * @Description:
 */
public class AlipayReturn implements Serializable {
    //标志支付请求成功与否
    private String isSuccess;
    //请求错误描述
    private String subMsg;
    //网关返回码描述
    private String msg;

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
