import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import beans.GameBean;

@WebServlet(urlPatterns = {"/Home"})
public class HomeController extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if(request.getParameter("new") != null){
            HttpSession session = request.getSession();
            ServletContext context = request.getServletContext();
            GameBean game = new GameBean();
            context.setAttribute("game", game);
            response.sendRedirect(request.getContextPath() + "/Game");
        }
        else{
            // load a new game
        }
    }
}  