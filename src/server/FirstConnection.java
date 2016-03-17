/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import interfaces.GameConnectionRequest;
import java.rmi.RemoteException;

/**
 *
 * @author miguelcasillas
 */
public class FirstConnection implements GameConnectionRequest{
    private int broadcastPort;
    private String broadCastAddress;
    private String tcpAddress;
    private int tcpPort;
    //Set the IP's and ports for the multicast and tcp connections
    public FirstConnection(String _bAddress, int _bPort, String _tcpAddress, int _tcpPort){
        broadCastAddress = _bAddress;
        broadcastPort = _bPort;
        tcpAddress = _tcpAddress;
        tcpPort = _tcpPort;
    }
    //Method to answer the configuration to the client
    @Override
    public String getGameIPandPort(String username) throws RemoteException {
        return broadCastAddress+"_"+broadcastPort+"_"+tcpAddress+"_"+tcpPort+"_"+GameSettings.getUserScore(username);
    }
    
}
