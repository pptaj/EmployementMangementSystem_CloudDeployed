<%--
  Created by IntelliJ IDEA.
  User: Christopher Dsouza
  Date: 3/29/2017
  Time: 4:02 PM
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
    <title>EMS - Password Reset Success</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/header.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/login.css">
</head>
<body>
<%@include file="header.jsp" %>
<div class="login">
    <div><h2 id="errorMessage"><c:out value="${message}"/></h2></div>
    <div><a href="../../testJSP.jsp">HOME PAGE</a></div>
</div>
</body>
</html>