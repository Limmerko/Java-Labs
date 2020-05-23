<%-- 
    Document   : addCompany
    Created on : 23.05.2020, 14:35:57
    Author     : Limmerko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Company</title>
    </head>
    <body>
        <form action="addCompany" method="post"
              <table>
                  <tr>
                      <td>
                          Title
                      </td>
                      <td><input required type="text" name="title"/></td>
                   </tr>
                   <tr>
                      <td>
                          Address
                      </td>
                      <td><input required type="text" name="address"/></td>
                    </tr>
            </table>
    </form>
    </body>
</html>
