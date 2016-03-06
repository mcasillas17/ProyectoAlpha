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
                System.out.println("Estoy recibiendo solicitudes TCP");
                try (Socket clientSocket = listenSocket.accept()) {
                    System.out.println("Conexion aceptada");
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                    Monster m = (Monster)in.readObject();
                    System.out.println("Monstruo recibido de: "+m.getUser()+" para la ronda "+m.getRound());
                    int r = GameSettings.getRoundNumber();
                    int n = GameSettings.getMonsterNumber();
                    boolean w = GameSettings.hasRoundWinner();
                    if(m.getRound()==r && m.getNumber()==n && !w){//Is a valid monster
                        GameSettings.registerPoint(m.getUser());
                        out.write(1);
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
