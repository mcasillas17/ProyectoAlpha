/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguelcasillas
 */
public class GameOutput extends Thread {
    
    private static InetAddress gameGroup;
    private static int gamePort = 6789; 
    private static String broadcastAddress = "228.11.13.17";
    private static MulticastSocket mulSocket;
    
    public GameOutput(){
        try{
            gameGroup = InetAddress.getByName(broadcastAddress);
            mulSocket = new MulticastSocket(gamePort);
            mulSocket.joinGroup(gameGroup);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run(){
        GameSettings.resetGameSettings();
        Random r = new Random();
        int number;
        while(true){
            try{
                //System.out.println("Enviando datos del juego a "+broadcastAddress);
                number = r.nextInt(9);
                String message;
                String win = GameSettings.winner();
                //Send a message if we have a winner
                if(win.equals("")) {
                    message = "r-"+GameSettings.getRoundNumber()+"-"+number+'\0';
                    GameSettings.setMonsterNumber(number);
                    GameSettings.increaseRoundNumber();
                }
                else{
                    message = "w-"+win;
                    GameSettings.resetGameSettings();
                }
                System.out.println("Enviando \""+message+"\" a "+broadcastAddress);
                //Create datagram packet
                byte [] m = message.getBytes();
                DatagramPacket datagram = new DatagramPacket(m, m.length, gameGroup, gamePort);
                mulSocket.send(datagram); //Send datagram
                try {
                    Thread.sleep(2000);//Sleep for two seconds
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameOutput.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(GameOutput.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Ocurrió un problema en el envío broadcast");
            }
        }
    }
    
}
