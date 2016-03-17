/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejecutor;

import client.ClientInput;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author miguelcasillas
 */
public class AutoPlayerInput extends Thread{
    private MulticastSocket socket;
    private AutoSettings settings;
    private String previousMessage = "";
    private boolean running = true;
    public AutoPlayerInput(AutoSettings _settings){
        settings = _settings;
    }
    private void doMagicThing(String m){
        String arr[] = m.split("-");
        if(arr[0].equals("r")){
            int round = Integer.parseInt(arr[1]);
            int number = Integer.parseInt(arr[2]);
            settings.setLastRound(round);
            settings.setLastMonsterNumber(number);
        }
    }
    @Override
    public void run(){
        try {
            InetAddress group = InetAddress.getByName("228.11.13.17");
            socket = new MulticastSocket(6789);
            socket.joinGroup(group);
            while(running){
                byte[] buffer = new byte[10];
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                socket.receive(messageIn);
                String m = new String(messageIn.getData()).trim();
                if(!m.equals(previousMessage)){
                    //System.out.println(m+ " from: "+ messageIn.getAddress());
                    previousMessage = m;
                    doMagicThing(m);
                }
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void terminate(){
        running = false;
    }
}
