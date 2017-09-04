package com.getjavajob.bezfamilnyydg.logIn.filters.checkLogged;

import com.getjavajob.bezfamilnyydg.common.exceptions.InitException;
import com.getjavajob.bezfamilnyydg.common.util.SessionHelper;
import com.getjavajob.bezfamilnyydg.models.Account;
import com.getjavajob.bezfamilnyydg.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.getjavajob.bezfamilnyydg.common.util.SessionHelper.isExistCurrentAccountInSession;
import static com.getjavajob.bezfamilnyydg.common.util.SessionHelper.setIsLogged;
import static com.getjavajob.bezfamilnyydg.logIn.Constants.*;

public class CheckLogged implements HandlerInterceptor {
    @Autowired
    private AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        boolean isAllowedUrl = checkUrlForAllow(httpServletRequest);
        if (userIsLogged(httpServletRequest) || isAllowedUrl) {
            return true;
        } else {
            if (isHasCookieForAutoLogIn(httpServletRequest, COOKIE_USER_ID)) {
                return true;
            } else {
                httpServletResponse.sendRedirect("/LogIn");
                return false;
            }
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    private boolean checkUrlForAllow(HttpServletRequest httpServletRequest) {
        String URI = httpServletRequest.getRequestURI();

        for (String allowed : ALLOWED_ENDS_FOR_URLs) {
            if (URI.endsWith(allowed) || URI.matches(allowed)) {
                return true;
            }
        }

        return false;
    }

    private boolean userIsLogged(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        Object isLogged = httpSession.getAttribute(IS_LOGGED);

        return isLogged != null;
    }

    private boolean isHasCookieForAutoLogIn(HttpServletRequest request, String cookieName) throws InitException {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName) && cookie.getValue() != null) {
                    if (!isExistCurrentAccountInSession(request)) {
                        setIsLoggedWithChecks(cookie, request);
                    } else {
                        setIsLogged(request);
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private void setIsLoggedWithChecks(Cookie cookie, HttpServletRequest request) throws InitException {
        try {
            int id = Integer.parseInt(cookie.getValue());
            Account account = accountService.getById(id);
            if (account != null) {
                SessionHelper.addInfoForLoggedUserInSession(request, account);
            } else {
                throw new InitException("Account with id = " + id + " not found");
            }
        } catch (NumberFormatException e) {
            throw new InitException("Can not parse id=" + cookie.getValue() + " from cookie " + COOKIE_USER_ID);
        }
    }
}
