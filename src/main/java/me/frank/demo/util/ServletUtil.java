package me.frank.demo.util;

import me.frank.demo.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static me.frank.demo.properties.SecurityConst.ATTR_ERROR;
import static me.frank.demo.properties.SecurityConst.AUTH_FAILED_URL;

/**
 * @author 王明哲
 */
public class ServletUtil {

    /**
     * URL转向
     * @param request 请求
     * @param response 回应
     */
    private static void forward(HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(AUTH_FAILED_URL);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转向异常链接（返回异常信息给用户）
     *
     * @param request   请求
     * @param response  回应
     * @param exception 异常信息
     */
    public static void goError(HttpServletRequest request,
                               HttpServletResponse response,
                               ServiceException exception) {
        request.setAttribute(ATTR_ERROR, exception);
        forward(request, response);
    }
}
