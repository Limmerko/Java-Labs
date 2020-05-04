<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Просмотр новости</h1>
<table>
    <tr>
        <td><label>Заголовок:</label></td>
        <td><c:out value="${news.title}" /></td>
    </tr>
    <tr>
        <td><label>Содержание:</label></td>
        <td><c:out value="${news.text}" /></td>
    </tr>
    <tfoot>
    <th colspan="2" style="padding: 10px 0px;">
        <c:url value="/news/${news.id}" var="newsDeleteUrl" />
        <form id="deleteForm"
              action="${newsDeleteUrl}"
              method="GET">
            <input type="hidden" name="delete" />
            <input type="submit" value="Удалить" />
            <c:url value="/news" var="newsListUrl" />
            <span><a href="${newsListUrl}">Отмена</a></span>
        </form>
    </th>
    </tfoot>
</table>