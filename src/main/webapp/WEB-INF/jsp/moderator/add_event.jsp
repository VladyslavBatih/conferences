<html>
<head>
    <meta charset="UTF-8"/>
    <title>Add event * Conferences</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div align="center">
        <h1>Conferences</h2>
        <br/>
        <h3>Add event</h1>
    </div>

    <div class="mainContainer" align="center">
        <div class="formContainer">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="addEvent"/>
                <table>
                    <tr>
                        <td></td>
                        <td><input placeholder="Event name" name="name" value="${requestScope.name}"/></td>
                        <td><span class="error"><c:out value="${requestScope.errors.name}"/></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input placeholder="Place" name="place" value="${requestScope.place}"/></td>
                        <td><span class="error"> <c:out value="${requestScope.errors.place}"/></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input placeholder="Date yyyy.mm.dd" name="date" value="${requestScope.date}"/></td>
                        <td><span class="error"> <c:out value="${requestScope.errors.date}"/></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input placeholder="Time hh.mm" name="time" value="${requestScope.time}"/></td>
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