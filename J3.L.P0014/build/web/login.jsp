<%-- 
    Document   : login
    Created on : Feb 18, 2021, 3:54:42 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="Login" method="POST">
            Email: <input type="text" name="txtEmail"/><br/>
            <c:if test="${not empty EMAILERROR}"><h1><font color="red">${requestScope.EMAILERROR}</font></h1><br/></c:if>
                Password: <input type="password" name="txtPassword"/><br/>
            <c:if test="${not empty PASSWORDERROR}"><h1><font color="red">${requestScope.PASSWORDERROR}</font></h1><br/></c:if>
            <input type="submit" value="Login" />
        </form>
        <form action="RegistrationPage">
            <input type="submit" value="Registration" />
        </form>
    </body>
</html>
