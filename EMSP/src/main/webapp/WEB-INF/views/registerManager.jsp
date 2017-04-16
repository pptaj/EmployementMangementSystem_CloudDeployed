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
</head>
<body>
	<%@include file="header.jsp" %>
	<form:form><span class="rightHeadingDiv">Hello <c:out value="${sessionScope.person.firstName }"/> [<a href="logout.htm">Logout</a>]</span></form:form>
	<div class="register">
	<form:form action="registermngr.htm" commandName="manager" method="POST">
	<div><h2 id="member">Manager Data Creation</h2></div>
	<div>
		FIRST NAME <form:input type="text"  class="inputArea" placeholder="Employee First Name" path="firstName" name ="firstname" id="firstname" required="required" onchange="validateFirstName()" />
		LAST NAME <form:input type="text" class="inputArea" placeholder="Employee Last Name" path="lastName" name ="lastname" id="lastname" required="required" onchange="validateLastName()"/>
		EMPLOYEE NUMBER <form:input type="number" class="inputArea" placeholder="Employee ID" path="empID" name ="empid" id="empid" required="required" onchange="validateEmployeeID()" />
		EMAIL ADDRESS <form:input type="email" class="inputArea" placeholder="Employee Office email address" path="emailAddress" name ="emailid" id="emailid" required="required" onchange="validateEmail()"/>
		PASSWORD <form:input type="password" class="inputArea" placeholder="Password" path="password" name ="password" id="password" required="required" onchange="validatePassword()" />
		PHONE NUMBER <form:input type="text" class="inputArea" placeholder="Employee Phone Number" path="phoneNumber" name ="phnno" id="phnno" required="required" onchange ="checkPhnNo()" />
		DESIGNATION <form:input type="text" class="inputArea" placeholder="Employee Designation" path="designation" name ="desig" id="desig" required="required" onchange ="validateDesignation()"/>
		ROLE <br/><form:input path="userRole" name="role" value="MANAGER" id="managerRole" class="inputArea" disabled="disabled"/>
		MANAGER ID <form:input type="number" class="inputArea" placeholder="Manager ID" path="managerID" name ="mngrid" id="mngrid" required="required" onchange="validateMngrID()"/>
		STREET NAME <form:input type="text" class="inputArea" placeholder="Address" path="streetName" name ="street" id="street" required="required" onchange="validateStreet()" />
		CITY <form:input type="text" class="inputArea" placeholder="City" path="city" name ="city" id="city" required="required" onchange = "validateCity()" />
		STATE <form:input type="text" class="inputArea" placeholder="State" path="state" name ="state" id="state" required="required" onchange="validateState()" />
		ZIPCODE <form:input type="number" class="inputArea" placeholder="Zipcode" path="zipCode" name ="zip" id="zip" required="required" onchange="validateZipCode()" />
		<div>
			<input type="submit" value="Create User" id="submitButton"/>
		</div> 
	</div>
	</form:form>
	</div>
	
	<script>
	function validateCity(){
		var city = document.getElementById("city").value;
		var txt = x.replace(/[^A-Za-z\s]/g,"");
	    document.getElementById("city").value = txt;
	}
	
	
		function validateState(){
			var state = document.getElementById("state").value;
			var txt = x.replace(/[^A-Za-z\s]/g,"");
		    document.getElementById("state").value = txt;
		}
	
		function validateFirstName(){
			var fname = document.getElementById("firstname").value;
			var txt = x.replace(/[^A-Za-z]/g,"");
		    document.getElementById("firstname").value = txt;
		}
		
		function validateLastName(){
			var lname = document.getElementById("lastname").value;
			var txt = x.replace(/[^A-Za-z]/g,"");
		    document.getElementById("lastname").value = txt;
		}
		
		function validateEmployeeID(){
			var empid = document.getElementById("empid").value;
			var txt = x.replace(/[^0-9]/g,"");
		    document.getElementById("empid").value = txt;
		}
		
		function validateMngrID(){
			var mngrid = document.getElementById("mngrid").value;
			var txt = x.replace(/[^0-9]/g,"");
		    document.getElementById("mngrid").value = txt;
		}
		
		function checkPhnNo(){
			var x = document.getElementById("phnno").value;
			var txt = x.replace(/[^0-9]/g,"");
		    document.getElementById("phnno").value = txt;
		}
		
		function validateZipCode(){
			var zip = document.getElementById("zip").value;
			var txt = x.replace(/[^0-9]/g,"");
		    document.getElementById("zip").value = txt;
		}
		
		
		function validateEmail(){
			var emailid = document.getElementById("emailid").value;
			var txt = x.replace(/[^A-Za-z0-9;@;.]/g,"");
		    document.getElementById("emailid").value = txt;
		}
			
		function validatePassword(){
			var password = document.getElementById("password").value;
			var txt = x.replace(/[^A-Za-z0-9\s]/g,"");
		    document.getElementById("password").value = txt;
		}
		
		function validateDesignation(){
			var desig = document.getElementById("desig").value;
			var txt = x.replace(/[^A-Za-z]/g,"");
		    document.getElementById("desig").value = txt;
		}
			
	</script>
</body>
</html>