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

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
        this.points = points;
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

    public String[] getPrevGuesses(){
        return prevGuesses;
    }
}