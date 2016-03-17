/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejecutor;

import client.Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import serializables.Monster;

/**
 *
 * @author miguelcasillas
 */
public class AutoPlayer extends Thread {
    public static int lastMonsterRound;
    private static AutoSettings settings;
    AutoPlayerInput api;
    private int queries;
    private String username = "usuario";
    private ObjetoTiempos objT;
    private long [] tiempos;
    public AutoPlayer(int _queries, int _userNumber, ObjetoTiempos _objT){
        queries = _queries;
        settings = new AutoSettings();
        api = new AutoPlayerInput(settings);
        api.start();
        username += _userNumber;
        objT = _objT;
    }
    public void sendMonster(int monsterNumber, int query){
        try {
            Socket clientSocket = new Socket("10.105.200.199",7896);
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            int registeredRound = settings.getLastRound();
            Monster mons = new Monster(registeredRound, monsterNumber, username);
            //leer tiempo inicio
            long inicio = System.currentTimeMillis();
            out.writeObject(mons);
            Monster res = (Monster) in.readObject();
            //leer tiempo final
            tiempos[query] = System.currentTimeMillis()-inicio;
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run(){
        tiempos = new long[queries];
        for(int i=0;i<queries;i++){
            sendMonster(settings.getLastMonsterNumber(),i);
        }
        killPlayer();
    }
    private double [] calcTiempEstad(){
        double sum = 0.0;
        double num = 0.0;
        for(int i=0;i<tiempos.length;i++) sum+=tiempos[i];
        double mean = sum/tiempos.length;
        for(int i=0;i<tiempos.length;i++) num+=Math.pow((tiempos[i]-mean), 2);
        double [] res = {mean,Math.sqrt(num/tiempos.length)};
        return res;
    }
    public void killPlayer(){
        api.terminate();
        double [] estad = calcTiempEstad();
        objT.setPromedio(estad[0]);
        objT.setDesv(estad[1]);
        //System.out.println("Se acabó de ejecutar el "+username);
    }
}
