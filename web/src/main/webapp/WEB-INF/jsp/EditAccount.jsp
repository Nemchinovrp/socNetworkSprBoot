<%--
  Created by IntelliJ IDEA.
  User: besfo
  Date: 23.05.2017
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditAccount</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/lib/jquery-confirm.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Main.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/HeaderOfSite.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/ForForm.css"/>

</head>
<body>

<%@ include file="/WEB-INF/jsp/HeaderOfSite.jsp" %>

<div class="container" style="margin-top:0px;">

    <form id="accountForm" class="edit" action="${pageContext.request.contextPath}/EditAccountLogic" method="post"
          enctype="multipart/form-data">

        <div class="form-group">
            <label for="avatar">Photo</label>
            <input type="file" id="avatar" name="avatar" onchange="readURL(this)">
            <img id="changedAvatar" src="${Avatar}" alt="your photo" class="img-thumbnail"
                 style="max-height:200px; max-width:200px; margin-top: 10px">
            <p class="help-block">Change your photo</p>
        </div>

        <div class="form-group hidden">
            <label for="Surname">id</label>
            <input value="${Account.id}" id="id" name="id" type="text" class="form-control">
        </div>

        <div id="Form">
            <div class="center-block">

                <div class="form-group">
                    <label for="Name">Name</label>
                    <input value="${Account.name}" id="Name" name="name" type="text" class="form-control"
                           placeholder="Enter name">
                </div>

                <div class="form-group">
                    <label for="Surname">Surname</label>
                    <input value="${Account.surname}" id="Surname" name="surname" type="text" class="form-control"
                           placeholder="Enter surname">
                </div>

                <div class="form-group">
                    <label for="MiddleName">Middle name</label>
                    <input value="${Account.middleName}" id="MiddleName" name="middleName" type="text"
                           class="form-control"
                           placeholder="Enter middle name">
                </div>

                <div class="form-group">
                    <label for="DateOfBirth">Date of birth</label>
                    <input value="${Account.dateOfBirth}" id="DateOfBirth" name="dateOfBirth" type="text"
                           class="form-control"
                           placeholder="Enter date of birth" style="position: relative; z-index: 100000;">
                </div>

                <div class="form-group">
                    <label>Personal phone number</label>
                    <c:set var="indexForPersPhone" value="-1"/>
                    <c:forEach items="${Account.personalPhoneNumbers}" var="phone">
                        <c:set var="indexForPersPhone" value="${indexForPersPhone + 1}"/>
                        <div class="phone_param">
                            <div class="input-group" style="margin-bottom:7px;">
                                <input id="PersonalPhoneNumber${indexForPersPhone}" name="personalPhoneNumbers"
                                       type="text"
                                       class="form-control"
                                       placeholder="Enter personal phone number" value="${phone.phone}">
                                <span class="input-group-btn">
                                <button name="psPhoneDeleteBtn" id="psPhoneDeleteBtn${indexForPersPhone}"
                                        class="btn btn-success" type="button"
                                        onclick="javascript:removeElementsOrElement(document.getElementById('PersonalPhoneNumber${indexForPersPhone}').parentNode.parentNode); return false;">Delete</button>
                            </span>
                            </div>
                        </div>
                    </c:forEach>
                    <input type="button" id="addPersonalNumberBtn" class="btn btn-success"
                           style="margin-top:5px;display:block;" value="Add phone">
                </div>

                <div class="form-group">
                    <label>Work phone number</label>
                    <c:set var="indexForWorkPhone" value="-1"/>
                    <c:forEach items="${Account.workPhoneNumbers}" var="phone">
                        <c:set var="indexForWorkPhone" value="${indexForWorkPhone + 1}"/>
                        <div class="phone_param">
                            <div class="input-group" style="margin-bottom: 7px">
                                <input id="WorkPhoneNumber${indexForWorkPhone}" name="workPhoneNumbers" type="text"
                                       class="form-control"
                                       placeholder="Enter work phone number" value="${phone.phone}">
                                <span class="input-group-btn">
                                <button name="workPhoneDeleteBtn" id="workPhoneDeleteBtn${indexForWorkPhone}"
                                        class="btn btn-success"
                                        type="button"
                                        onclick="javascript:removeElementsOrElement(document.getElementById('WorkPhoneNumber${indexForWorkPhone}').parentNode.parentNode); return false;">Delete</button>
                            </span>
                            </div>
                        </div>
                    </c:forEach>
                    <input type="button" id="addWorkNumberBtn" class="btn btn-success"
                           style="margin-top:5px;display:block;" value="Add phone">
                </div>

                <div class="form-group">
                    <label for="HomeAddress">Home address</label>
                    <input value="${Account.homeAddress}" id="HomeAddress" name="homeAddress" type="text"
                           class="form-control"
                           placeholder="Enter home address">
                </div>

                <div class="form-group">
                    <label for="WorkAddress">Work address</label>
                    <input value="${Account.workAddress}" id="WorkAddress" name="workAddress" type="text"
                           class="form-control"
                           placeholder="Enter work address">
                </div>

                <div class="form-group">
                    <label for="Email">Email</label>
                    <input value="${Account.email}" id="Email" name="email" type="email" class="form-control"
                           placeholder="Enter email">
                </div>

                <div class="form-group">
                    <label for="Password">Password</label>
                    <input id="Password" name="password" type="password" class="form-control"
                           placeholder="If you want to change it, then enter new password">
                </div>

                <div class="form-group">
                    <label for="ICQ">ICQ</label>
                    <input value="${Account.icq}" id="ICQ" name="icq" type="text" class="form-control"
                           placeholder="Enter ICQ">
                </div>

                <div class="form-group">
                    <label for="Skype">Skype</label>
                    <input value="${Account.skype}" id="Skype" name="skype" type="text" class="form-control"
                           placeholder="Enter Skype">
                </div>

                <div class="form-group">
                    <label for="AddInfo">Additional info</label>
                    <input value="${Account.additionInfo}" id="AddInfo" name="additionInfo" type="text"
                           class="form-control"
                           placeholder="Enter additional info">
                </div>

            </div>
        </div>

        <div class="col-md-6" style="margin-bottom:15px;">
            <label for="UploadXML" class="btn btn-default btn-block">
                <h>Upload XML</h>
                <span class="glyphicon glyphicon-upload" aria-hidden="true"></span>
                <input type="file" id="UploadXML" style="display:none;">
            </label>
        </div>
        <div class="col-md-6" style="margin-bottom:15px;">
            <a class="btn btn-default btn-block" id="DownloadXML" target="_blank" download="account.xml">
                Download XML
                <i class="glyphicon glyphicon-download"></i>
            </a>
        </div>

        <button id="editAccBtn" type="button" class="btn btn-success btn-block">Edit
            account
        </button>

    </form>
</div>

<%--<script src="${pageContext.request.contextPath}/JS/lib/jquery-3.2.1.js"></script>--%>
<script src="${pageContext.request.contextPath}/JS/EditAccount.js"></script>
<%--<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>--%>
<script src="${pageContext.request.contextPath}/JS/lib/jquery-confirm.min.js"></script>
</body>
</html>
