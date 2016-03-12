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
    public ClientSettings(String _username){
        score = 0;
        lastRound = 0;
        username = _username;
    }
    public int getScore(){
        return score;
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
