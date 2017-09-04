<%--
  Created by IntelliJ IDEA.
  User: besfo
  Date: 14.05.2017
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>CreateAccount</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/lib/jquery-confirm.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Main.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/HeaderOfSite.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/ForForm.css"/>
    <%--<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>--%>
    <%--<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>--%>

</head>

<body>
<%@ include file="/WEB-INF/jsp/HeaderOfSite.jsp" %>

<div class="container">

    <form id="accountForm" class="create" action="${pageContext.request.contextPath}/CreateAccountLogic" method="post"
          enctype="multipart/form-data">

        <div class="form-group">
            <label for="avatar">Photo</label>
            <input type="file" id="avatar" name="avatar" onchange="readURL(this);">
            <img id="changedAvatar" src="${pageContext.request.contextPath}/img/defaultAvatar.jpg" alt="your photo"
                 class="img-thumbnail" style="max-height:200px; max-width:200px; margin-top: 10px">
            <p class="help-block">Upload your photo</p>
        </div>


        <div id="Form">
            <div class="center-block">

                <div class="form-group">
                    <label for="test">Name</label>
                    <input id="test" name="name" type="text" class="form-control" placeholder="Enter name">
                </div>

                <div class="form-group">
                    <label for="Surname">Surname</label>
                    <input id="Surname" name="surname" type="text" class="form-control" placeholder="Enter surname">
                </div>

                <div class="form-group">
                    <label for="MiddleName">Middle name</label>
                    <input id="MiddleName" name="middleName" type="text" class="form-control"
                           placeholder="Enter middle name">
                </div>

                <div class="form-group">
                    <label for="DateOfBirth">Date of birth</label>
                    <input id="DateOfBirth" name="dateOfBirth" type="text" class="form-control"
                           placeholder="Enter date of birth" style="position: relative; z-index: 100000;">
                </div>

                <div class="form-group">
                    <label for="PersonalPhoneNumber">Personal phone number</label>
                    <div class="phone_param" id="persPhone1">
                        <div class="input-group" style="margin-bottom:7px;">
                            <input id="PersonalPhoneNumber" name="personalPhoneNumbers" type="text"
                                   class="form-control"
                                   placeholder="Enter personal phone number">
                            <span class="input-group-btn">
                            <button id="psPhoneDeleteBtn" class="btn btn-success" type="button"
                                    onclick="javascript:removeElementsOrElement(document.getElementById('persPhone1')); return false;">Delete</button>

                        </span>
                        </div>
                    </div>
                    <input type="button" id="addPersonalNumberBtn" class="btn btn-success"
                           style="margin-top:5px; display:block;" <%--onclick="addPsPhone()"--%> value="Add phone">
                </div>

                <div class="form-group">
                    <label for="WorkPhoneNumber">Work phone number</label>
                    <div class="phone_param" id="workPhone1">
                        <div class="input-group" style="margin-bottom:7px;">
                            <input id="WorkPhoneNumber" name="workPhoneNumbers" type="text" class="form-control"
                                   placeholder="Enter work phone number">
                            <span class="input-group-btn">
                            <button id="workPhoneDeleteBtn" class="btn btn-success"
                                    type="button"
                                    onclick="javascript:removeElementsOrElement(document.getElementById('workPhone1')); return false;">Delete</button>

                            </span>
                        </div>
                    </div>
                    <input type="button" id="addWorkNumberBtn" class="btn btn-success"
                           style="margin-top:5px; display:block;" <%--onclick="addWorkPhone()"--%> value="Add phone">
                </div>

                <div class="form-group">
                    <label for="HomeAddress">Home address</label>
                    <input id="HomeAddress" name="homeAddress" type="text" class="form-control"
                           placeholder="Enter home address">
                </div>

                <div class="form-group">
                    <label for="WorkAddress">Work address</label>
                    <input id="WorkAddress" name="workAddress" type="text" class="form-control"
                           placeholder="Enter work address">
                </div>

                <div class="form-group">
                    <label for="Email">Email</label>
                    <input id="Email" name="email" type="email" class="form-control" placeholder="Enter email">
                </div>

                <div class="form-group">
                    <label for="Password">Password</label>
                    <input id="Password" name="password" type="password" class="form-control"
                           placeholder="Enter password">
                </div>

                <div class="form-group">
                    <label for="ICQ">ICQ</label>
                    <input id="ICQ" name="icq" type="text" class="form-control"
                           placeholder="Enter ICQ">
                </div>

                <div class="form-group">
                    <label for="Skype">Skype</label>
                    <input id="Skype" name="skype" type="text" class="form-control"
                           placeholder="Enter Skype">
                </div>

                <div class="form-group">
                    <label for="AddInfo">Additional info</label>
                    <input id="AddInfo" name="additionInfo" type="text" class="form-control"
                           placeholder="Enter additional info">
                </div>
            </div>
        </div>

        <button id="createBtn" type="button" class="btn btn-success btn-block">Create account</button>

    </form>

</div>

<%--<script src="${pageContext.request.contextPath}/JS/lib/jquery-3.2.1.js"></script>--%>
<script src="${pageContext.request.contextPath}/JS/CreateAccount.js"></script>
<%--<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>--%>
<script src="${pageContext.request.contextPath}/JS/lib/jquery-confirm.min.js"></script>

</body>
</html>
