<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Conferences</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div align="center">
    <h1>Conferences</h2>

    </div>

<div class="container" align="center">
    <form class="auth" action="controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <table>
            <tr>
                <td><input placeholder="Your login" type="text" name="login" value="${requestScope.login}"></td>
                <td><span class="error"><c:out value="${requestScope.errors.login}"/></span></td>
            </tr>
            <tr>
                <td><input placeholder="Your password" type="password" name="password"></td>
                <td><span class="error"><c:out value="${requestScope.errors.password}"/></span></td>
            </tr>
            <tr>
                <td><input type="submit" style="width: 88%" value="Sign in"></td>
            </tr>
            <tr>
                <td>
                <label>Don&apos;t have an account</label>
                <a href="controller?command=registrationView">Sign up</a>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>