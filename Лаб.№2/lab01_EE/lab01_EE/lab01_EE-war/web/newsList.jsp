<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>news</title>
    </head>
    <body>
        <h1>Список пользователей</h1>
        <a href="add-news">Создать</a>
        <table>
            <tr>
                <th>id</th>
                <th>Заголовок</th>
                <th>Описание</th>
                <th></th>
            </tr>
            <c:forEach items="${news}" var="news">
            <tr>
                <td>${news.id}</td>
                <td>${news.title}</td>
                <td>${news.text}</td>
                <td><a href="edit-news">Изменить</a></td>
                <td>
                    <form action="delete-news" method="post">
                        <input type="hidden" name="id" value="${news.id}">
                        <input type="submit" value="удалить">
                    </form></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
