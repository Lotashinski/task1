package com.github.lotashinski.servlet.users;

import com.github.lotashinski.service.json.JsonConverter;
import com.github.lotashinski.util.ServletConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/users/logout")
public final class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(ServletConstants.USER, null);
        JsonConverter.toJsonResponse("LOGOUT", resp);
    }
}
