package pojo;

import java.util.Date;

public class XslThrowinglog {
    private Integer id;

    private String ip;

    private String throwing;

    private String operationer;

    private Date throwingtime;

    private String methodname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getThrowing() {
        return throwing;
    }

    public void setThrowing(String throwing) {
        this.throwing = throwing == null ? null : throwing.trim();
    }

    public String getOperationer() {
        return operationer;
    }

    public void setOperationer(String operationer) {
        this.operationer = operationer == null ? null : operationer.trim();
    }

    public Date getThrowingtime() {
        return throwingtime;
    }

    public void setThrowingtime(Date throwingtime) {
        this.throwingtime = throwingtime;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname == null ? null : methodname.trim();
    }
}