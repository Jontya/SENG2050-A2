<%
    int points = (int) request.getAttribute("points");
    int secreteNumber = (int) request.getAttribute("secreteNumber");
    int roundNumber = (int) request.getAttribute("roundNumber");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <link href="${pageContext.request.contextPath}/Styling.css" rel="stylesheet" type="text/css">
    <title>Secrete Number</title>
</head>
    <body>
        <div class="main-container">
            <h1 class="main-title">Congratulations!</h1>
            <h2 class="end-title">Round: <%= roundNumber + 1%></h2>
            <h2 class="end-title">Secrete Number: <%= secreteNumber %></h2>
            <h2 class="end-title">Points: <%= points %></h2>

            <form action="/SENG2050-A2/Home" method="GET">
                <input type="submit" value="Home" name="submit" id="end-home">
            </form>
        </div>
    </body>
</html>