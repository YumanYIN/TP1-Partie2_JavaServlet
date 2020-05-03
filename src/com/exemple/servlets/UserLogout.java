package com.exemple.servlets;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user_logout")
public class UserLogout extends HttpServlet {
    public static final String CONNEXION_PAGE  = "/connexion";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);//avoid creating Session
        if(session == null){
            response.sendRedirect( CONNEXION_PAGE );
            return;
        }
        session.setAttribute("sessionUtilisateur",null);
        session.removeAttribute("sessionUtilisateur");
        response.sendRedirect( CONNEXION_PAGE );
    }
}
