<%
    int secreteNumber = (int) request.getAttribute("secreteNumber");
    int roundNumber = (int) request.getAttribute("roundNumber") + 1;
    String prevGuesses = (String) request.getAttribute("prevGuesses");
    String highLow = (String) request.getAttribute("highLow");
    String errors = (String) request.getAttribute("errorString");
%>

<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/Styling.css" rel="stylesheet" type="text/css">
    <title>Secrete Number</title>
</head>
    <body>
        <div class="main-container">
            <h1>Guess The Secrete Number</h1>
            <h2>Round: <%= roundNumber%></h2>
            <h2>Previous Guesses</h2>
            <p><%= prevGuesses %></p>

            <% if(!highLow.equals("")){
                %>
                <h2>Higher Or Lower</h2>
                <p><%= highLow %></p>
                <%
            }%>
        
            <p><%= secreteNumber %></p>

            <form action="/SENG2050-A2/Game" method="POST">
                <input type='text' name='guess'>
                <input type='submit' value='Submit' name='sumbit'/>

                <input type='text' name='username'>
                <input type='submit' value='Save' name='save'/>
            </form>

            <% if(errors != null){
                %><p><%= errors %></p><%
            }%>
        </div>
    </body>
</html>