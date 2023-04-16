<%
    int points = (int) request.getAttribute("points");
    int secreteNumber = (int) request.getAttribute("secreteNumber");
    int roundNumber = (int) request.getAttribute("roundNumber");
%>

<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/Styling.css" rel="stylesheet" type="text/css">
    <title>Secrete Number</title>
</head>
    <body>
        <h1>Congratulations!</h1>
        <h2>Round: <%= roundNumber + 1%></h2>
        <h2>Secrete Number: <%= secreteNumber %></h2>
        <h2>Points: <%= points %></h2>

        <form action="/SENG2050-A2/Home" method="GET">
            <input type='submit' value='Home' name='sumbit'/>
        </form>
    </body>
</html>