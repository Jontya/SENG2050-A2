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
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        
        if(request.getParameter("new") != null){
            GameBean game = new GameBean();
            context.setAttribute("game", game);
            response.sendRedirect(request.getContextPath() + "/Game");
        }
        else{
            // Needs extra validation for if file exists
            GameBean game = null;
            String username = request.getParameter("username");
            try {
                FileInputStream fileIn = new FileInputStream(context.getRealPath("/WEB-INF/saved-games/" + username + ".ser"));
                ObjectInputStream in = new ObjectInputStream(fileIn);
                game = (GameBean) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("Username not found");
                c.printStackTrace();
                return;
            }

            context.setAttribute("game", game);
            response.sendRedirect(request.getContextPath() + "/Game");
        }
    }
}  