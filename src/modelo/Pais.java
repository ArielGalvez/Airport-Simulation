/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Galvez
 */
public class Pais {
    private ArrayList<Aeropuerto> aeropuertos;  //lista aeropuertos
    private ArrayList<Pasajero> pasajeros;      //lista pasajeros
    private ArrayList<Pasajero> pasajerosConBoleto;//lista pasajeros con boleto
    private String fecha;
    private Itiner itinerario;
    public Pais(){
       aeropuertos= new ArrayList<Aeropuerto>();
       pasajeros= new ArrayList<Pasajero>();
       pasajerosConBoleto=new ArrayList<Pasajero>();
       fecha="26.10.2015";//al cambiar esto importante q este separadoo por puntos
       itinerario=new Itiner();
       cargarItiner();
    }
    public void setDatos(ArrayList<Aeropuerto> aeropuretos, ArrayList<Pasajero> pasajeros
            ,ArrayList<Pasajero> pasajerosConBoleto, String fecha, Itiner itinerario){
        this.aeropuertos=aeropuretos;
        this.pasajeros=pasajeros;
        this.pasajerosConBoleto=pasajerosConBoleto;
        
        this.fecha=fecha;//.replace('.', '/');
        //System.out.println(""+this.fecha);
        this.itinerario=itinerario;
        
    }
    public String obtenerFecha(){
        Calendar c= Calendar.getInstance();
        int dia = c.get(Calendar.DATE);
        String mes = Integer.toString(c.get(Calendar.MONTH));
        String annio = Integer.toString(c.get(Calendar.YEAR));
        return (dia+mes+annio);
    }
    public void cargarItiner(){
        
        itinerario.add("La Paz", "Cobija", fecha, new Hora(0,30));
        itinerario.add("La Paz", "Cochabamba", fecha, new Hora(1,0));
        itinerario.add("La Paz", "Sucre", fecha, new Hora(1,30));
        itinerario.add("La Paz", "Tarija", fecha, new Hora(2,0));
        
        itinerario.add("Cochabamba", "La Paz", fecha, new Hora(0,30));
        itinerario.add("Cochabamba", "Trinidad", fecha, new Hora(1,0));
        itinerario.add("Cochabamba", "Santa Cruz", fecha, new Hora(1,30));
        itinerario.add("Cochabamba", "Sucre", fecha, new Hora(2,0));
        
        itinerario.add("Santa Cruz", "Trinidad", fecha, new Hora(0,30));
        itinerario.add("Santa Cruz", "Cochabamba", fecha, new Hora(1,0));
        itinerario.add("Santa Cruz", "Sucre", fecha, new Hora(1,30));
        itinerario.add("Santa Cruz", "Tarija", fecha, new Hora(2,0));
        
        itinerario.add("Trinidad", "Cochabamba", fecha, new Hora(0,30));
        itinerario.add("Trinidad", "Santa Cruz", fecha, new Hora(1,0));
        itinerario.add("Trinidad", "Cobija", fecha, new Hora(1,30));
        
        itinerario.add("Cobija", "La Paz", fecha, new Hora(0,30));
        itinerario.add("Cobija", "Trinidad", fecha, new Hora(1,0));
        
        itinerario.add("Sucre", "La Paz", fecha, new Hora(0,30));
        itinerario.add("Sucre", "Cochabamba", fecha, new Hora(1,0));
        itinerario.add("Sucre", "Santa Cruz", fecha, new Hora(1,30));
        itinerario.add("Sucre", "Tarija", fecha, new Hora(2,0));
        
        itinerario.add("Tarija", "La Paz", fecha, new Hora(0,30));
        itinerario.add("Tarija", "Sucre", fecha, new Hora(1,0));
        itinerario.add("Tarija", "Santa Cruz", fecha, new Hora(2,0));
    }
    
    public ArrayList<Aeropuerto> getAeropuertos(){return aeropuertos;}
    public ArrayList<Pasajero> getPasajeros(){return pasajeros;}
    
    /*espero funcione*/
    public ArrayList<Boleto> getBoletos(){
        ArrayList<Boleto> res= new ArrayList<>();
        for(int i=0;i<aeropuertos.size();i++){
            for(int k=0;k<aeropuertos.get(i).getAviones().size();k++){
                for(int j=0;j<aeropuertos.get(i).getAviones().get(k).getBoletosAsignados().size();j++){
                    res.add(aeropuertos.get(i).getAviones().get(k).getBoletosAsignados().get(j));
                }
            }
        }
        return res;
    }
    
