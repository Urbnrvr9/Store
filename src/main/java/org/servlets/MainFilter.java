package org.servlets;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@WebFilter(filterName = "mainFilter", urlPatterns = {"/*"})
public class MainFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        createSession(request);
        filterChain.doFilter(request,response);
    }

    private void createSession(ServletRequest request) {
        var session = ((HttpServletRequest) request).getSession(true);
        if (session.isNew()) {
            session.setMaxInactiveInterval(60 * 5);
            MDC.put("SESSIONID", session.getId());
            log.info("Создана новая сессия");
        } else {
            Arrays.stream(((HttpServletRequest) request).getCookies())
                    .filter(cookie -> "JSESSIONID".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findAny()
                    .ifPresent(id -> MDC.put("SESSIONID", id));
        }
    }
}
