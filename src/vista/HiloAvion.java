/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Ariel
 */
public class HiloAvion extends JLabel{
    private double xIni,yIni;
    private double xFin, yFin;
    private double xAux,yAux;
    Timer tVuelo;
    double incrementoX, incrementoY;
    private int sentido;// 0=SurEste 1=NorOste 2=NorEste 3=SurOeste 
    private int LOST=5;
    private int enVuelo;//-1=nunca volo 1=en vuelo 0=paro vuelo
    private AllPositions ap= new AllPositions();
    
    public HiloAvion(String dir, int idInicio, int idFin){
            //double incrementoX, double incrementoY){
        
        setIcon(new ImageIcon(getClass().getResource(dir)));
        this.xIni=ap.getPosX(idInicio); 
        this.yIni=ap.getPosY(idInicio);
        this.xFin=ap.getPosX(idFin);
        this.yFin=ap.getPosY(idFin);
        this.xAux=ap.getPosX(idInicio);
        this.yAux=ap.getPosY(idInicio);
        //this.incrementoX=incrementoX;
        //this.incrementoY=incrementoY;
        enVuelo=-1;
        asignarSentido();
        asignarIncremento();
        //System.out.println(idInicio+"-"+idFin+" sentido:"+sentido);
        setBounds(Math.round((float) xAux), Math.round((float)yAux), 45, 45);
        setVisible(false);
        tVuelo=new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                if(sentido==0){
                    if(xAux<xFin&&yAux<yFin){
                        avanzar();//System.out.println("xAux="+xAux+" yAux="+yAux);
                    }else
                        parar();
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
    private void asignarIncremento(){
        double restaX=(float) (xAux-xFin);
        double restaY=(float) (yAux-yFin);
        double base=restaX;
        if(base>=0) base=base+1;
        else        base=base-1;
        
        double valor=base/((restaY/restaX)*base);
        valor=Math.abs(valor);
        incrementoX=valor; incrementoY=1.0;
        if(valor>=1){
            incrementoX=1.0; incrementoY=Math.pow(valor, -1.0);
        }
        //asginamos signo en X y Y
        double signoX=1, signoY=1;
        if(sentido==0){  
            signoX=1; signoY=1;
        }else if(sentido==1){
            signoX=-1; signoY=-1;
        }else if(sentido==2){
            signoX=1; signoY=-1;
        }else if(sentido==3){
            signoX=-1; signoY=1;
        } 
        //finalmente
        incrementoX=incrementoX*signoX;
        incrementoY=incrementoY*signoY;
        //System.out.println("x:"+incrementoX+" y:"+incrementoY);
    }
    
    private void avanzar(){
        xAux+=incrementoX;yAux+=incrementoY;
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
    public boolean getEnVuelo(){
        boolean res=false;
        if(enVuelo==0)
            res=true;
        return res;
    }
    public void setDestino(int idDestino){
        xIni=xAux;
        yIni=yAux;
        xFin=ap.getPosX(idDestino);
        yFin=ap.getPosY(idDestino);
        asignarSentido();
        asignarIncremento();
    }
}
