<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.List"%>
<%@ page import="db.entity.dto.EventDTO"%>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>User Panel * Conferences</title>
</head>
<body>
    <div align="center">
        <h1>Conferences</h2>
        <br/>
        <h3>All events</h1>
    </div>

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

                <% List<EventDTO> eventDTOList = (List) request.getAttribute("eventDTOList"); %>

                <c:forEach var="eventDTO" items="${eventDTOList}">
                    <tr>
                        <c:url var="detailsButton" value="">
                            <c:param name="eventId" value="${eventDTO.id}"/>
                            <c:param name="command" value="eventDetails"/>
                        </c:url>
                        <td>${eventDTO.name}</td>
                        <td>${eventDTO.place}</td>
                        <td>${eventDTO.date}</td>
                        <td>${eventDTO.time}</td>
                        <td>
                            <input type="button" value="Details"
                                onclick="window.location.href='${detailsButton}'"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
</body>
</html>