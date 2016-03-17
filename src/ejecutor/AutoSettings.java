/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejecutor;

/**
 *
 * @author miguelcasillas
 */
public class AutoSettings {
    private int lastRound;
    private int lastMonsterNumber;
    public AutoSettings(){
        lastRound = 0;
        lastMonsterNumber = 0;
    }
    public int getLastRound(){
        return lastRound;
    }
    public void setLastRound(int _lastRound){
        lastRound = _lastRound;
    }
    public int getLastMonsterNumber(){
        return lastMonsterNumber;
    }
    public void setLastMonsterNumber(int _lastMonsterNumber){
        lastMonsterNumber = _lastMonsterNumber;
    }
}
