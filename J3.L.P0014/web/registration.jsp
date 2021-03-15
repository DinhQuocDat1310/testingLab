<%-- 
    Document   : registration
    Created on : Feb 17, 2021, 10:07:50 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    <s:head/>
</head>
<body>
    <h1>Register Form</h1>
    <form action="SignUp">
        Email: <input type="text" name="txtEmail"/><br/>
        <c:if test="${not empty EMAILERROR}"><h1><font color="red">${requestScope.EMAILERROR}</font></h1><br/></c:if>
            Name: <input type="text" name="txtName"/><br/>
        <c:if test="${not empty NAMEERROR}"><h1><font color="red">${requestScope.NAMEERROR}</font></h1><br/></c:if>
            Password: <input type="text" name="txtPassword"/><br/>
        <c:if test="${not empty PASSWORDERROR}"><h1><font color="red">${requestScope.PASSWORDERROR}</font></h1><br/></c:if>
            Confirm Password: <input type="text" name="txtConfirmPassword"/><br/>
        <c:if test="${not empty COMFIRMPASSWORDERROR}"><h1><font color="red">${requestScope.COMFIRMPASSWORDERROR}</font></h1><br/></c:if>
        <input type="submit" value="Register" />
    </form>
    <form action="CancelSignUp">
        <input type="submit" value="Cancel" />
    </form>
</body>
</html>
