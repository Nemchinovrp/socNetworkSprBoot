<%--
  Created by IntelliJ IDEA.
  User: besfo
  Date: 27.05.2017
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Search</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Main.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/HeaderOfSite.css"/>
</head>
<body>
<%@ include file="/WEB-INF/jsp/HeaderOfSite.jsp" %>

<div class="container" style="background:white; margin-top:80px;">

    <c:choose>
        <c:when test="${fn:length(DTOAccountAvatarMap) > 0}">
            <div class="row" style="padding:30px;">
                <div class="col-md-12">
                    <c:set var="countOfAccount" value="-1"/>
                    <c:forEach items="${DTOAccountAvatarMap}" var="entry">
                        <c:set var="countOfAccount" value="${countOfAccount + 1}"/>
                        <div class="row">
                            <div class="col-md-offset-5 col-md-7">
                                <img id="AccImg${countOfAccount}" src="${entry.value}"
                                     alt="Avatar" class="img-thumbnail" style="width:120px; height:120px;">
                                <a id="AccLinkToPage${countOfAccount}"
                                   href="${pageContext.request.contextPath}/PageFromSearch?id=${entry.key.id}">${entry.key.name} ${" "} ${entry.key.surname}</a>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>
                </div>
            </div>

            <div class="row" style="margin-bottom:10px; padding:30px;">
                <div class="col-md-2">
                    <button id="previousPageBtn" class="btn btn-success btn-block pull-left clearfix"
                            style="display:none;">previous page
                    </button>
                </div>
                <div class="col-md-8">
                    <p id="displayPage" class="text-center">Page 1 of ${CountPagesWithResultSearch}</p>
                </div>
                <div class="col-md-2">
                    <button id="nextPageBtn" class="btn btn-success btn-block pull-right clearfix"
                            style="display:none;">next page
                    </button>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row" style="padding:50px;">
                <div class="col-md-12 text-center">
                    <p>Has no results, try again =)</p>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

    <div class="hidden" id="CountAccountsPerPage">${CountAccountsPerPage}</div>
    <div class="hidden" id="CountPagesWithResultSearch">${CountPagesWithResultSearch}</div>
    <div class="hidden" id="Pattern">${CurrentPatternForSearch}</div>

</div>

<script src="${pageContext.request.contextPath}/JS/SearchAccount.js"></script>
</body>
</html>
