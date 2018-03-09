package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import mediatheque.Utilisateur;

public class VerifAuthetificationServlet {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	protected void doGet (HttpServletRequest request,HttpServletResponse response) 
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
    	response.setContentType("text/html");
    	HttpSession session = request.getSession(true);
    	
    	out.println("<html>");
    	
    	out.println("<head>");
    	String title = "Vérification de l'authentification";
    	out.println("<title>" + title + "</title>");
    	out.println("</head>");
    	
    	out.println("<body bgcolor=\"white\">");
    	Utilisateur user = new Utilisateur("login", "pass");
       	if (user!=null)
       		out.println("Utilisateur enregistré dans la variable session user " + user);
       	else out.println("pas de variable session user");
        out.println("</body>");
        
        out.println("</html>");
    }
}
