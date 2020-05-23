<%-- 
    Document   : addPerson
    Created on : 23.05.2020, 14:36:07
    Author     : Limmerko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add person</title>
    </head>
    <body>
        <form action="addPerson" method="post"
              <table>
                  <tr>
                      <td>
                          Name
                      </td>
                      <td><input required type="text" name="name"/></td>
                   </tr>
                   <tr>
                      <td>
                          Age
                      </td>
                      <td><input required type="text" name="age"/></td>
                    </tr>
                    <tr>
                      <td>
                          Place_of_work
                      </td>
                      <td>
                          <select name="placeOfWork">
                              <c:forEach items="${works}" var="work">
                                  <option value="${work.id}">${work.title}</option>
                              </c:forEach>
                          </select>
                      </td>
                  </tr>
            </table>
    </form>
    </body>
</html>
