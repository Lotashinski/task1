package com.github.lotashinski.servlet.users;

import com.github.lotashinski.dto.RegistrationDto;
import com.github.lotashinski.dto.UserDto;
import com.github.lotashinski.entity.UserEntity;
import com.github.lotashinski.service.json.JsonConverter;
import com.github.lotashinski.service.res.ServiceFactory;
import com.github.lotashinski.service.res.UserService;
import com.github.lotashinski.util.ServletConstants;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/users/registration")
public final class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RegistrationDto registrationDto = JsonConverter.fromJsonRequest(request, RegistrationDto.class);
        ServiceFactory serviceFactory = (ServiceFactory) request.getAttribute(ServletConstants.SERVICE_FACTORY);
        UserService userService = serviceFactory.getUserService();

        UserEntity candidate = new UserEntity();
        candidate.setLogin(registrationDto.getLogin());
        candidate.setPassword(registrationDto.getPassword());

        UserEntity newUser = userService.registration(candidate);

        UserDto userDto = new UserDto();
        userDto.setId(newUser.getId());
        userDto.setLogin(newUser.getLogin());
        userDto.setRoles(newUser.getRoles());

        JsonConverter.toJsonResponse(userDto, response);
    }
}
