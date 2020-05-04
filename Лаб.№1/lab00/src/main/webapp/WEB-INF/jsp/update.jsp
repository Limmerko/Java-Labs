<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Добавление новости</h1>
<c:url value="/news/update/${news.id}" var="createNewsUrl" />
<form id="addTaskForm" action="${createNewsUrl}" method="POST">
    <table>
        <tr>
            <td><label>Заголовок:</label></td>
            <td><input name="title" type="text"
                  value="<c:out value="${news.title}"/>"/>
            </td>
        </tr>
        <tr>
            <td><label>Содержание:</label></td>
            <td><input name="text" type="text"
                    value="<c:out value="${news.text}"/>"/>
            </td>
        </tr>
        <tfoot>
        <th colspan="2" style="padding: 10px 0px;">
            <c:url value="/news" var="newsListUrl" />
            <input type="submit" value="Изменить" />
            <span><a href="${newsListUrl}">Отмена</a></span>
        </th>
        </tfoot>
    </table>
</form>