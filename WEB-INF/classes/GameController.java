import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import beans.GameBean;

@WebServlet(urlPatterns = {"/Game"})
public class GameController extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();

        GameBean game = (GameBean) context.getAttribute("game");
        request.setAttribute("secreteNumber", game.getSecreteNumber());
        request.setAttribute("prevGuesses", game.getPrevGuesses());
        request.setAttribute("roundNumber", game.getRound());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Game.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        GameBean game = (GameBean) context.getAttribute("game");
        
        if(request.getParameter("save") != null){
            String username = "test";
            try{
                FileOutputStream fileOut = new FileOutputStream(context.getRealPath("/WEB-INF/saved-games/username.ser"));
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(game);
                out.close();
                fileOut.close();
            } catch(IOException i){
                i.printStackTrace();
            }
        }
        else{
            int guess = Integer.parseInt(request.getParameter("guess"));

            String[] prevGuesses = game.getPrevGuesses();
            boolean valid = true;

            // Need to do guess validation...

            for(int i = 0; i < prevGuesses.length; i++){
                if(prevGuesses[i] != null){
                    if(guess == Integer.parseInt(prevGuesses[i])){
                        // Error number has already been guessed this game
                        valid = false;
                        break;
                    }
                }
                else{
                    break;
                }
            }

            if(valid){
                if(guess == game.getSecreteNumber()){
                    // end game
                }
                else{
                    game.addNewGuess(Integer.toString(guess));
                    game.incRound();
                }
            }

            doGet(request, response);
        }
    }
}  