/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import SessionBeans.PersonLocal;
import SessionBeans.WorkLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Work;

/**
 *
 * @author Limmerko
 */
public class WorkController {
    @EJB
    private WorkLocal workLocal;
    
    private Work work = new Work();
    
    protected void goGet(HttpServletRequest request,
            HttpServletResponse response) 
                throws ServletException, IOException {
        request.setAttribute("persons", workLocal.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/showPersons.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) 
            throws ServletException, IOException {
    String act = request.getParameter("act");
    RequestDispatcher dispatcher;
    switch (act){
        case ("Update"):
            work.setTitle(request.getParameter("titleUpdate"));
            work.setNumber(Integer.parseInt(request.getParameter("numberUpdate")));
            workLocal.update(work);
            dispatcher =
                request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            break;
        case ("Create"):
            work = new Work();
            work.setTitle(request.getParameter("titleCreate"));
            work.setNumber(Integer.parseInt(request.getParameter("titleUpdate")));
            workLocal.add(work);
            dispatcher =
                request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            break;
        case ("Delete"):
            workLocal.remove(work);
            break;
        default:
            dispatcher=request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
    }
    }
}
