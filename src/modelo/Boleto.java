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
public class Boleto implements Serializable{
   private String persona;
   private int ci;
   private String placa;
   private String fecha;
   private String origen;
   private String destino;
   private double costo;
   private Hora hora;
   private String clase;
   
   public Boleto(String persona,int ci,String placa,String fecha,String origen, String destino, String clase,double costo, Hora hora){
       this.persona=persona;
       this.ci=ci;
       this.placa=placa;
       this.fecha=fecha;
       this.origen=origen;
       this.destino=destino;
       this.clase=clase;
       this.costo=costo;
       this.hora=hora;
   }
   
   public String getOrigen(){return origen;}
   public String getDestino(){return origen;}
   public double getCosto(){return costo;}
   public Hora getHora(){return hora;}
   public String getClase(){return clase;}
   public String getPlaca(){return placa;}
   public int getCI(){return ci;}
   
   public String toString(){
       return" persona: "+persona+" ci: "+ci+" Asignado:"+placa+" fecha:"+fecha+" origen:"+origen+" destino:"+destino+" costo:"+costo+" hora:"+hora.getHoraForVuelo()+" clase:"+clase;
   }
   /*public String convertirClase(){
    String res="Normal";
    if(clase==1) res="Turista";
    else if(clase==2) res="Bussines";
    return res;
   }*/
   public Object[] getForTable(){
       return new Object[]{persona,ci,placa,fecha,origen,destino,costo,hora.getHoraForVuelo(),clase};
   }
   public boolean compararForVuelo(Boleto otro){
       boolean res=false;
       if(origen.equals(otro.getOrigen()) && destino.equals(otro.getDestino()) &&
               hora.getHoraForVuelo().equals(otro.getHora().getHoraForVuelo())&&
               placa.equals(otro.getPlaca()))
           res=true;
       return res;
   }
   public void setDestino(String destino){this.destino=destino;}
}
