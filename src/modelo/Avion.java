/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Galvez
 */
public class Avion implements Serializable{
    private String placa;
    private String nombre;
    private String modelo;
    private int capMaxima;
    private boolean enVuelo;
    private ArrayList<Boleto> boletosAsignados;
    private String origen;
    private String destino;
    private int origenNum, destinoNum;
    
    boolean conDestino;
    private int horaVuelo[];
    private String fechaVuelo;
    
    public Avion(String modelo, String placa, String nombre, int capMaxima){
        this.modelo=modelo;
        this.placa=placa;
        this.nombre=nombre;
        this.capMaxima=capMaxima;
        enVuelo=false;
        boletosAsignados=new ArrayList<>();
        this.conDestino=false;
        
        horaVuelo = new int[2];
    }
    public void setVolando(){
        if(enVuelo)     enVuelo=false;
        else if(!enVuelo)            enVuelo=true;
    }
    public void volando(){enVuelo=true;}
    public void detener(){enVuelo=false;}
    
    public boolean getVolando(){return enVuelo;}
    public String getModelo(){return modelo;}
    public String getPlaca(){return placa;}
    public String getNombre(){return nombre;}
    public int getCapMax(){return capMaxima;}
    public int getCantPas(){return boletosAsignados.size();}
    
    public boolean avionLleno(){
        boolean res=false;
        if(boletosAsignados.size()==capMaxima) res=true;
        return res;
    }
    public String toString(){
        return " modelo: "+modelo+" placa: "+placa+" nombre: "+nombre+" capacidad: "+capMaxima+" pasajeros: "+boletosAsignados.size();
    }
    
    public String mostrarBoletos(){
        String res="";
        for(int i=0;i<boletosAsignados.size();i++){
            if(!boletosAsignados.isEmpty())
            res+=boletosAsignados.get(i).toString()+"\n";
        }
        return res;
    }
    public Object[] getForTable(String location){
        return new Object[]{location,modelo,placa,nombre,capMaxima,boletosAsignados.size()};
    }
    public Object[] getForMonitoreo(){
        String estado;
        if(enVuelo)     estado="SI";
        else            estado="NO";
        return new Object[]{placa,estado,getCantPas(),origen,destino};
    }
    /*public Object[] getForMonitoreo(){
        JLabel estado=new JLabel("dsas");
        if(enVuelo)     estado.setBackground(Color.green);
        else            estado.setBackground(Color.red);
        System.out.println(estado);
        return new Object[]{placa,estado,getCantPas()};
    }*/
    /*mas metodos*/
    
    public boolean addBoleto(String persona,int ci, String fecha,String 
        origen, String destino, String clase,double costo, Hora hora){
        boolean res=false;
        if(boletosAsignados.isEmpty()){
            boletosAsignados.add(new Boleto(persona,ci,placa,fecha, origen, destino, clase,costo, hora));
            this.origenNum=getIdAeropuerto(origen);
            this.origen=origen;
            this.destinoNum=getIdAeropuerto(destino);
            this.destino=destino;
            conDestino=true;
            //System.out.println("origen: "+origenNum+" destino: "+destinoNum);
            horaVuelo[0]=hora.getHora();
            horaVuelo[1]=hora.getMinuto();
            
            fechaVuelo=fecha;
            res=true;
            return res;
        }
        else if(conDestino&&this.destino.equals(destino)){
            if(boletosAsignados.size()<capMaxima){
                boletosAsignados.add(new Boleto(persona,ci,placa,fecha, origen, destino, clase,costo, hora));
                res=true;
                return res;
            }
        }
        return res;
    }
    public ArrayList<Boleto> getBoletosAsignados(){
        return boletosAsignados;
    }
    public int getCantBoletos(){return boletosAsignados.size();}
    public int getDestinoNum(){return destinoNum;}
    public int getOrigenNum(){return origenNum;}
    
    public void resetearDatos(String origen){
        //boletosAsignados.clear();
        this.origen=origen;
        destino="Sin asignar";
        conDestino=false;
        enVuelo=false;
        boletosAsignados.clear();
    }
    
    public int[] getHoraVuelo(){return horaVuelo;}
    
    public String getFechaVuelo(){return fechaVuelo;}
    
    public int getIdAeropuerto(String location){
        int res=0;
        switch(location){
                case "La Paz":      res=0; break;
                case "Cochabamba":  res=1; break;
                case "Santa Cruz":  res=2; break;
                case "Trinidad":    res=3; break;
                case "Cobija":      res=4; break;
                case "Sucre":       res=5; break;
                case "Tarija":      res=6; break;
            }
        return res;
    }
    public void setDestinoNun(int destino){this.destinoNum=destino;}
    public void setDestino(String destino){
        this.destino=destino;
        for (int i = 0; i < boletosAsignados.size(); i++) {
            boletosAsignados.get(i).setDestino(destino);
        }
    }
 }
