<%String header = "Current date:";%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Conferences</title>
</head>
<body>
    <h1><%=header%></h1>
    <p><%=new java.util.Date()%></p>

    <div>
    <h2>Please, log in</h2>
    </div>

<div class="container">
    <form class="auth" action="controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <table>
            <tr>
                <td><input placeholder="Your login" type="text" name="login" value="${requestScope.login}"></td>
            </tr>
            <tr>
                <td><span class="error"><c:out value="${requestScope.errors.login}"/></span></td>
            </tr>
            <tr>
                <td><input placeholder="Your password" type="password" name="password"></td>
            </tr>
            <tr>
                <td><span class="error"><c:out value="${requestScope.errors.password}"/></span></td>
            </tr>
            <tr>
                <td><input type="submit" style="width: 100%" value="Submit"></td>
            </tr>
            <tr>
                <td><a href="controller?command=registrationView">Registration</a></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>