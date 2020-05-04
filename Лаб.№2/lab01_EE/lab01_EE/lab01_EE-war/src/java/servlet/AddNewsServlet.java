/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.IDAO;
import bean.IStatefulBean;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.News;
import singleton.CountNews;

/**
 *
 * @author Limmerko
 */
public class AddNewsServlet extends HttpServlet {
    
    @EJB 
    private IDAO dao;
    
    @EJB
    private IStatefulBean sb;
    
    @EJB 
    private CountNews countNews;
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try { 
            request.getRequestDispatcher("add-news.jsp").forward(request, response);
        } catch (Exception e) { 
            throw new ServletException(e.getLocalizedMessage()); 
        } 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        try {
            News news = new News();
            news.setTitle(request.getParameter("title"));
            sb.addTitle(news.getTitle());
            news.setText(request.getParameter("text"));
            dao.create(news);
            countNews.add();
            response.sendRedirect("news");
        } catch (Exception e) {
            throw new ServletException(e.getLocalizedMessage());       
        }
    }
    
    @Override
    public String getServletInfo() {
        return "";
    }
}
