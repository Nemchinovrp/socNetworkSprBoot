package com.getjavajob.bezfamilnyydg.common.util;

import com.getjavajob.bezfamilnyydg.models.Account;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.getjavajob.bezfamilnyydg.logIn.Constants.*;


public class SessionHelper {

    public static void addInfoForLoggedUserInSession(HttpServletRequest request, Account account) {
        HttpSession httpSession = request.getSession();

        setIsLogged(request);
        removeIsLoggedFailed(request);
        httpSession.setAttribute(CURRENT_ACCOUNT, account);
    }

    public static void setIsLogged(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        httpSession.setAttribute(IS_LOGGED, true);

        httpSession.removeAttribute(IS_LOGGED_FAILED);
    }

    public static void logOut(HttpSession session, HttpServletResponse response) {
        if (session != null) {

            // delete cookie remember me
            Cookie cookie = new Cookie(COOKIE_USER_ID, "delete");
            cookie.setMaxAge(0);
            response.addCookie(cookie);

            session.removeAttribute(IS_LOGGED);
            session.removeAttribute(CURRENT_ACCOUNT);
            session.invalidate();

        }
    }

    public static void setIsLoggedFailed(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(IS_LOGGED_FAILED, true);
    }

    public static void removeIsLoggedFailed(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(IS_LOGGED_FAILED);
    }

    public static boolean isExistCurrentAccountInSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute(CURRENT_ACCOUNT) != null;
    }

}
