<%-- 
    Document   : allCompanies
    Created on : 23.05.2020, 14:37:07
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
        <td></td>

    </tr>
    <c:forEach items="${company}" var="companies">
                 <tr>
                    <td>${company.title}</td>
                    <td>${company.address}</td>
                    <td><form action=<c:url value="updatePerson.jsp"/>
                        <input type="hidden" name="prmNameUp" value="${company}">
                        <input class="btn" type="submit" value="Update">                     
                    </form>
                    </td>
                    <td><form action="companies" method="post"/>
                        <input type="hidden" name="prmNameUp" value="${company}">
                        <input class="btn" type="submit" value="Delete">
                    </td>
                    </form>
                </tr>   
        </c:forEach>
        </table>
    </body>
</html>
