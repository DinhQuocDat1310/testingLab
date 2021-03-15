<%-- 
    Document   : displayquiz
    Created on : Feb 21, 2021, 12:53:15 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Page</title>
    </head>
    <body>
        <c:set var="SLIST" value="${sessionScope.SLIST}"/>
        <div>
            <h1>Hello,<font color="green">${sessionScope.NAME}</font></h1>
            <form action="LogOut"><input type="submit" value="Logout"/></form>
        </div>
        <c:forEach var="slist" items="${SLIST}" varStatus="counter">
            <div>
                <form action="TakeAQuiz">
                    ${counter.count}.${slist.subjectID}
                    <input type="hidden" name="txtSubjectID" value="${slist.subjectID}" />
                    <select name="cbbTime">
                        <option value="">~~~~~~~CHOOSE ONE~~~~~~~</option>
                        <option value="60:00">60 Minutes - 60 Questions</option>
                        <option value="45:00">45 Minutes - 45 Questions</option>
                        <option value="30:00">30 Minutes - 30 Questions</option>
                        <option value="15:00">15 Minutes - 15 Questions</option>
                        <option value="10:00">10 Minutes - 10 Questions</option>
                        <option value="05:00">5 Minutes - 5 Questions</option>
                    </select>
                    <input type="submit" value="Take a Quiz" />
                </form>
            </div><br/>
        </c:forEach>
    </body>
</html>
