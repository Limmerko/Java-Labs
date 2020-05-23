<%-- 
    Document   : allPersons
    Created on : 23.05.2020, 14:36:47
    Author     : Limmerko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All person</title>
    </head>
    <body>
    <table align="center">
    <tr>
        <td>Name</td>
        <td>Age</td>
        <td>Place_of_work</td>
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
                        <input class="btn" type="submit" value="Delete">                     
                    </form>
                    </td>
                </tr>   
        </c:forEach>
        </table>
    </body>
</html>
