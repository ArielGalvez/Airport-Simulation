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
public class Aeropuerto implements Serializable{

    /**
     * @param args the command line arguments
     */
    private String nombre;
    private ArrayList<Avion> colaAviones;//cola de aviones
    //private String asignadoAmatricula;
    
    public Aeropuerto(String nombre){
        this.nombre=nombre;
        colaAviones=new ArrayList<Avion>();
        //asignadoAmatricula="S/M";
    }
    public void encolar(Avion a){
        a.resetearDatos(nombre);
        colaAviones.add(a);
        //asignadoAmatricula=colaAviones.get(0).getPlaca();
    }
    public Avion desencolar(int ind){//ind es la prioridad
        Avion avion=colaAviones.get(ind);
        colaAviones.remove(ind);
        return avion;
    }
    public int getSize(){
        return colaAviones.size();
    }
    public boolean esVacio(){return colaAviones.isEmpty();}
    public ArrayList<Avion> getAviones(){
        return colaAviones;
    }
    public String getNombre(){return nombre;}
    
    public String mostrarAviones(){
        String res=nombre+" Aviones que tiene:\n";
        for(int i=0;i<colaAviones.size();i++){
            res=res+colaAviones.get(i).toString()+"\n";
        }
        return res;
    }
    public String mostrarBoletos(){
        String res=nombre+" Boletos que tiene:\n";
        for(int i=0;i<colaAviones.size();i++){
            res=res+colaAviones.get(i).mostrarBoletos()+"\n";
        }
        return res;
    }
    
    public boolean tieneAsientosDisponibles(){
        boolean res=false;
        for(int i=0;i<colaAviones.size();i++){
            if(!colaAviones.get(i).avionLleno()){
                res=true; break;
            }
        }
        return res;
    }
    
    public boolean addBoleto(String persona,int ci, String fecha,String 
        origen, String destino, String clase,double costo, Hora hora){
        boolean res=false;
        int indice=0;
        while(indice<colaAviones.size()){
            if(colaAviones.get(indice).addBoleto(persona,ci, fecha, origen, destino, clase, costo, hora)){
                res=true;
                return res;
            }
            indice++;
        }
        return res;
    }
    public int getCantBoletos(){
        int res=0;
        for(int i=0;i<colaAviones.size();i++){
            res+=colaAviones.get(i).getCantBoletos();
        }
        return res;
    }
    
}
