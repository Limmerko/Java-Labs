<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Count-add-news</title>
    </head>
    <body>
        <label><h2>Count add News = ${countAdd}</h2></label>
        </br>
        <label><h2>Title add News = </h2></label>
        <c:forEach items="${titleAdd}" var="title">
            <h2>${title}, </h2>
        </c:forEach>
    </body>
</html>
