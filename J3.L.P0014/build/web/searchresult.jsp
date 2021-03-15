<%-- 
    Document   : searchresult
    Created on : Feb 19, 2021, 2:03:16 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
    </head>
    <body>
        <h1>Result Page</h1>
        <c:set var="SLIST" value="${sessionScope.SLIST}"/>
        <c:set var="QLIST" value="${sessionScope.QLIST}"/>
        <c:set var="MAP" value="${sessionScope.MAP}"/>
        <c:set var="MAXPAGENO" value="${requestScope.MAXPAGENO}"/>
        <div>
            <form action="SearchQuestion">
                Name: <input type="text" name="txtQuestionName" value="" /><br/>
                Status: <select name="cboStatus">
                    <option value="TRUE">Activated</option>
                    <option value="FALSE">Deactivated</option>
                </select>
                Subject: <select name="cboSubject">
                    <option value="">---Choose---</option>
                    <c:forEach var="listsubject" items="${SLIST}">
                        <option value="${listsubject.subjectID}">${listsubject.subjectID}</option>    
                    </c:forEach>
                </select>
                <input type="submit" value="Search" />
            </form>
        </div>
        <div>
            <h1>Hello,<font color="green">${sessionScope.NAME}</font></h1>
            <form action="LogOut"><input type="submit" value="Logout"/></form>
        </div>
        <form action="CreatePage">
            <input type="submit" value="Add A Question" />
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Subject ID</th>
                    <th>Question</th>
                    <th>Answer</th>
                </tr>
            </thead>
            <tbody>

                <c:forEach var="list" items="${QLIST}" varStatus="counter">

                    <tr>
                        <td>${counter.count}</td>
                        <td>${list.subjectID}</td>
                        <td>${list.questionContent}</td>
                        <td>
                            <c:forEach var="map" items="${MAP}">
                                <c:if test="${map.key == list.questionID}">
                                    <c:forEach var="value" items="${map.value}">
                                        <c:if test="${value.status == true}">
                                            <font color="red">
                                            ${value.answerContent}<br/>
                                            </font>
                                        </c:if>
                                        <c:if test="${value.status != true}">
                                            ${value.answerContent}<br/>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <form action="DeleteQuestion">
                                <input type="submit" value="Delete" />
                                <input type="hidden" name="txtQuestionID" value="${list.questionID}" />
                            </form>
                        </td>
                        <td>
                            EDIT LINK
                        </td>
                    </tr>

                </c:forEach>
            </tbody>
        </table>
        <c:forEach var="maxpageno" begin="1" end="${MAXPAGENO}">
            <a href="SearchQuestionServlet?pageNo=${maxpageno}&txtQuestionName=${param.txtQuestionName}&cboStatus=${param.cboStatus}&cboSubject=${param.cboSubject}">${maxpageno}</a>
        </c:forEach>
    </body>
</html>
