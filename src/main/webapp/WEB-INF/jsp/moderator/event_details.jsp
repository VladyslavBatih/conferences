<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.List"%>
<%@ page import="db.entity.dto.EventDTO"%>
<%@ page import="db.entity.dto.ReportDTO"%>
<%@ page import="db.entity.dto.UserDTO"%>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Event details * Conferences</title>
</head>
<body>
    <div align="center">
    <h1>Conferences</h2>
    <br/>
    <h3>All reports</h1>
    </div>


<%
    List<ReportDTO> reportDTOList = (List) request.getAttribute("reportDTOList");
    EventDTO eventDTO = (EventDTO) request.getAttribute("eventDTO");
%>

<div align="center">
    <table border="1">
        <thead>
            <tr>
                <th>Topic</th>
                <th>Speaker</th>
            </tr>
        </thead>


        <c:forEach var="reportDTO" items="${reportDTOList}">
        <tr>

            <td>${reportDTO.topic}</td>
            <td>${reportDTO.speakerFirstName} ${reportDTO.speakerLastName}</td>
        </tr>
        </c:forEach>

    </table>
        <table>
                <tr>
                    <form>
                            <input type="hidden" name="command" value="addReportView"/>
                            <input type="hidden" name="eventId" value=${eventDTO.id}>
                            <td></td>
                            <td><input type="submit" value="Add report"</td>
                    </form>
                </tr>
        </table>
        <table>
                <tr>
                    <td></td>
                    <td><a href="controller?command=moderatorPanel">Back to events</a></td>
                </tr>
        </table>
</div>
</body>
</html>