<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.List"%>
<%@ page import="db.entity.dto.EventDTO"%>
<%@ page import="db.entity.dto.ReportDTO"%>
<%@ page import="db.entity.dto.UserDTO"%>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Moderator Panel * Conferences</title>
</head>
<body>
    <div align="center">
    <h1>Conferences</h2>
    <br/>
    <h3>All events</h1>
    </div>


<%
    List<EventDTO> eventDTOList = (List) request.getAttribute("eventDTOList");
    List<ReportDTO> reportDTOList = (List) request.getAttribute("reportDTOList");
    List<UserDTO> userDTOList = (List) request.getAttribute("userDTOList");
%>
    <br/>
<div align="center">
<form action="controller" method="get">

    <table border="1">
        <thead>
            <tr>
                <th>Name</th>
                <th>Place</th>
                <th>Date</th>
                <th>Time</th>
                <th>Link</th>
            </tr>
        </thead>


        <c:forEach var="eventDTO" items="${eventDTOList}">
        <tr>
        <form>
            <input type="hidden" name="command" value="eventDetails"/>
            <input type="hidden" name="eventId" value=${eventDTO.id}>
            <td>${eventDTO.name}</td>
            <td>${eventDTO.place}</td>
            <td>${eventDTO.date}</td>
            <td>${eventDTO.time}</td>
            <td><input type="submit" value="Details"</td>
        </form>
        </tr>
        </c:forEach>
    </table>
    <table>
         <tr>
            <td><a href="controller?command=addEventView">Add new event</a></td>
         </tr>
    </table>
</div>
</body>
</html>