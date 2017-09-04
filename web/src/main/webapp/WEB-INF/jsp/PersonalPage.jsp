<%--
  Created by IntelliJ IDEA.
  User: besfo
  Date: 14.05.2017
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>PersonalPage</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Main.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Content.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/ContentTop.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Wall.css"/>
    <%--<script src="${pageContext.request.contextPath}/Bootstrap/js/jquery-1.11.1.min.js"></script>--%>
</head>

<body>

<%@ include file="/WEB-INF/jsp/HeaderOfSite.jsp" %>

<div class="container content" style="margin-top: 80px;">

    <div class="row content-top">

        <div id="photoArea" class="col-md-4" style="padding: 10px; background-color: white;">
            <img src="${Avatar}" alt="your photo" class="img-thumbnail" style="max-height:480px;">

            <c:choose>
                <c:when test="${sessionScope.Account.id == requestScope.accountIdFromRequest}">
                    <a href="${pageContext.request.contextPath}/EditAccount" class="btn btn-success btn-block"
                       style="margin-top: 5px">Edit page</a>
                    <a id="showFriendsBtn" class="btn btn-success btn-block" style="margin-top: 5px;">Your friends</a>
                    <a id="gameLink" href="${pageContext.request.contextPath}/Game" class="btn btn-success btn-block"
                       style="margin-top: 5px;">Game</a>
                </c:when>
                <c:when test="${requestScope.isFriends}">
                    <div class="dropdown" style="margin-top: 5px;">
                        <button class="btn btn-default btn-block dropdown-toggle" type="button" id="Friend"
                                data-toggle="dropdown">
                            You are friends
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="Friend">
                            <li><a role="menuitem" tabindex="-1"
                                   href="${pageContext.request.contextPath}/RemoveFromFriends?currentAccId=${sessionScope.Account.id}
                            &accIdForRemovedFromFriends=${requestScope.accountIdFromRequest}">
                                Delete from friends</a></li>
                        </ul>
                    </div>
                </c:when>
                <c:when test="${requestScope.isSubscribed}">
                    <div class="dropdown" style="margin-top: 5px;">
                        <button class="btn btn-default btn-block dropdown-toggle" type="button" id="Subscribed"
                                data-toggle="dropdown">
                            You are subscribed
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="Subscribed">
                            <li><a role="menuitem" tabindex="-1"
                                   href="${pageContext.request.contextPath}/Unsubscribe?currentAccId=${sessionScope.Account.id}
                            &accIdForUnsubscribe=${requestScope.accountIdFromRequest}">To unsubscribe</a></li>
                        </ul>
                    </div>
                </c:when>
                <c:when test="${!requestScope.isFriends and !requestScope.isSubscribed and not empty sessionScope.Account}">
                    <a href="${pageContext.request.contextPath}/SendRequest?currentAccId=${sessionScope.Account.id}&accIdForSubscribe=${requestScope.accountIdFromRequest}"
                       class="btn btn-success btn-block" style="margin-top: 5px;">Send request to friends</a>
                </c:when>
            </c:choose>

        </div>

        <div id="areaForRenderedInfo" class="col-md-offset-1 col-md-7 text-left"
             style="padding: 20px; background-color: white;">
            <p><span class="lead">Name:</span>&nbsp;<u>${Account.name!=null ?Account.name:"-"}</u></p>
            <p><span class="lead">Surname:</span>&nbsp;<u>${Account.surname!=null?Account.surname:"-"}</u></p>
            <p><span class="lead">Middle name:</span>&nbsp;<u>${Account.middleName!=null?Account.middleName : "-"}</u>
            </p>
            <p><span class="lead">Date of birth:</span>&nbsp;<u>${Account.dateOfBirth!=null?Account.dateOfBirth:"-"}</u>
            </p>
            <p><span class="lead">Personal phone numbers:</span>
            <ul class="list-inline">
                <c:forEach items="${Account.personalPhoneNumbers}" var="phone">
                    <li><u>${phone.phone}</u></li>
                </c:forEach>
            </ul>
            </p>
            <p><span class="lead">Work phone numbers:</span>
            <ul class="list-inline">
                <c:forEach items="${Account.workPhoneNumbers}" var="phone">
                    <li><u>${phone.phone}</u></li>
                </c:forEach>
            </ul>
            </p>
            <p><span class="lead">Home address:</span>&nbsp;<u>${Account.homeAddress!=null?Account.homeAddress:"-"}</u>
            </p>
            <p><span class="lead">Work address:</span>&nbsp;<u>${Account.workAddress!=null?Account.workAddress:"-"}</u>
            </p>
            <p><span class="lead">Email:</span>&nbsp;<u>${Account.email!=null?Account.email:"-"}</u></p>
            <p><span class="lead">ICQ:</span>&nbsp;<u>${Account.icq!=null?Account.icq:"-"}</u></p>
            <p><span class="lead">Skype:</span>&nbsp;<u>${Account.skype!=null?Account.skype:"-"}</u></p>
            <p><span
                    class="lead">Addition info:</span>&nbsp;<u>${Account.additionInfo!=null?Account.additionInfo:"-"}</u>
            </p>

            <hr/>
        </div>

    </div>

</div>
<script src="${pageContext.request.contextPath}/JS/PersonalPage.js"></script>
<script src="${pageContext.request.contextPath}/Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
