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
public class Itiner implements Serializable{
    
    ArrayList<ItemItiner> datos;
    //constructor de la clase itinerario
    public Itiner(){
        datos= new ArrayList<>();
    }
    //metodo para agregar datos al itinerario
    public void add(String origen,String destino, String fecha, Hora hora){
        datos.add(new ItemItiner(origen, destino, fecha, hora));
    }
    //metodo q devuelve los datos del itinerario
    public ArrayList<ItemItiner> getDatos(){return datos;}
    //metodo para eliminar datos del itinerario
    public void remove(int ind){
        datos.remove(ind);
    }
    //metodo q nos devuelve el tamano del itinerario
    public int getSize(){return datos.size();}
    
    public void setDato(int id, String fecha, String hora){
        datos.get(id).setFechaYhora(fecha, hora);
    }
}
