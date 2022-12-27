<%@ page import="db.entity.dto.EventDTO"%>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Add report * Conferences</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div align="center">
        <h1>Conferences</h2>
        <br/>
        <h3>Add report</h1>
    </div>
    <% EventDTO eventDTO = (EventDTO) request.getAttribute("eventDTO"); %>

    <div class="mainContainer" align="center">
        <div class="formContainer">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="addReport"/>
                <table>
                    <tr>
                        <td><input type="hidden" name="eventId" value=${eventDTO.id}></td>
                        <td><input placeholder="Report topic" name="topic" value="${requestScope.topic}"/></td>
                        <td><span class="error"><c:out value="${requestScope.errors.topic}"/></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" style="width: 100%" value="Submit"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><a href="controller?command=eventDetails&eventId=${eventDTO.id}">Back to reports</a></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>