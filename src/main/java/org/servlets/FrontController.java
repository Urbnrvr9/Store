package org.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(name = "main", urlPatterns = {"/", "/main"})
public class FrontController extends HttpServlet  {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var path = isLoggedIn(request.getSession())
                ? "/catalog"
                : "/authentication";
        request.getRequestDispatcher(path).forward(request, response);
    }

    private boolean isLoggedIn(HttpSession session) {
        return !session.isNew()
                && Objects.nonNull(session.getAttribute("username"))
                && Objects.nonNull(session.getAttribute("password"));
    }
}
