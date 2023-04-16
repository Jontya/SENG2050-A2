package beans;

import java.lang.Math;
import java.io.Serializable;

public class GameBean implements Serializable{
    private String username;
    private int points;
    private int round;
    private int secreteNumber;
    private String[] prevGuesses;

    public GameBean(){
        username = "";
        points = 0;
        round = 0;
        secreteNumber = (int)(Math.random() * 11) + 1;
        prevGuesses = new String[10];
    }

    public String getHighLow(){
        String highLow = "";
        if(prevGuesses[0] == null){
            return highLow;
        }
        else{
            highLow = "The Secrete Number is: ";
            for(int i = 0; i < prevGuesses.length; i++){
                if(prevGuesses[i] == null){
                    return highLow;
                }

                if(Integer.parseInt(prevGuesses[i]) > secreteNumber){
                    highLow += "Less than " + prevGuesses[i] + ", ";
                }
                else{
                    highLow += "Greater than " + prevGuesses[i] + ", ";
                }
            }
            return highLow;
        }
    }

    public String getPrevGuesses(){
        String out = "";
        if(prevGuesses[0] == null){
            out = "There have been no previous guesses";
        }
        else{
            for(int i = 0; i < prevGuesses.length; i++){
                if(prevGuesses[i] == null){
                    break;
                }

                out += prevGuesses[i] + ", ";
            }
        }
        return out;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public int getPoints(){
        return 100-((round + 1) -1)*10;
    }

    public int getSecreteNumber(){
        return secreteNumber;
    }

    public void incRound(){
        round++;
    }
    
    public int getRound(){
        return round;
    }

    public void addNewGuess(String guess){
        prevGuesses[round] = guess;
    }

    public String[] getGuessList(){
        return prevGuesses;
    }

}