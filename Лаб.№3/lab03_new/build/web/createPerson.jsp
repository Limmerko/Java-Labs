<%-- 
    Document   : createperson
    Created on : 04.05.2020, 22:05:48
    Author     : Limmerko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить сотрудника</title>
    </head>
    <body>
        <form action="createPerson" method="post"
              <table>
                  <tr>
                      <td>
                          Имя
                      </td>
                      <td><input required type="text" name="name"/></td>
                   </tr>
                   <tr>
                      <td>
                          Возраст
                      </td>
                      <td><input required type="text" name="age"/></td>
                    </tr>
                    <tr>
                      <td>
                          Место работы
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
