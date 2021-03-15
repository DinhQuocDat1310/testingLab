<%-- 
    Document   : update
    Created on : Feb 20, 2021, 9:37:57 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <c:set var="QLIST" value="${sessionScope.QLIST}"/>
        <c:set var="MAP" value="${sessionScope.MAP}"/>
        <c:set var="updatequestionID" value="${param.txtQuestionID}"/>
        <div>
            <h1>Hello,<font color="green">${sessionScope.NAME}</font></h1>
            <form action="LogOut"><input type="submit" value="Logout"/></form>
        </div>
        <form action="UpdateQuestion">
            <input type="hidden" name="txtQuestionID" value="${updatequestionID}" />
            <c:forEach var="list" items="${QLIST}">
                <c:if test="${list.questionID == updatequestionID}">
                    <div>
                        <textarea rows="9" cols="70" name="txtQuestionContent">${list.questionContent}</textarea><br/>
                        <c:forEach var="map" items="${MAP}">
                            <c:if test="${map.key == list.questionID}">
                                <c:forEach var="value" items="${map.value}" varStatus="counter">
                                    <c:if test="${value.status == true}">
                                        <div>
                                            Answer ${counter.count}:<br/>
                                            <input type="hidden" name="txtAnswerID" value="${value.answerID}" />
                                            <textarea rows="2" cols="70" name="txtAnswerContent">${value.answerContent}</textarea><br/>
                                            Is it true? <input type="radio" name="chkStatus${counter.count}" value="true" checked="true"/>YES
                                            <input type="radio" name="chkStatus${counter.count}" value="false" />NO
                                        </div><br/>
                                    </c:if>
                                    <c:if test="${value.status != true}">
                                        <div>
                                            Answer ${counter.count}:<br/>
                                            <input type="hidden" name="txtAnswerID" value="${value.answerID}" />
                                            <textarea rows="2" cols="70" name="txtAnswerContent">${value.answerContent}</textarea><br/>
                                            Is it true? <input type="radio" name="chkStatus${counter.count}" value="true"/>YES
                                            <input type="radio" name="chkStatus${counter.count}" value="false" checked="true"/>NO
                                        </div><br/>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:if>
            </c:forEach>
            <input type="submit" value="Update" />
        </form>
        <form action="CancelQuestionAction">
            <input type="submit" value="Cancel" />
        </form>
    </body>
</html>
