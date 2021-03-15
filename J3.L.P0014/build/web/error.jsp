<%-- 
    Document   : error
    Created on : Feb 17, 2021, 10:47:03 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1><font color="red">${requestScope.ERROR}</font>
        </h1>
        <a href="login.jsp">Click here to try again!</a>
    </body>
</html>
