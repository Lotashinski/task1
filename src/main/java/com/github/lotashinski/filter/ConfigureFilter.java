package com.github.lotashinski.filter;

import com.github.lotashinski.dto.ExceptionDto;
import com.github.lotashinski.entity.UserEntity;
import com.github.lotashinski.filter.exception.ForbiddenException;
import com.github.lotashinski.repository.RepositoryFactory;
import com.github.lotashinski.service.json.JsonConverter;
import com.github.lotashinski.service.res.ServiceFactory;
import com.github.lotashinski.util.ServletConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public final class ConfigureFilter implements Filter {

    private static final Logger logger = LogManager.getRootLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Config filter call");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();
        logger.debug("Servlet path: " + servletPath);

        logger.debug("Configure repository");
        try (RepositoryFactory repositoryFactory = new RepositoryFactory()) {
            logger.debug("Set attribute " + ServletConstants.REPOSITORY_FACTORY);
            servletRequest.setAttribute(ServletConstants.REPOSITORY_FACTORY, repositoryFactory);

            ServiceFactory serviceFactory = new ServiceFactory(repositoryFactory);
            logger.debug("Set attribute " + ServletConstants.SERVICE_FACTORY);
            servletRequest.setAttribute(ServletConstants.SERVICE_FACTORY, serviceFactory);

            logger.debug("Check user");
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute(ServletConstants.USER);

            if (null != user) {
                logger.debug("Add userEntity from HttpSession in current Hibernate session");
                repositoryFactory.getSession().merge(user);
                logger.debug("Set attribute " + ServletConstants.USER);
                servletRequest.setAttribute(ServletConstants.USER, user);
            }else {
                logger.debug("HttpSession user not found. Check access for anon anonymous");
                if (!servletPath.equals("/users/login") && !servletPath.equals("/users/registration")){
                    throw new ForbiddenException("Forbidden");
                }
            }

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            logger.error("Request exception", e);

            logger.debug("Configure exception response");
            ExceptionDto eDto = configureExceptionMessage(e);
            JsonConverter.toJsonResponse(eDto, response);
            response.setStatus(eDto.getStatus());
        }
    }

    @Override
    public void destroy() {
    }

    private static ExceptionDto configureExceptionMessage(Exception e) {
        ExceptionDto eDto = new ExceptionDto();
        eDto.setMessage(e.getMessage());
        eDto.setStatus(500);
        return eDto;
    }
}
