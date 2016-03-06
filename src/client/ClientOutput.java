/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author miguelcasillas
 */
public class ClientOutput extends Thread{
    private String tcpIP;
    private int tcpPort;
    private JButton btns [];
    public ClientOutput(String _tcpIP, int _tcpPort, JButton [] _btns){
        tcpIP = _tcpIP;
        tcpPort = _tcpPort;
        btns = _btns;
        setListeners();
    }
    private void setListeners(){
        for(int i=0;i<btns.length;i++){
            btns[i].addActionListener(new ActionListener() { 
                @Override
                public void actionPerformed(ActionEvent e) { 
                    
                } 
            } );
        }
    }
    @Override
    public void run(){
        
    }
}
