package com.exemple.filters;

import com.exemple.beans.Utilisateur;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class ConnexionFilter implements Filter {
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String CONNEXION        = "/connexion";

    public void init(FilterConfig fConfig) throws ServletException {
    }
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String currentURL = request.getRequestURI();
        String ctxPath = request.getContextPath();
        String targetURL = currentURL.substring(ctxPath.length());

        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();

        System.out.println(targetURL);
        if (!("/connexion").equals(targetURL)) {
            if (session == null || session.getAttribute(ATT_SESSION_USER) == null){
                response.sendRedirect(CONNEXION);
            }else {
                chain.doFilter(request, response);
            }
        }else{
            chain.doFilter(request, response);
        }
        return;

        //Utilisateur sessionUtilisateur = (Utilisateur) session.getAttribute( ATT_SESSION_USER );



    }
}
