package com.github.lotashinski.servlet.users;

import com.github.lotashinski.dto.LoginDto;
import com.github.lotashinski.dto.UserDto;
import com.github.lotashinski.entity.UserEntity;
import com.github.lotashinski.service.json.JsonConverter;
import com.github.lotashinski.service.res.ServiceFactory;
import com.github.lotashinski.service.res.UserService;
import com.github.lotashinski.util.ServletConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/users/login")
public final class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginDto loginDto = JsonConverter.fromJsonRequest(req, LoginDto.class);

        ServiceFactory serviceFactory = (ServiceFactory) req.getAttribute(ServletConstants.SERVICE_FACTORY);
        UserService userService = serviceFactory.getUserService();

        UserEntity user = userService.login(loginDto.getLogin(), loginDto.getPassword().toCharArray());
        HttpSession session = req.getSession();
        session.setAttribute(ServletConstants.USER, user);

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setRoles(user.getRoles());

        JsonConverter.toJsonResponse(userDto, resp);
    }
}
