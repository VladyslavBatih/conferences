<%@ page import="db.entity.dto.EventDTO"%>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Update event * Conferences</title>
</head>

<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div align="center">
        <h1>Conferences</h2>
        <br/>
        <h3>Update Event</h1>
    </div>

    <div class="mainContainer" align="center">
        <div class="formContainer">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="updateEvent"/>
                <table>
                    <% EventDTO eventDTO = (EventDTO) request.getAttribute("eventDTO"); %>
                    <input type="hidden" name="eventId" value="${eventDTO.id}"
                    <tr>
                        <td></td>
                        <td><input placeholder="Event name" name="name" value="${eventDTO.name}" size=30/></td>
                        <td><span class="error"><c:out value="${requestScope.errors.name}"/></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input placeholder="Place" name="place" value="${eventDTO.place}" size=30/></td>
                        <td><span class="error"> <c:out value="${requestScope.errors.place}"/></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input placeholder="Date yyyy.mm.dd" name="date" value="${eventDTO.date}" size=30/></td>
                        <td><span class="error"> <c:out value="${requestScope.errors.date}"/></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input placeholder="Time hh.mm" name="time" value="${eventDTO.time.substring(0, 5)}" size=30/></td>
                        <td><span class="error"> <c:out value="${requestScope.errors.time}"/></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" style="width: 100%" value="Submit"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><a href="controller?command=moderatorPanel">Back to events</a></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>