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
    public FirstConnection(String _bAddress, int _bPort, String _tcpAddress, int _tcpPort){
        broadCastAddress = _bAddress;
        broadcastPort = _bPort;
        tcpAddress = _tcpAddress;
        tcpPort = _tcpPort;
    }
    @Override
    public String getGameIPandPort() throws RemoteException {
        return broadCastAddress+"_"+broadcastPort+"_"+tcpAddress+"_"+tcpPort;
    }
    
}
