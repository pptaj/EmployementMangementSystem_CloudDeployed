<%--
  Created by IntelliJ IDEA.
  User: Christopher Dsouza
  Date: 3/29/2017
  Time: 5:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>EMS - Reset Password</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/header.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="login">
    <form action="resetPassword.htm" commandName="person" method="POST">
        <div><h2 id="member">Reset Password</h2></div>
        <div>
            <div><h2 id="errorMessage"><c:out value="${message}"/></h2></div>
            NEW PASSWORD <input type="password" path="password" required="required" class="inputArea" id="password" name ="password" onchange="checkPassword()"/>
            CONFIRM PASSWORD <input type="password" path="password" required="required" class="inputArea" id="confirmpassword" name ="confirmpassword"/>
            <input type="hidden" name="email" value="<%= request.getParameter("username") %>" />
            <div>
                <input type="submit" value="SUBMIT" id="submitButton" onclick="verifyPassword()"/>
            </div>
        </div>
    </form>
</div>

<script>
    function checkPassword(){
        var x = document.getElementById("password").value;
        var txt = x.replace(/[^A-Za-z0-9\s]/g,"");
        document.getElementById("password").value = txt;
    }

    function verifyPassword(){
        var newPass = document.getElementById("password").value;
        var confirmPass = document.getElementById("confirmpassword").value;
        if(newPass!=confirmPass){
            document.getElementById("errorMessage").innerHTML = "Passwords do not match";
            return true;
        }
    }
</script>

</body>
</html>