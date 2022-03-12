package com.github.lotashinski.servlet.users;

import com.github.lotashinski.dto.UserDto;
import com.github.lotashinski.entity.UserEntity;
import com.github.lotashinski.service.json.JsonConverter;
import com.github.lotashinski.util.ServletConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowUserServlet", urlPatterns = "/users/me")
public final class ShowCurrentUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity user = (UserEntity) req.getAttribute(ServletConstants.USER);

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setRoles(user.getRoles());

        JsonConverter.toJsonResponse(userDto, resp);
    }
}
