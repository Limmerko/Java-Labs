<%-- 
    Document   : updateWork
    Created on : 04.05.2020, 21:43:13
    Author     : Limmerko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All pages</title>
    </head>
    <body>
    <table align="center">
    <tr>
        <td>Название</td>
        <td></td>

    </tr>
    <c:forEach items="${teams}" var="team">
                 <tr>
                    <td>${team.name}</td>
                    <form action=<c:url value="editTeam.jsp"/>>
                        <input type="hidden" name="prmNameUp" value="${team.name}">
                        <input class="btn" type="submit" value="Update">                     
                    </form>
                </td>
                </tr>   
        </c:forEach>
        </table>

    </body>
</html>
