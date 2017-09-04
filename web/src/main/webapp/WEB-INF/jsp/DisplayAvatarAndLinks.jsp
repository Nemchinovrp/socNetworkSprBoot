<%--
  Created by IntelliJ IDEA.
  User: besfo
  Date: 22.08.2017
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul class="nav nav-tabs" role="tablist">
    <li><a class="btn" id="showFriendsTab">Friends</a></li>
    <li><a class="btn" id="showTakenRequests">Taken requests</a></li>
    <li><a class="btn" id="showSentRequests">Sent requests</a></li>
</ul>

<c:choose>
    <c:when test="${fn:length(DTOAccountAvatarMap) > 0 }">
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
    </c:when>
    <c:otherwise>
        <p style="margin-top:30px;">No results, use search in header</p>
    </c:otherwise>
</c:choose>

