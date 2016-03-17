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
public class ObjetoTiempos {
    private double promedioTiempos;
    private double desvEst;
    public void setPromedio(double _promedio){
        promedioTiempos = _promedio;
    }
    public void setDesv(double _desv){
        desvEst = _desv;
    }
    public double getPromedio(){
        return promedioTiempos;
    }
    public double getDesv(){
        return desvEst;
    }
}
