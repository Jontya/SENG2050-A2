<%
    int secreteNumber = (int) request.getAttribute("secreteNumber");
    int roundNumber = (int) request.getAttribute("roundNumber");
    String[] prevGuesses = (String[]) request.getAttribute("prevGuesses");
%>

<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/Styling.css" rel="stylesheet" type="text/css">
    <title>Secrete Number</title>
</head>
    <body>
        <h1>Guess The Secrete Number</h1>
        <h2>Previous Guesses</h2>
        <h2>Round: <%= roundNumber + 1%></h2>
        <%
            if(prevGuesses[0] != null){
                for(int i = 0; i < prevGuesses.length; i++){
                    if(prevGuesses[i] == null){
                        break;
                    }
                    else{
                        %><%= prevGuesses[i] %>, <%
                    }
                }
            }
            else{
                %><p>No Previous Guesses</p><%
            }
        %>
        <p><%= secreteNumber %></p>

        <form action="/SENG2050-A2/Game" method="POST">
            <input type='text' name='guess'>
            <input type='submit' value='Submit' name='sumbit'/>
            <input type='submit' value='Save' name='save'/>
        </form>
    </body>
</html>