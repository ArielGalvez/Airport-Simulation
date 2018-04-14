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
public class Pasajero implements Serializable{
    private String nombre;
    private int ci;
    private int edad;
    private String genero;
    private int location;
    public Pasajero(String nombre, int ci, int edad, String sexo, int location){
        this.nombre=nombre;
        this.ci=ci;
        this.edad=edad;
        this.genero=sexo;
        this.location=location;
    }
    public String getNombre(){return nombre;}
    public int getCI(){return ci;}
    public int getEdad(){return edad;}
    public String getSexo(){return genero;}
    public int getLocation(){return location;}
    public String convertirLocation(){
        String res="";
        switch(location){
            case 0: res="La Paz";break;
            case 1: res="Cochabamba";break;
            case 2: res="Santa Cruz";break;
            case 3: res="Trinidad";break;
            case 4: res="Cobija";break;
            case 5: res="Sucre";break;
            case 6: res="Tarija";break;
        }
        return res;
    }
    public String toString(){
        return " nombre: "+nombre+" CI: "+ci+" edad: "+edad+" genero: "+genero+" ubicacion: "+convertirLocation();
    }
    public Object[] getForTable(){
    return new Object[]{nombre,ci,edad,genero,convertirLocation()};
    }
    public boolean comparteTo(Pasajero p){
        boolean res=false;
        if(p.getNombre()==nombre&&p.getCI()==ci)
            res=true;
        return res;
    }
    
    public void setLocation(int newLoc){
        this.location=newLoc;
    }
}
