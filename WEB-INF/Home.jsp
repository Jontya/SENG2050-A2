<%
    String error = (String) request.getAttribute("errorString");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <link href="${pageContext.request.contextPath}/Styling.css" rel="stylesheet" type="text/css">
    <title>Secrete Number</title>
</head>
    <body>
        <div class="main-container">
            <h1 class="main-title">Guess The Secrete Number</h1>
            <form action="/SENG2050-A2/Home" method="POST">
                <input type='submit' value='New Game' name='new' id='new-game'> <br>
                <input type='text' name='username' id="username" placeholder="Username">
                <input type='submit' value='Load Game' name='load' id='load-game'>
            </form>

            <% if(error != null){
                %><p class="error"><%= error %></p><%
            }%>
    </div>
    </body>
</html>