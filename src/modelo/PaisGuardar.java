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
 * @author Ariel
 */
public class PaisGuardar implements Serializable{
    private ArrayList<Aeropuerto> aeropuertos;  //lista aeropuertos
    private ArrayList<Pasajero> pasajeros;      //lista pasajeros
    private ArrayList<Pasajero> pasajerosConBoleto;
    //private ArrayList<Boleto> boletos;
    private String fecha;
    private int hora;
    private int min;
    private Itiner itinerario;
    
    public PaisGuardar(ArrayList<Aeropuerto> aeropuertos, ArrayList<Pasajero> pasajeros,
            ArrayList<Pasajero> pasajerosConBoleto, String fecha, int hora, int min, Itiner itinerario){
        this.aeropuertos=aeropuertos;
        this.pasajeros=pasajeros;
        this.pasajerosConBoleto=pasajerosConBoleto;
        this.fecha=fecha;
        this.hora=hora;
        this.min=min;
        this.itinerario=itinerario;
    }
    
    public ArrayList<Aeropuerto> getAeropuertos(){ return aeropuertos;}
    public ArrayList<Pasajero> getPasajeros(){return pasajeros;}
    public ArrayList<Pasajero> getPasajerosConBoleto(){return pasajerosConBoleto;}
    public String getFecha(){return fecha;}
    public int getHora(){return hora;}
    public int getMin(){return min;}
    public Itiner getItinerario(){return itinerario;}
}
