<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title>Sign up * Conferences</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div align="center">
    <h1>Conferences</h2>
    <br/>
    <h3>Registration</h1>
    </div>

<div class="mainContainer" align="center">
    <div class="formContainer">
    <form action="controller" method="post">
        <input type="hidden" name="check" value="${random.nextInt()}">
        <input type="hidden" name="command" value="registration"/>
        <table>
            <tr>
                <td></td>
                <td><input placeholder="Your login" name="login" value="${requestScope.login}"/></td>
                <td><span class="error"><c:out value="${requestScope.errors.login}"/></span></td>
            </tr>
            <tr>
                <td></td>
                <td><input placeholder="Your password" type="password" name="password"/></td>
                <td><span class="error"><c:out value="${requestScope.errors.password}"/></span></td>
            </tr>
            <tr>
                <td></td>
                <td><input placeholder="Confirm your password" type="password" name="confirm"/></td>
                <td><span class="error"><c:out value="${requestScope.errors.confirm}"/></span></td>
            </tr>
            <tr>
                <td></td>
                <td><input placeholder="Your first name" name="firstname" value="${requestScope.firstname}"/></td>
                <td><span class="error"> <c:out value="${requestScope.errors.firstName}"/></span></td>
            </tr>
            <tr>
                <td></td>
                <td><input placeholder="Your last name" name="lastname" value="${requestScope.lastname}"/></td>
                <td><span class="error"> <c:out value="${requestScope.errors.lastName}"/></span></td>
            </tr>
            <tr>
                <td></td>
                <td><select name="role" style="width: 100%">
                      <option value="speaker">Speaker</option>
                      <option value="user">User</option>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" style="width: 100%" value="Submit"/></td>
            </tr>
            <tr>
                <td></td>
                <td><a href="controller?command=loginView">Back to login</a></td>
            </tr>
        </table>
    </form>
    </div>
</div>
</body>
</html>