<%-- 
    Document   : create
    Created on : Feb 19, 2021, 6:43:28 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <h1>Create Page</h1>
        <div>
            <h1>Hello,<font color="green">${sessionScope.NAME}</font></h1>
            <form action="LogOut"><input type="submit" value="Logout"/></form>
        </div>
        <h1>Input the information for the new Question</h1>
        <c:set var="SLIST" value="${sessionScope.SLIST}"/>
        <form action="CreateQuestion">
            <table border="1">
                <tbody>
                    <tr>
                        <td>
                            <div>
                                Content:<br/>
                                <textarea rows="9" cols="70" name="txtQuestionContent"></textarea><br/>
                            </div>
                        </td>
                        <td>
                            Subject: <select name="cboSubject">
                                <option value="">---Choose---</option>
                                <c:forEach var="listsubject" items="${SLIST}">
                                    <option value="${listsubject.subjectID}">${listsubject.subjectID}</option>    
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>
                                Answer 1:<br/>
                                <textarea rows="2" cols="70" name="txtAnswerContent"></textarea><br/>
                                Is it true? <input type="radio" name=rbtStatus1" value="true"/>YES
                                <input type="radio" name=rbtStatus1" value="false" checked="true"/>NO
                                
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>
                                Answer 2:<br/>
                                <textarea rows="2" cols="70" name="txtAnswerContent"></textarea><br/>
                                Is it true? <input type="radio" name=rbtStatus2" value="true"/>YES
                                <input type="radio" name=rbtStatus2" value="false" checked="true"/>NO
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>
                                Answer 3:<br/>
                                <textarea rows="2" cols="70" name="txtAnswerContent"></textarea><br/>
                                Is it true? <input type="radio" name=rbtStatus3" value="true"/>YES
                                <input type="radio" name=rbtStatus3" value="false" checked="true"/>NO
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>
                                Answer 4 :<br/>
                                <textarea rows="2" cols="70" name="txtAnswerContent"></textarea><br/>
                                Is it true? <input type="radio" name=rbtStatus4" value="true"/>YES
                                <input type="radio" name=rbtStatus4" value="false" checked="true"/>NO
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Create" />
        </form>
        <form action="CancelQuestionAction">
            <input type="submit" value="Cancel" />
        </form>
    </body>
</html>
