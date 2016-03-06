/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.HashMap;

/**
 * @author miguelcasillas
 */
public class GameSettings {
    private static int round = 1;
    private static HashMap<String,Integer> scores = new HashMap();
    private static int maxScore = 5;
    private static int monsterNumber;
    private static boolean winner;
    public static void resetGameSettings(){
        round = 1;
        scores.clear();
        monsterNumber = 1;
    }
    public static int getRoundNumber(){
        return round;
    }
    public static void increaseRoundNumber(){
        round++;
        winner = false;
    }
    public static String winner(){
        for (Object o : scores.keySet()) 
            if (scores.get(o).equals(maxScore)) 
              return o.toString();
        return "";
    }
    public static boolean registerPoint(String _user){
        boolean res = false;
        int num = scores.containsKey(_user)? scores.get(_user):0;
        scores.put(_user, num);
        winner = true;
        return res;
    }
    public static void setMonsterNumber(int _number){
        monsterNumber = _number;
    }
    public static int getMonsterNumber(){
        return monsterNumber;
    }
    public static boolean hasRoundWinner(){
        return winner;
    }
}
