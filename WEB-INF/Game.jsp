<%
    int secreteNumber = (int) request.getAttribute("secreteNumber");
    int roundNumber = (int) request.getAttribute("roundNumber") + 1;
    String prevGuesses = (String) request.getAttribute("prevGuesses");
    String highLow = (String) request.getAttribute("highLow");
    String errors = (String) request.getAttribute("errorString");
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
            <h2 class="sec-title">Round: <%= roundNumber%></h2>
            <h2 class="sec-title">Previous Guesses</h2>
            <p class="game-para"><%= prevGuesses %></p>

            <% if(!highLow.equals("")){
                %>
                <h2 class="sec-title">Higher Or Lower</h2>
                <p class="game-para"><%= highLow %></p>
                <%
            }%>

            <form action="/SENG2050-A2/Game" method="POST">
                <input type='text' name='guess' placeholder="Guess" id="game-guess">
                <input type='submit' value='Submit' name='submit' id="game-submit">

                <input type='text' name='username' placeholder="Username" id="game-username">
                <input type='submit' value='Save' name='save' id="game-save">
            </form>

            <% if(errors != null){
                %><p class="error"><%= errors %></p><%
            }%>
        </div>
    </body>
</html>