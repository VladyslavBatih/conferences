<%@ page import="db.entity.dto.ReportDTO"%>
<%@ page import="db.entity.dto.EventDTO"%>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Update report * Conferences</title>
</head>

<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div align="center">
        <h1>Conferences</h2>
        <br/>
        <h3>Update Report</h1>
    </div>

    <div class="mainContainer" align="center">
        <div class="formContainer">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="updateReport"/>
                <table>
                    <% ReportDTO reportDTO = (ReportDTO) request.getAttribute("reportDTO"); %>
                    <% EventDTO eventDTO = (EventDTO) request.getAttribute("eventDTO"); %>
                    <tr>
                        <td></td>
                        <td>
                            <input placeholder="Report topic" name="reportTopic" value="${reportDTO.topic}"/>
                            <input type="hidden" name="reportId" value="${reportDTO.id}"/>
                            <input type="hidden" name="eventId" value="${eventDTO.id}"/>
                        </td>
                        <td><span class="error"><c:out value="${requestScope.errors.name}"/></span></td>
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