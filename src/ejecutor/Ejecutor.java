/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejecutor;

/**
 *
 * @author miguelcasillas
 */
public class Ejecutor {
    static AutoPlayer[] players;
    static ObjetoTiempos[] tiempos;
    static boolean [] vivos;
    static int n;
    static int m;
    public static boolean hayClienteVivo(){
        boolean res = false;
        for(int i=0;i<vivos.length;i++){
            vivos[i] = players[i].isAlive();
            res = res || vivos[i];
        }
        return res;
    }
    public static void imprimeEstadisticas(){
        for(int i=0;i<tiempos.length;i++)
            System.out.println("Promedio: "+tiempos[i].getPromedio()+" Desv: "+tiempos[i].getDesv());
    }
    public static void main(String[] args) {
        //N clientes, cada cliente envía M solicitudes
        n = 51; m = 300;
        players = new AutoPlayer[n];
        tiempos = new ObjetoTiempos[n];
        vivos = new boolean[n];
        for(int i=0;i<n;i++) tiempos[i] = new ObjetoTiempos();
        for(int i=0;i<n;i++) players[i] = new AutoPlayer(m,i,tiempos[i]);
        for(int i=0;i<n;i++) players[i].start();
        while(hayClienteVivo());
        System.out.println("Se acabaron de ejecutar los clientes.");
        imprimeEstadisticas();
    }    
}