    public ArrayList<Pasajero> getPasajerosConBoleto(){return pasajerosConBoleto;}
    public String getFecha(){return fecha;}
    public void setFecha(String f){fecha=f;}
    public int getCantAviones(){
        int res=0;
        for(int i=0;i<aeropuertos.size();i++){
            res=res+aeropuertos.get(i).getSize();
        }
        return res;
    }
    public int getCantAvionesSegunId(int id){
        int res=0;
        res=aeropuertos.get(id).getSize();
        return res;
    }
    public int getCantPasajeros(){return pasajeros.size()+pasajerosConBoleto.size();}
    
    public int getCantBoletos(){
        int res=0;
        for(int i=0;i<aeropuertos.size();i++){
            res+=aeropuertos.get(i).getCantBoletos();
        }
        return res;
    }
    
    public ArrayList<Pasajero> getPasajerosSegun(int id){
        ArrayList<Pasajero> resultado= new ArrayList<Pasajero>();
        for(int i=0;i<pasajeros.size();i++){
            if(pasajeros.get(i).getLocation()==id)
                resultado.add(pasajeros.get(i));
        }
        return resultado;
    }
    public ArrayList<Pasajero> getPasajerosConBoletoSegun(int id){
        ArrayList<Pasajero> resultado= new ArrayList<Pasajero>();
        for(int i=0;i<pasajerosConBoleto.size();i++){
            if(pasajerosConBoleto.get(i).getLocation()==id)
                resultado.add(pasajerosConBoleto.get(i));
        }
        return resultado;
    }
    
    
    public ArrayList<String> getNombresDestinosPara(int id){
        ArrayList<String> resultado= new ArrayList<String>();  
        int valores[]=obtenerValores(id);
        for(int i=0; i<valores.length;i++){
        resultado.add(aeropuertos.get(valores[i]).getNombre());
        }
        return resultado;
    }
    /*  Para ubicarse
    *   0=La Paz 1=Cochabamba 2=Santa Cruz 3=Trinidad 4=Cobija
        5=Sucre 6=Tarija
    */
    public int[] obtenerValores(int id){
        int res[]={};
        int v0[]={4,1,5,6};
        int v1[]={0,3,2,5};
        int v2[]={3,1,5,6};
        int v3[]={1,2,4};
        int v4[]={0,3};
        int v5[]={0,1,2,6};
        int v6[]={0,5,2};
        switch (id){
            case 0: res=v0; break;
            case 1: res=v1; break;
            case 2: res=v2; break;
            case 3: res=v3; break;
            case 4: res=v4; break;
            case 5: res=v5; break;
            case 6: res=v6; break;
        }
        return res;
    }
    public Aeropuerto getAeropuertoSegun(int id){
       return aeropuertos.get(id);
    }
    public void crearAeropuerto(String name){
        aeropuertos.add(new Aeropuerto(name));
    }
    public void registrarAvion(int id, String modelo, String placa, String nombre, int capacidad){
        if(id>-1)//importnte el id hace referencia al pais donde se encuentra
        aeropuertos.get(id).encolar(new Avion(modelo,placa,nombre,capacidad));
    }
    public void registrarPasajero(String nombre, int ci, int edad, String sexo, int location){
        pasajeros.add(new Pasajero(nombre, ci, edad, sexo, location));
        
    }
    public boolean comprarBoleto(int id,String nom,int ci, String fecha,String 
            origen, String destino, String clase,double costo, Hora hora){
        boolean res=false;
        if(/*existeAvion(placa)&&*/existePersona(ci)){
            if(aeropuertos.get(id).addBoleto(nom,ci,fecha, origen, destino, clase, costo, hora)){
                moverApasConBoleto(ci);
                res=true;
            }
        }else{
            System.out.println("persona CI:"+ci/*+" placa:"+placa*/);
            System.out.println("no existe la persona o el avion");}
        return res;
        
    }
    
    public void moverAvion(int ind, int origen, int destino){
        
        //System.out.println("ind: "+ind+" or: "+origen+" dest: "+destino);
        try {
            ArrayList<Boleto> asignados=aeropuertos.get(origen).getAviones().get(ind).getBoletosAsignados();
            //if(asignados==null) return;
            for(int i=0;i<asignados.size();i++){
                moverApasajeros(asignados.get(i).getCI(),destino);
                aeropuertos.get(origen).getAviones().get(ind).setVolando();
                aeropuertos.get(destino).encolar(aeropuertos.get(origen).desencolar(ind));//esto estaba abajo antes
                //break;
                //System.out.println(aeropuertos.get(origen).getAviones().get(ind).getVolando());
            }
            //aeropuertos.get(origen).getAviones().get(ind).setVolando();
            //aeropuertos.get(destino).encolar(aeropuertos.get(origen).desencolar(ind));
        } catch (Exception e) {
        }
        
        
    }
    
