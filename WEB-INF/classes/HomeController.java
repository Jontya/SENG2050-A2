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


        String errorString = (String) context.getAttribute("errorString");
        request.setAttribute("errorString", errorString);
        context.setAttribute("errorString", null);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        request.setAttribute("errorString", "");
        
        if(request.getParameter("new") != null){
            GameBean game = new GameBean();
            context.setAttribute("game", game);
            response.sendRedirect(request.getContextPath() + "/Game?success=true");
        }
        else{
            String username = request.getParameter("username");
            File saveFile = new File(context.getRealPath("/WEB-INF/saved-games/" + username + ".ser"));
            if(saveFile.exists()){
                GameBean game = null;
                try {
                    FileInputStream fileIn = new FileInputStream(context.getRealPath("/WEB-INF/saved-games/" + username + ".ser"));
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    game = (GameBean) in.readObject();
                    in.close();
                    fileIn.close();
                } catch (Exception e) {
                    // Throws error
                }

                saveFile.delete();

                context.setAttribute("game", game);
                response.sendRedirect(request.getContextPath() + "/Game?success=true");
            }
            else{
                String errorString = "Username does not exist";
                context.setAttribute("errorString", errorString);
                response.sendRedirect(request.getContextPath() + "/Home");
            }
        }
    }
}  