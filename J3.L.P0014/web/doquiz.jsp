<%-- 
    Document   : doquiz
    Created on : Feb 21, 2021, 6:02:38 PM
    Author     : Welcome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Do Quiz Page</title>
    </head>
    <body>
        <h1>Hello,<font color="green">${sessionScope.NAME}</font></h1>
        <h1>Quiz List</h1>
        <c:set var="QUIZLIST" value="${sessionScope.QUIZLIST}"/>
        <c:set var="MAP" value="${sessionScope.MAP}"/>
        <c:set var="QUIZPOS" value="${sessionScope.QUIZPOS}"/>
        <c:set var="QUIZTIME" value="${sessionScope.QUIZTIME}"/>
        <!-- Display the countdown timer in an element -->
        <div id="QuizTime"></div>
        <script>
            // Set the date we're counting down to
            var countDownDate = new Date('<c:out value="${QUIZTIME}"/>').getTime();
            // Update the count down every 1 second
            var x = setInterval(function () {
                // Get today's date and time
                var now = new Date().getTime();
                // Find the distance between now and the count down date
                var distance = countDownDate - now;
                // Time calculations for days, hours, minutes and seconds
//                var days = Math.floor(distance / (1000 * 60 * 60 * 24));
//                var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                var seconds = Math.floor((distance % (1000 * 60)) / 1000);
                // Display the result in the element with id="QuizTime"
                document.getElementById("QuizTime").innerHTML = minutes + "m " + seconds + "s ";
                // If the count down is finished, write some text
                if (distance < 0) {
                    clearInterval(x);
                    document.getElementById("QuizTime").innerHTML = "EXPIRED";
                }
            }, 1000);
        </script>
        <c:forEach var="list" items="${QUIZLIST}" >
            <div>
                Question ${QUIZPOS}: ${list.questionContent}
            </div>
            <div>
                Answer:<br/>
                <c:forEach var="map" items="${MAP}">
                    <c:if test="${map.key == list.questionID}">
                        <c:forEach var="value" items="${map.value}" varStatus="counter"> 
                            <c:if test="${value.status == true}">
                                <input type="radio" name="rbtQuestion${QUIZPOS}" value="true" />${counter.count}. ${value.answerContent}<br/>
                            </c:if>
                            <c:if test="${value.status != true}">
                                <input type="radio" name="rbtQuestion${QUIZPOS}" value="false" />${counter.count}. ${value.answerContent}<br/>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:forEach>
                <form action="ChangeQuestionInQuiz">
                    <input type="submit" value="Select" />
                    <input type="hidden" name="txtQuestionPos" value="${QUIZPOS + 1}" />
                    <input type="hidden" name="txtMinute" value="${MINUTE}" />
                    <input type="hidden" name="txtSecond" value="${SECOND}" />
                </form>
            </div>
            <form action="ChangeQuestionInQuiz">
                <input type="submit" value="Next Question ~~>" />
                <input type="hidden" name="txtQuestionPos" value="${QUIZPOS + 1}" />
                <input type="hidden" name="txtMinute" value="${MINUTE}" />
                <input type="hidden" name="txtSecond" value="${SECOND}" />
            </form>
            <form action="ChangeQuestionInQuiz">
                <input type="submit" value="<~~ Previous Question" />
                <input type="hidden" name="txtQuestionPos" value="${QUIZPOS - 1}" />
                <input type="hidden" name="txtMinute" value="${MINUTE}" />
                <input type="hidden" name="txtSecond" value="${SECOND}" />
            </form>
        </c:forEach>
    </body>
</html>
