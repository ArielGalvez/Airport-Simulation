/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;

/**
 *
 * @author Galvez
 */
public class Hora implements Serializable{
    private int hora;
    private int minuto;
    public Hora(String hora){
        String h[]=hora.split(":");
        this.hora=Integer.parseInt(h[0]);
        this.minuto=Integer.parseInt(h[1]);
    }
    public Hora(int hora, int minuto/*, int segundo*/){
        this.hora=hora;
        this.minuto=minuto;
    }
    public String getHoraForVuelo(){
        return (hora<=9?"0":"")+hora+":"+(minuto<=9?"0":"")+minuto;
    }
    public int getHora(){return hora;}
    public int getMinuto(){return minuto;}
}
