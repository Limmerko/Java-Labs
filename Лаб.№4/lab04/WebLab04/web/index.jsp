<%-- 
    Document   : index
    Created on : 23.05.2020, 14:35:20
    Author     : Limmerko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action = "transactionControllers" method="post">
            <input type="hidden" name="action" value="person">
            <input type="submit" value="Trans to Person">
        </form><br>
        <form action = "TransController" method="post">
            <input type="hidden" name="action" value="company">
            <input type="submit" value="Trans to Company">
        </form>
    </body>
</html>