    public void moverApasConBoleto(int ci){
        for(int i=0;i<pasajeros.size();i++){
            if(ci==pasajeros.get(i).getCI()){
                pasajerosConBoleto.add(pasajeros.get(i));
                pasajeros.remove(i);}
        }
    }
    public void moverApasajeros(int ci, int destino){
        for(int i=0;i<pasajerosConBoleto.size();i++){
            if(ci==pasajerosConBoleto.get(i).getCI()){
                pasajerosConBoleto.get(i).setLocation(destino);
                pasajeros.add(pasajerosConBoleto.get(i));
                pasajerosConBoleto.remove(i);}
        }
    }
    private boolean existePersona(int ci){
        boolean res=false;
        for(int i=0;i<pasajeros.size();i++){
            if(ci==pasajeros.get(i).getCI()) res=true;
        }
        return res;
    }
    private boolean existeAvion(String placa){
        boolean res=false;
        for(int i=0;i<aeropuertos.size();i++){
            for(int j=0;j<aeropuertos.get(i).getAviones().size();j++){
                if(placa.equals(aeropuertos.get(i).getAviones().get(j).getPlaca())) res=true;
            }
        }
        return res;
    }
    /*public void moverAvion(int id1, int id2){
        aeropuertos.get(id2).encolar(aeropuertos.get(id1).desencolar());
    }*/
    public Itiner getItiner(){return itinerario;}
    /*public void addForItinerario(String origen, String destino, String fecha,Hora hora){
        itinerario.add(origen, destino, fecha, hora);
    }*/
    /*metodos para imprimir y ver*/
    public String printAeropuertos(){
        String res="\nLista de aeropuertos con Aviones:\n";
        for(int i=0;i<aeropuertos.size();i++){
            if(!aeropuertos.get(i).esVacio())
            res=res+(aeropuertos.get(i).mostrarAviones());
        }
        res+="\n";
        return res;
    }
    public String printPasajeros(){
        String res="\nLista de Pasajeros registrados:\n";
        //if(pasajeros.isEmpty()) res+="Ninguno";
        //else{
            for(int i=0;i<pasajeros.size();i++){
                if(!pasajeros.isEmpty())
                res=res+(pasajeros.get(i).toString()+"\n");
            }
            for(int i=0;i<pasajerosConBoleto.size();i++){
                if(!pasajerosConBoleto.isEmpty())
                res=res+(pasajerosConBoleto.get(i).toString()+"\n");
            }
        //}
        res+="\n";
        return res;
    }
    public String printBoletos(){
        String res="\nLista de Boletos Comprados:\n";
        for(int i=0;i<aeropuertos.size();i++){
            if(!aeropuertos.get(i).esVacio())
            res=res+(aeropuertos.get(i).mostrarBoletos());
        }
        res+="\n";
        return res;
    }
    public static void main(String[] args) {
        Pais p= new Pais();
        //se agregan todos los aeropuertos
       p.crearAeropuerto("LaPaz");        //0
       p.crearAeropuerto("Cochabamba");   //1
       p.crearAeropuerto("SantaCruz");    //2
       //p.crearAeropuerto("Beni");
       //p.crearAeropuerto("Pando");
       //p.crearAeropuerto("Tarija");
       
       //se registran los aviones para LaPaz
        p.registrarAvion(0 ,"Boeng","XL12","Pepito",10);
        p.registrarAvion(0 ,"Boeng","CF12","Pepito", 20);
        
        //se registran los aviones para Cochabamba
        p.registrarAvion(1 ,"Boeng","CH12","Pepito",14);
        p.registrarAvion(1 ,"Boeng","RF33","Pepito", 20);
        
        //se registran los aviones para LaPaz
        p.registrarAvion(2 ,"Boeng","JL12","Pepito",12);
        p.registrarAvion(2 ,"Boeng","LK40","Pepito", 20);
        
        //registro los pasajeros
        p.registrarPasajero("Pablo", 778899, 21, "masculino", 0);
        p.registrarPasajero("Juana", 778866, 19, "femenino", 0);
        p.registrarPasajero("Carla", 772299, 21, "femenino", 1);
        p.registrarPasajero("Dani", 668899, 21, "masculino", 1);
        p.registrarPasajero("Javi", 558899, 21, "masculino", 2);
        
        //aqgregar al itinerario
        p.getItiner().add("La Paz", "SantaCruz", "8/2/2015", new Hora("20:44:12"));
        p.comprarBoleto(0,"Pablo", 778899, "8/2/2015", "LaPaz", "SantaCruz", "Normal",20.5, new Hora("20:44:12"));
        //veo los aeropuertos q tiene
        System.out.println(p.printAeropuertos());
        System.out.println(p.printPasajeros());
        System.out.println(p.printBoletos());
        //p.moverAvion(0, 1);
        System.out.println(p.printAeropuertos());
    }
}
