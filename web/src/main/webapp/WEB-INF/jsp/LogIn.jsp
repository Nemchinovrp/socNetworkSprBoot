<%--
  Created by IntelliJ IDEA.
  User: besfo
  Date: 14.05.2017
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/ForForm.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Main.css"/>
</head>

<body>

<%@ include file="/WEB-INF/jsp/HeaderOfSite.jsp" %>
<div class="container">

    <div id="Form" class="row">
        <div class="center-block">
            <form method="POST" action="${pageContext.request.contextPath}/login" class="log-in">
                <span class="alert-info">${message}</span>
                <div class="form-group ${error != null ? 'has-error' : ''}">
                    <label for="InputEmail">Email</label>
                    <input id="InputEmail" name="email" type="email" class="form-control" placeholder="Enter email"
                           autofocus="true">
                </div>
                <div class="form-group">
                    <label for="InputPassword">Password</label>
                    <input id="InputPassword" name="password" type="password" class="form-control"
                           placeholder="Enter password">
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="remember-me">
                        Remember me
                    </label>
                </div>
                <span class="alert-danger">${error}</span>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-success btn-block">Log in</button>
            </form>
        </div>
    </div>

    <div class="row text-center" style="margin-top: 10px">
        <p style="margin-bottom:0px;">Do not register?</p>
        <a href="${pageContext.request.contextPath}/CreateAccount" style="margin-top:0px;">Create account</a>
    </div>

</div>

</body>
</html>
