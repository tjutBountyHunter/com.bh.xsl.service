package service;


import javax.servlet.http.HttpServletRequest;

public interface createQrcode {
    XslResult coderUrl(int userId, HttpServletRequest req);
}
