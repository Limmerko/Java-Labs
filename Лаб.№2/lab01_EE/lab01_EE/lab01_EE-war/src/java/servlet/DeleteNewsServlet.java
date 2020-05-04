/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.IDAO;
import java.io.IOException;
import static java.util.Date.parse;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.News;

/**
 *
 * @author Limmerko
 */
public class DeleteNewsServlet extends HttpServlet{
    
    @EJB 
    private IDAO dao;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       dao.delete(Integer.parseInt(request.getQueryString()));
       response.sendRedirect("news");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       dao.delete(Integer.parseInt(request.getQueryString()));
       response.sendRedirect("news");
    }

    @Override
    public String getServletInfo() {
        return "";
    }
}
