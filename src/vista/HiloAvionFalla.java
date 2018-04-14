/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import modelo.Avion;

/**
 *
 * @author Ariel
 */
public class HiloAvionFalla extends JLabel{
    private double xIni,yIni;
    private double xFin, yFin;
    private double xAux,yAux;
    private double xFalla, yFalla;
    Timer tVuelo;
    double incrementX, incrementY;
    private int sentido;// 0=se 1=no 3=ne 4=no
    private int LOST=5;
    private int enVuelo;//-1=nunca volo 1=en vuelo 0=paro vuelo
    
    private ArrayList<Vuelo> vuelos;
    
    private int probabilidadDeFallo=90;//en porcentaje max 100
    private static Random random = new Random();
    
    public HiloAvionFalla(/*ArrayList<Vuelo> vuelos ,*/String dir, int xInicio, int yInicio,int xFinal,int yFinal,
            double incrementoX, double incrementoY, double xFalla, double yFala,
            double xSetFinal,double ySetFinal,double incrementoXfalla,double incrementoYfalla,String dir2){
        //this.vuelos=vuelos;
        setIcon(new ImageIcon(getClass().getResource(dir)));
        this.xIni=xInicio; 
        this.yIni=yInicio;
        this.xFin=xFinal;
        this.yFin=yFinal;
        this.xAux=xInicio;
        this.yAux=yInicio;
        this.incrementX=incrementoX;
        this.incrementY=incrementoY;
        this.xFalla=xFalla;
        this.yFalla=yFala;
        
        enVuelo=-1;
        asignarSentido();
        setBounds(Math.round((float) xAux), Math.round((float)yAux), 45, 45);
        setVisible(false);
        
        int prob = random.nextInt(100)+1;//aqui controlo q lo aleatorio me salga de 0 a 100
        //System.out.println(prob);
        tVuelo=new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                if(sentido==0){
                    if(prob<=probabilidadDeFallo){
                        if(xAux>=xFalla&&yAux>=yFala){
                            xFin=xSetFinal; yFin=ySetFinal;
                            incrementX=incrementoXfalla; incrementY=incrementoYfalla;
                            setIcon(new ImageIcon(getClass().getResource(dir2)));
                            asignarSentido();
                        }else{    
                            if(xAux<xFin&&yAux<yFin){
                                avanzar();//System.out.println("xAux="+xAux+" yAux="+yAux);
                            }else
                                parar();
                        }
                    }else{
                        if(xAux<xFin&&yAux<yFin){
                            avanzar();//System.out.println("xAux="+xAux+" yAux="+yAux);
                        }else
                            parar();
                    }
                }else if(sentido==1){
                    if(xAux>xFin&&yAux>yFin)
                        avanzar();
                    else
                        parar();
                }else if(sentido==2){
                    if(xAux<xFin&&yAux>yFin)
                        avanzar();
                    else
                        parar();
                }else if(sentido==3){
                    if(xAux>xFin&&yAux<yFin)
                        avanzar();
                    else
                        parar();
                }
            }
        });
    }
    private void asignarSentido(){
        if(xAux<xFin&&yAux<yFin)        sentido=0;
        else if(xAux>xFin&&yAux>yFin)   sentido=1;
        else if(xAux<xFin&&yAux>yFin)   sentido=2;
        else if(xAux>xFin&&yAux<yFin)   sentido=3;
    }
    private void avanzar(){
        xAux+=incrementX;yAux+=incrementY;
        setLocation(Math.round((float) xAux), Math.round((float)yAux));
    }
    private void parar(){
        tVuelo.stop();
        setLocation(Math.round((float) xAux), Math.round((float)yAux));
        setVisible(false);
        enVuelo=0;
    }
    public void iniciarVuelo(){
        if(sentido==0){
            if(xAux+LOST>=xFin&&yAux+LOST>=yFin)
                reiniciar();
            else
                iniciar();
        }else if(sentido==1){
            if(xAux-LOST<=xFin&&yAux-LOST<=yFin)
                reiniciar();
            else    
                iniciar();
        }else if(sentido==2){
            if(xAux+LOST>=xFin&&yAux-LOST<=yFin)
                reiniciar();
            else    
                iniciar();
        }else if(sentido==3){
            if(xAux-LOST<=xFin&&yAux+LOST>=yFin)
                reiniciar();
            else    
                iniciar();
        }
    }
    private void iniciar(){
        tVuelo.start();
        setVisible(true);
        enVuelo=1;
    }
    private void reiniciar(){
        xAux=xIni;yAux=yIni;
        tVuelo.restart();
        setVisible(true);
        enVuelo=1;
    }
    public double obtenerX(){return xIni;}
    public double obtenerY(){return yIni;}
    public void setXandY(int x, int y){
        this.xIni=x;
        this.yIni=y;
    }
    public boolean getEnVuelo(){
        boolean res=false;
        if(enVuelo==0)
            res=true;
        return res;
    }
}
