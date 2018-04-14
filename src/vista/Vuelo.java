/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.JButton;
import javax.swing.text.html.HTMLDocument;
import vista.HiloAvion;

/**
 *
 * @author Ariel
 */
public class Vuelo {
    private String placa;
    private int origen;
    private int destino;
    private int ind;//hace referencia a la posicion del avion
    private HiloAvion hiloDeVuelo;
    
    public Vuelo(String placa,int ind, int origen, int destino, HiloAvion hiloDeVuelo){
        this.placa=placa;
        this.ind=ind;
        this.origen=origen;
        this.destino=destino;
        this.hiloDeVuelo=hiloDeVuelo;
    }
    public String getPlaca(){return placa;}
    public int getId(){return ind;}
    public int getOrigen(){return origen;}
    public int getDestino(){return destino;}
    public HiloAvion getHiloDeVuelo(){return hiloDeVuelo;}
    
    public void setDestino(int destino){this.destino=destino;}
}
