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
        request.setAttribute("highLow", game.getHighLow());

        if(request.getParameter("success").equals("false")){
            String errorString = (String) context.getAttribute("errorString");
            request.setAttribute("errorString", errorString);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Game.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        GameBean game = (GameBean) context.getAttribute("game");
        
        if(request.getParameter("save") != null){
            String username = request.getParameter("username");
            ArrayList<String> nameErrors = new ArrayList<>();
            boolean valid = true;

            // Username must not be empty
            if(username.length() == 0){
                valid = false;
                nameErrors.add("Not be left blank");
            }

            // Username must be one word
            String[] validUsername = username.split(" ");
            if(validUsername.length > 1){
                valid = false;
                nameErrors.add("Only contain one word");
            }

            // Username must not contain any numbers or special characters
            if(!username.matches("[a-zA-Z]+")){
                valid = false;
                nameErrors.add("Not contain any numbers");
            }

            if(valid){
                try{
                    FileOutputStream fileOut = new FileOutputStream(context.getRealPath("/WEB-INF/saved-games/" + username + ".ser"));
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(game);
                    out.close();
                    fileOut.close();
                } catch(IOException i){
                    i.printStackTrace();
                }

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp");
                dispatcher.forward(request, response);
            }
            else{
                String errorString = "The username must: ";
                for(int i = 0; i < nameErrors.size(); i++){
                    errorString += nameErrors.get(i) + ", ";
                }
                context.setAttribute("errorString", errorString);
                response.sendRedirect(request.getContextPath() + "/Game?success=false");
            }

        }
        else{
            String guess = request.getParameter("guess");
            ArrayList<String> guessErrors = new ArrayList<String>();

            String[] prevGuesses = game.getGuessList();
            boolean valid = true;

            // 1. Validate one word
            String[] validWord = guess.split(" ");
            if(validWord.length > 1){
                guessErrors.add("Must only contain one number");
                valid = false;
            }

            // Remove white space from the end of the string;
            guess = guess.replaceAll("\\s", "");

            // Checks that the string is a number;
            if (!guess.matches("[0-9]+")){
                guessErrors.add("Only contain numbers");
                valid = false;
            }
            else{
                // Number is not within bounds
                int guessValid = Integer.parseInt(guess);
                if(guessValid < 1 || guessValid > 11){
                    guessErrors.add("Within the bounds of 1-11");
                    valid = false;
                }
            }

            for(int i = 0; i < prevGuesses.length; i++){
                if(prevGuesses[i] != null){
                    if(guess.equals(prevGuesses[i])){
                        guessErrors.add("Not have been nominated in a previous round");
                        valid = false;
                        break;
                    }
                }
                else{
                    break;
                }
            }

            if(valid){
                if(Integer.parseInt(guess) == game.getSecreteNumber()){
                    request.setAttribute("secreteNumber", game.getSecreteNumber());
                    request.setAttribute("roundNumber", game.getRound());
                    request.setAttribute("points", game.getPoints());

                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/End.jsp");
                    dispatcher.forward(request, response);
                }
                else{
                    game.addNewGuess(guess);
                    game.incRound();
                }
                request.setAttribute("errorString", "");
                response.sendRedirect(request.getContextPath() + "/Game?success=true");
            }
            else{
                String errorString = "The guess must: ";
                for(int i = 0; i < guessErrors.size(); i++){
                    errorString += guessErrors.get(i) + ", ";
                }
                context.setAttribute("errorString", errorString);
                response.sendRedirect(request.getContextPath() + "/Game?success=false");
            }
        }
    }
}  