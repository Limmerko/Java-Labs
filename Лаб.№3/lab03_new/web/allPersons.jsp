<%-- 
    Document   : allPersons
    Created on : 04.05.2020, 21:42:38
    Author     : Limmerko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Все сотрудники</title>
    </head>
    <body>
    <table align="center">
    <tr>
        <td>Имя</td>
        <td>Возраст</td>
        <td>Место работы</td>
        <td></td>

    </tr>
    <c:forEach items="${persons}" var="person">
                 <tr>
                    <td>${person.name}</td>
                    <td>${person.age}</td>
                    <td>${person.placeOfWork}</td>
                    <td><form action=<c:url value="updatePerson.jsp"/>
                        <input type="hidden" name="prmNameUp" value="${person}">
                        <input class="btn" type="submit" value="Update">                     
                    </form>
                    </td>
                    <td><form action="persons" method="post"/>
                        <input type="hidden" name="prmNameUp" value="${person}">
                        <input class="btn" type="submit" value="Удалить">                     
                    </form>
                    </td>
                </tr>   
        </c:forEach>
        </table>
    </body>
</html>
