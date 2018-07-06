package me.frank.demo.exception;

import com.google.gson.Gson;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static me.frank.demo.dto.AppResponse.failed;
import static me.frank.demo.exception.ServiceException.INTERNAL_SERVICE_ERROR;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * @author 王明哲
 */
@Component
@Order(HIGHEST_PRECEDENCE)
public class ServletExceptionHandler implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            writeErrorInResponse(e, (HttpServletResponse) servletResponse);
        }
    }

    private void writeErrorInResponse(Exception e, HttpServletResponse response) throws IOException {
        final String errorMessage = getErrorMessageFromException(e);
        final String responseBody = new Gson().toJson(failed(errorMessage));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(responseBody);
        response.flushBuffer();
    }

    private String getErrorMessageFromException(Exception e) {
        if (e.getCause() instanceof ServiceException) {
            return e.getCause().getMessage();
        } else {
            e.printStackTrace();
            return INTERNAL_SERVICE_ERROR.getMessage();
        }
    }

    @Override
    public void destroy() {
    }
}
