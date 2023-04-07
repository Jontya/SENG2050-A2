<!DOCTYPE html>
<html>
<head>
    <title>SENG2050-A2</title>
</head>
    <body>
    <jsp:useBean id="javaBean" scope="application" class="beans.JavaBean"/>
    <jsp:setProperty name="javaBean" property="test" value="Hello World"/>
    <h1><jsp:getProperty name="javaBean" property="test"/></h1>

    </body>
</html>