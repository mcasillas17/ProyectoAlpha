/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import serializables.Monster;

/**
 *
 * @author miguelcasillas
 */
public class GameInput extends Thread{
    ServerSocket listenSocket;
    int serverPort = 7896;
    public GameInput(){
        try {
            listenSocket = new ServerSocket(serverPort);
        } catch (IOException ex) {
            Logger.getLogger(GameInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run(){
        while(true){
            try {
                //System.out.println("Estoy recibiendo solicitudes TCP");
                try (Socket clientSocket = listenSocket.accept()) {
                    //System.out.println("Conexion aceptada");
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                    Monster m = (Monster)in.readObject();
                    System.out.println("Monstruo recibido de: "+m.getUser()+" para la ronda "+m.getRound());
                    int r = GameSettings.getRoundNumber();
                    int n = GameSettings.getMonsterNumber();
                    System.out.println("Ronda del juego: "+r+" // no. monstruo del juego: "+n);
                    boolean w = GameSettings.hasRoundWinner();
                    Monster res;
                    if(m.getRound()==(r-1) && m.getNumber()==n && !w){//Is a valid monster
                        GameSettings.registerPoint(m.getUser());
                        res = new Monster(-1, -1, "correct");
                        out.writeObject(res);
                    }else {
                        res = new Monster(-1,-1,"wrong");
                        out.writeObject(res);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(GameInput.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GameInput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
