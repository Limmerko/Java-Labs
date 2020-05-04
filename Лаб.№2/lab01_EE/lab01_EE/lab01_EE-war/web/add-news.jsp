<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add-news</title>
    </head>
    <body>
        <form action="add-news" method="post">
            <input type="hidden" name="id" value="${news.id}">
            <table>
                <tr> 
                    <td>Заголовок</td>
                    <td><input required type="text" name="title" value="${news.title}"></td>
                </tr>
                <tr> 
                    <td>Описание</td>
                    <td><input required type="text" name="text" value="${news.text}"></td>
                </tr>
                <tr> 
                    <td colspan="2"><input type="submit" value="Создать"></td>
                </tr>
            </table>
        </form>

    </body>
</html>
