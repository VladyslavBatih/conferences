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
                    <th>Link</th>
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
                <td></td>
                <td><a href="controller?command=userPanel">Back to events</a></td>
            </tr>
        </table>
    </div>
</body>
</html>