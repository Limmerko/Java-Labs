/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;


import DAO.IDAO;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.News;
/**
 *
 * @author Limmerko
 */
public class NewsServlet extends HttpServlet {
    
    @EJB 
    private IDAO dao;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try { 
            List<News> news = dao.getAll();
            request.setAttribute("news", news);
            request.getRequestDispatcher("newsList.jsp").forward(request, response);
        } catch (Exception e) { 
            throw new ServletException(e.getLocalizedMessage()); 
        }    
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
        try{
            request.getRequestDispatcher("newsList.jsp")
                    .forward(request,response);
        }catch (Exception e) { 
            throw new ServletException(e.getMessage()); 
        }    
    }

    @Override
    public String getServletInfo() {
        return "";
    }


}
