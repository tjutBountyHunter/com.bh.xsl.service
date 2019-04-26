package pojo;

import java.util.Date;

public class XslLog {
    private Integer id;

    private String ip;

    private String operation;

    private Date operationtime;

    private String operationer;

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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public String getOperationer() {
        return operationer;
    }

    public void setOperationer(String operationer) {
        this.operationer = operationer == null ? null : operationer.trim();
    }
}