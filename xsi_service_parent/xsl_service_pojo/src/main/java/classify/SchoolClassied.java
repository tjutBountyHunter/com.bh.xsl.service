package classify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SchoolClassied {
    //为空
    private static byte degree_int = -1;

    /**
     * 学位数据库输入
     *
     * @param request
     * @param response
     * @return
     */
    public static byte school(HttpServletRequest request, HttpServletResponse response) {
        String degree = request.getParameter("u_education");
        if (degree.isEmpty() && degree.length() == 0) {
            return degree_int;
        } else {
            if (degree.equals("primary")) {
                degree_int = 0;
            } else if (degree.equals("Junior")) {
                degree_int = 1;
            } else if (degree.equals("undergraduate")) {
                degree_int = 2;
            } else if (degree.equals("technicalSecondary")) {
                degree_int = 3;
            } else if (degree.equals("senior")) {
                degree_int = 4;
            } else if (degree.equals("specialty")) {
                degree_int = 5;
            } else if (degree.equals("masterDegreeCandidate")) {
                degree_int = 6;
            } else if (degree.equals("doctoralCandidate")) {
                degree_int = 7;
            }
            return degree_int;
        }
    }
}
