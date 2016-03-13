/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author miguelcasillas
 */
public class ClientSettings {
    private int score;
    private final String username;
    private int lastRound;
    private int lastMonsterNumber;
    //set the settings for a user
    //this will be an object in the Client class, this means every Client
    //will have it's settings
    public ClientSettings(String _username){
        score = 0;
        lastRound = 0;
        username = _username;
    }
    public int getScore(){
        return score;
    }
    public void resetScore(){
        score = 0;
    }
    public void increaseScore(){
        score++;
    }
    public int getLastRound(){
        return lastRound;
    }
    public void setLastRound(int _lastRound){
        lastRound = _lastRound;
    }
    public String getUsername(){
        return username;
    }
    public int getLastMonsterNumber(){
        return lastMonsterNumber;
    }
    public void setLastMonsterNumber(int _lastMonsterNumber){
        lastMonsterNumber = _lastMonsterNumber;
    }
}
