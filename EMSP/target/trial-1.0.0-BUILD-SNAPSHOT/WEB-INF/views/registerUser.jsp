<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Data Creation</title>
<link rel="stylesheet" type="text/css" href="resources/css/header.css">
<link rel="stylesheet" type="text/css" href="resources/css/registerUser.css">
<style>
		body{
			margin:0;
			paddding:0;
			background-image: url("http://www.atlaviation.com/wp-content/uploads/2014/10/fit.jpg");
			background-image: url("http://www.raffaelphoto.com/wp-content/uploads/2013/01/new-york-city-at-night-skyline-panorama1.jpg");
			
    		background-repeat: no-repeat;
    		background-size:cover;
		}
		
		input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button {
			-webkit-appearance: none; 
    		margin: 0; 
		}
	</style>
</head>
<body>
	<%@include file="header.jsp" %>
	<form:form><span class="rightHeadingDiv">Hello <c:out value="${sessionScope.person.firstName }"/> [<a href="logout.htm">Logout</a>]</span></form:form>
	<div class="register">
	<form:form action="registeruser.htm" commandName="employee" method="POST">
	<div><h2 id="member">Employee Data Creation</h2></div>
	<div><h2 id="errorMessage"><c:out value="${message}"/></h2></div>
	<div>
		FIRST NAME <form:input type="text"  class="inputArea" placeholder="Employee First Name" path="firstName" name ="firstname" id="firstname" required="required" onchange="checkFirstName()"/>
		LAST NAME <form:input type="text" class="inputArea" placeholder="Employee Last Name" path="lastName" name ="lastname" id="lastname" required="required" onchange="checkLastName()"/>
		EMPLOYEE NUMBER <form:input type="number" class="inputArea" placeholder="Employee ID" path="empID" name ="empid" id="empid" required="required" max ="99999999" onchange="checkEmpID"/>
		EMAIL ADDRESS <form:input type="email" class="inputArea" placeholder="Employee Office email address" path="emailAddress" name ="emailid" id="emailid" required="required" onchange="checkEmail()"/>
		PASSWORD <form:input type="password" class="inputArea" placeholder="Password" path="password" name ="password" id="password" required="required" onchange="checkPassword()"/>
		PHONE NUMBER <form:input type="number" class="inputArea" placeholder="Employee Phone Number" path="phoneNumber" name ="phnno" id="phnno" required="required" max="9999999999" onchange="checkPhnNo()"/>
		DESIGNATION <form:input type="text" class="inputArea" placeholder="Employee Designation" path="designation" name ="desig" id="desig" required="required" onchange="checkDesig()"/>
		ROLE <br/><form:radiobutton path="userRole" name="role" value="ADMIN" id="adminRole" class="rado"/>ADMIN<br/>
			<form:radiobutton path="userRole" name="role" value="EMPLOYEE" id="employeeRole" checked="checked" class="rado"/> EMPLOYEE <p></p>
		MANAGER EMAIL ADDRESS <form:input type="email" class="inputArea" placeholder="Manager Office email address" path="managerEmail" name ="mngremailid" id="mngremailid" required="required" onchange="checkmngrEmail()"/>
		STREET NAME <form:input type="text" class="inputArea" placeholder="Address" path="streetName" name ="street" id="street" required="required" onchange="checkStreet()"/>
		CITY <form:input type="text" class="inputArea" placeholder="City" path="city" name ="city" id="city" required="required" onchange="checkCity()"/>
		STATE <form:input type="text" class="inputArea" placeholder="State" path="state" name ="state" id="state" required="required" onchange="checkState()"/>
		ZIPCODE <form:input type="number" class="inputArea" placeholder="Zipcode" path="zipCode" name ="zip" id="zip" required="required" max="99999" onchange="checkZip()"/>
		<div>
			<input type="submit" value="Create User" id="submitButton"/>
		</div> 
	</div>
	</form:form>
	</div>
</body>
<script>
function checkFirstName(){
	var x = document.getElementById("firstname").value;
	var txt = x.replace(/[^A-Za-z]/g,"");
    document.getElementById("firstname").value = txt;
}

function checkLastName(){
	var x = document.getElementById("lastname").value;
	var txt = x.replace(/[^A-Za-z]/g,"");
	document.getElementById("lastname").value = txt;
}

function checkEmpID(){
    var x = document.getElementById("empid").value;
    var txt = x.replace(/[^0-9]/g,"");
    document.getElementById("empid").value = txt;
}

function checkEmail(){
	var x = document.getElementById("emailid").value;
	var txt = x.replace(/[^A-Za-z0-9;@;.]/g,"");
    document.getElementById("emailid").value = txt;
}

function checkPassword(){
	var x = document.getElementById("password").value;
	var txt = x.replace(/[^A-Za-z0-9\s]/g,"");
    document.getElementById("password").value = txt;
}

function checkPhnNo(){
	var x = document.getElementById("phnno").value;
	var txt = x.replace(/[^0-9]/g,"");
    document.getElementById("phnno").value = txt;
} 

function checkDesig(){
	var x = document.getElementById("desig").value;
	var txt = x.replace(/[^A-Za-z]/g,"");
    document.getElementById("desig").value = txt;
}

function checkmngrEmail(){
	var x = document.getElementById("mngremailid").value;
	var txt = x.replace(/[^A-Za-z0-9;@;.]/g,"");
    document.getElementById("mngremailid").value = txt;
}

function checkStreet(){
	var x = document.getElementById("street").value;
	var txt = x.replace(/[^A-Za-z0-9\s]/g,"");
    document.getElementById("street").value = txt;
}

function checkCity(){
	var x = document.getElementById("city").value;
	var txt = x.replace(/[^A-Za-z\s]/g,"");
    document.getElementById("city").value = txt;
}

function checkState(){
	var x = document.getElementById("state").value;
	var txt = x.replace(/[^A-Za-z\s]/g,"");
    document.getElementById("state").value = txt;
}


function checkZip(){
	var x = document.getElementById("zip").value;
	var txt = x.replace(/[^0-9]/g,"");
    document.getElementById("zip").value = txt;
}

</script>
</html>