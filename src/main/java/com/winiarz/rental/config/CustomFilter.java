package com.winiarz.rental.config;

import com.winiarz.rental.model.dto.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        String requestUri = req.getRequestURI();

        if (requestUri.endsWith("/")) {
            resp.sendRedirect(resp.encodeRedirectURL("login"));
        } else if (
                userDto != null ||
                        requestUri.endsWith(".css") ||
                        requestUri.endsWith(".jpg") ||
                        requestUri.endsWith(".png") ||
                        requestUri.endsWith("bootstrap.min.js") ||
                        requestUri.endsWith("login")  ||
                        requestUri.endsWith("register") ||
                        requestUri.endsWith("dataPrivacy") ||
                        requestUri.endsWith("contact") ||
                        requestUri.endsWith("warehouseItemList") ||
                        requestUri.endsWith("resetPassword") ||
                        requestUri.endsWith("confirmEmail")
        ) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect(resp.encodeRedirectURL("login"));
        }

//        req.getSession().setAttribute("user", new UserDto(1, "a@a", UserRole.ADMIN));
//        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
