<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Список всех новостей</title>
</head>
<body>
<h1>Список всех новостей</h1>
<br/>
<a href="/news/create">Добавить новость</a>
<br/>
<table border="2">
    <thead>
        <th>#</th>
        <th>Название</th>
        <th>Описание</th>
    </thead>
    <c:forEach var ="news" items="${newsList}">
        <c:url value="/news/edit/${news.id}" var="showNewsUrl" />
        <c:url value="/news/${news.id}" var="newsDeleteUrl" />
        <c:url value="/news/update/${news.id}" var="newsUpdateUrl" />

        <tr>
            <td><c:out value="${news.id}"/></td>
            <td><c:out value="${news.title}"/></td>
            <td><c:out value="${news.text}"/></td>
            <td>
                <a href="${showNewsUrl}">Просмотр</a> |
                <a href="${newsUpdateUrl}">Изменить</a> |
                <a href="${newsDeleteUrl}">Удалить</a>
            </td>
        </tr>

    </c:forEach>
</table>

<a href="/">Назад</a>