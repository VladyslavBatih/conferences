<%String header = "Current date:";%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>First JSP App</title>
</head>
<body>
    <h1><%=header%></h1>
    <p><%=new java.util.Date()%></p>

    <div>
    <h2>My first web application!</h2>
    </div>

    <div>
        <div>
            <button onclick="location.href='/list'">List users</button>
            <button onclick="location.href='/add'">Add user</button>
        </div>
    </div>
</body>
</html>