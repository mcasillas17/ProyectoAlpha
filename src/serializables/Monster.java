/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializables;

import java.io.Serializable;


/**
 *
 * @author miguelcasillas
 */
public class Monster implements Serializable{
    private int number;
    private int round;
    private String user;
    //Set the round, monsterNumber inside the grid, and the user number
    //for this monster
    public Monster(int _round, int _number, String _user){
        round = _round;
        number = _number;
        user = _user;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    @Override
    public String toString(){
        return "Username: "+user+" Round: "+round+" Number: "+number;
    }
}
