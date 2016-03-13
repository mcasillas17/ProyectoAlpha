/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import interfaces.GameConnectionRequest;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguelcasillas
 */
public class Server {
    private static final int portRMI = 4567;
    private static final String nameRMI = "FirstConnection";
    private static final int broadcastPort = 6789; 
    private static final String broadcastAddress = "228.11.13.17";
    private static final int tcpPort = 7896;
    private static boolean setRMIService(){
        boolean ok = false;
        System.setProperty("java.security.policy","/Users/miguelcasillas/NetBeansProjects/ProyectoAlpha/src/server/server.policy");
        if (System.getSecurityManager() == null) {
           System.setSecurityManager(new SecurityManager());
        }
        try{
            LocateRegistry.createRegistry(portRMI);
            Registry myRegistry = LocateRegistry.getRegistry(portRMI);
            String myIPAddress = Inet4Address.getLocalHost().getHostAddress();
            FirstConnection fc = new FirstConnection(broadcastAddress, broadcastPort, myIPAddress, tcpPort);
            GameConnectionRequest gcr = (GameConnectionRequest)UnicastRemoteObject.exportObject(fc,portRMI);
            myRegistry.rebind(nameRMI, gcr);
            ok = true;
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
    public static void main(String[] args) {
        boolean okRMI = setRMIService();
        System.out.println(okRMI?"Se pudo levantar el RMI":"No se pudo levantar el RMI");
        if(!okRMI) return;
        System.out.println("Se empezará el servicio broadcast");
        GameOutput go = new GameOutput();
        go.start();
        GameInput gi = new GameInput();
        gi.start();
    }
}
