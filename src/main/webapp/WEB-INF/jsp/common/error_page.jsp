<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error * Conferences</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<center>ERROR</center>
<center><c:out value="${requestScope.errorMessage}"/></center>
</body>
</html>