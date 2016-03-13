/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author miguelcasillas
 */
public class ClientInput extends Thread{
    private String broadcastIP;
    private int broadcastPort;
    private MulticastSocket socket;
    private String previousMessage;
    private JButton btns[];
    private ClientSettings settings;
    private JLabel lblScore;
    
    public ClientInput(String _broadcastIP, int _broadcastPort, JButton [] _btns, ClientSettings _settings, JLabel _lblScore){
        broadcastIP = _broadcastIP;
        broadcastPort = _broadcastPort;
        previousMessage = "";
        btns = _btns;
        settings = _settings;
        lblScore = _lblScore;
    }
    private void changeBtnImage(final int btnNumber){
        ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("resources/images/monster.png"));
        float scale = 5f; // 2 times smaller
        int width = ii.getIconWidth();
        int newWidth = (int) ((float)width / (float)scale);
        btns[btnNumber].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
                java.awt.Image.SCALE_SMOOTH)));
        Timer timer = new Timer();
        TimerTask action = new TimerTask() {
            public void run() {
               ImageIcon i2 = new ImageIcon(getClass().getClassLoader().getResource("resources/images/grass.png"));
                        float scale = 6f; // 2 times smaller
                        int width = i2.getIconWidth();
                        int newWidth = (int) ((float)width / (float)scale);
                        btns[btnNumber].setIcon(new ImageIcon(i2.getImage().getScaledInstance(newWidth, -1,
                                java.awt.Image.SCALE_SMOOTH))); 
            }

        };
        timer.schedule(action, 750);
        
    }
    
    private void doMagicThing(String m){
        String arr[] = m.split("-");
        if(arr[0].equals("r")){
            int round = Integer.parseInt(arr[1]);
            int number = Integer.parseInt(arr[2]);
            settings.setLastRound(round);
            settings.setLastMonsterNumber(number);
            changeBtnImage(number);
        }else{
            String bla = "";
            if(arr[1].equals(settings.getUsername())){
                bla = "Ganaste";
            }
            else{
                bla = "El ganador es: "+arr[1];
            }
            settings.resetScore();
            lblScore.setText("Score: 0");
            JOptionPane.showMessageDialog(null, bla);
        }
    }
    
    @Override
    public void run(){
        socket = null;
        try {
            InetAddress group = InetAddress.getByName(broadcastIP);
            socket = new MulticastSocket(broadcastPort);
            socket.joinGroup(group);
            while(true){
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
}
