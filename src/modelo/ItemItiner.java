/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Ariel
 */
public class ItemItiner implements Serializable{
    private String origen;
    private String destino;
    private String fecha;
    private String hora;
    
    public ItemItiner(String origen,String destino, String fecha, Hora hora){
        this.origen=origen;
        this.destino=destino;
        this.fecha=fecha;
        String horaModif=hora.getHoraForVuelo();
        this.hora=horaModif;
    }
    public String getOrigen(){return origen;}
    public String getDestino(){return destino;}
    public String getFecha(){return fecha;}
    public String getHora(){return hora;}
    
    public Object[] getForTable(){
        return new Object[]{origen,destino,fecha,hora};
    }
    public void setFechaYhora(String fecha, String hora){this.fecha=fecha;this.hora=hora;}
    //public void setHora(String hora){this.hora=hora;}
}
