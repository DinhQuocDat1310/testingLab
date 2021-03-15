<%-- 
    Document   : main
    Created on : Feb 17, 2021, 10:31:57 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
    </head>
    <body>
        <h1>Main Page</h1>
        <c:if test="${sessionScope.ROLE.equals('Admin')}">
            <h1>Hello,<font color="green">${sessionScope.NAME}</font></h1>
            <form action="LogOut"><input type="submit" value="Logout"/></form>
            <form action="DisplayQuestion">
                <input type="submit" value="Display Question"/>
            </form>
        </c:if>
        <c:if test="${sessionScope.ROLE.equals('Student')}">
            <h1>Hello,<font color="green">${sessionScope.NAME}</font></h1>
            <form action="LogOut"><input type="submit" value="Logout" /></form>
            <form action="DisplayQuizList"><input type="submit" value="Display Quiz List" /></form>
        </c:if>
        <c:if test="${empty sessionScope.ROLE}">
            <h1>~~~~!ERROR!~~~~</h1>
        </c:if>
    </body>
</html>
