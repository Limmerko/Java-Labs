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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import singleton.CountNews;

/**
 *
 * @author Limmerko
 */
public class CountAddNewsServlet extends HttpServlet{
    
    @EJB 
    private CountNews countNews;
    
    @EJB
    private IStatefulBean sb;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setAttribute("countAdd", countNews.getCount());
       List<String> list =sb.returnList();
       request.setAttribute("titleAdd", list);
       request.getRequestDispatcher("count-add-news.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "";
    }
}
