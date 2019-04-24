package contentApi;

import javax.servlet.http.HttpServletRequest;

public class inetAddressIp {
    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteUser();
        }
        return request.getHeader("x-forwarded-for");
    }
}


