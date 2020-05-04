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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Person;

/**
 *
 * @author Limmerko
 */
public class PersonController extends HttpServlet {
    
    @EJB
    private PersonLocal personLocal;
    @EJB
    private WorkLocal workLocal;
    
    private Person person = new Person();
    
    protected void goGet(HttpServletRequest request,
            HttpServletResponse response) 
                throws ServletException, IOException {
        request.setAttribute("persons", personLocal.getAll());
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
            person.setName(request.getParameter("nameUpdate"));
            person.setAge(Integer.parseInt(request.getParameter("ageUpdate")));
            person.setPlaceOfWork(workLocal.getById(Long.parseLong(
                    request.getParameter("workIdUpdate"))));
            personLocal.update(person);
            dispatcher =
                request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            break;
        case ("Create"):
            person = new Person();
            person.setName(request.getParameter("nameCreate"));
            person.setAge(Integer.parseInt(request.getParameter("ageCreate")));
             person.setPlaceOfWork(workLocal.getById(Long.parseLong(
                    request.getParameter("workIdCreate"))));
            personLocal.add(person);
            dispatcher =
                request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            break;
        case ("Delete"):
            personLocal.remove(person);
            break;
        default:
            dispatcher=request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
    }
    }
}
