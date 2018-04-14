/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Menu;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import modelo.Pais;

/**
 *
 * @author Ariel
 */
public final class PanelDibujo extends javax.swing.JPanel {

    /**
     * Creates new form PanelDibujo
     */
    
    private Timer t;
    private int h, m;
    private int dia,mes,anio;
    private HiloAvion av0a1,av1a0;//hilo de la paz a cochabamba y viceversa
    private HiloAvion av0a5,av5a0;
    private HiloAvion av0a4,av4a0;
    private HiloAvion av0a6,av6a0;//este era Falla
    private HiloAvion av4a3,av3a4;
    private HiloAvion av3a2,av2a3;
    private HiloAvion av3a1,av1a3;
    private HiloAvion av1a2,av2a1;
    private HiloAvion av1a5,av5a1;
    private HiloAvion av5a6,av6a5;
    private HiloAvion av5a2,av2a5;
    private HiloAvion av2a6,av6a2;
    
    JLabel tiempo, fecha;    
    
    private Main main;
    private Menu r1;
    private Pais pais;
    private AllPositions ap;
    
    private javax.swing.JButton btnAero;
    private Image imagen;
    private ArrayList<Vuelo> vuelos;
    
    //para el monitoreo
    private JInternalFrame monitoreo;
    private JScrollPane sp;
    private DefaultTableModel modelo;
    private JTable table;
    private boolean visibleMonitoreo=false;
    private JTextField cajaSelected;
    private JButton btnCambiar;
    private JComboBox cbDestino;
    
    public void actualizarPersona(){ main.setCantPasajeros(pais.getCantPasajeros());}
    public void actualizarAvion(){main.setCantAviones(pais.getCantAviones());}
    public void actualizarBoleto(){main.setCantBoletos(pais.getCantBoletos());}
    public void setHoraYminInicial(int h, int m){
        this.h=h;
        this.m=m;
    }
    
    public PanelDibujo(Pais pais,Main main) {
        this.pais=pais;
        this.main=main;
        ap=new AllPositions();
        vuelos = new ArrayList<>();
        cargarImagenes();
        this.setLayout(null);
        initComponents();
        t=new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                ++m;
                if(m==60){
                    m = 0;
                    ++h;
                }
                if(h==25){
                    h = 0;
                    ++dia;
                }
                if(dia==getCantDias(mes)+1){
                    dia=1;
                    ++mes;
                }
                if(mes==13){
                    mes=1;
                    ++anio;
                }
                if(anio==16)
                    anio=16;
                actualizarTime();
                iniciarVuelos(fecha.getText());
                arrivarPasajeros();
                actualizarTabla();
            }
        });
    }
    
    private void actualizarTime(){
        String tiempo = (h<=9?"0":"")+h+":"+(m<=9?"0":"")+m;
        this.tiempo.setText(tiempo);
        String f= (dia<=9?"0":"")+dia+"/"+(mes<=9?"0":"")+mes+"/"+(anio<=9?"0":"")+anio;
        fecha.setText(f);
    }
    public void iniciarVuelos(String f){
        //vuelos.clear();
        for(int i=0;i<pais.getAeropuertos().size();i++){
            for(int k=0;k<pais.getAeropuertos().get(i).getAviones().size();k++){
                if(!pais.getAeropuertos().get(i).getAviones().get(k).getVolando()){//si no esta en vuelo el avion
                    int hora=pais.getAeropuertos().get(i).getAviones().get(k).getHoraVuelo()[0];
                    int min=pais.getAeropuertos().get(i).getAviones().get(k).getHoraVuelo()[1];
                    String fecha=pais.getAeropuertos().get(i).getAviones().get(k).getFechaVuelo();
                    if(h==hora && m==min && f.equals(fecha)){
                        
                        pais.getAeropuertos().get(i).getAviones().get(k).setVolando();//ahora el avion vuela
                        String placa= pais.getAeropuertos().get(i).getAviones().get(k).getPlaca();
                        int origen= pais.getAeropuertos().get(i).getAviones().get(k).getOrigenNum();
                        int destino= pais.getAeropuertos().get(i).getAviones().get(k).getDestinoNum();
                        
                        //iniciarVueloCon(origen, destino);
                        vuelos.add(new Vuelo(placa,k,origen, destino,iniciarVueloCon(origen, destino)));
                    }
                }
            }
        }
    }
   
    /*  Para ubicarse
    *   0=La Paz 1=Cochabamba 2=Santa Cruz 3=Trinidad 4=Cobija
        5=Sucre 6=Tarija
    */
    public HiloAvion iniciarVueloCon(int origen, int destino){
        HiloAvion res=null;
        if(origen==0){
            switch(destino){
                case 4: av0a4.iniciarVuelo(); return av0a4; 
                case 1: av0a1.iniciarVuelo(); return av0a1;
                case 5: av0a5.iniciarVuelo(); return av0a5;
                case 6: av0a6.iniciarVuelo(); return av0a6;
            }
        }else if(origen==1){
            switch(destino){
                case 0: av1a0.iniciarVuelo(); return av1a0;
                case 3: av1a3.iniciarVuelo(); return av1a3;
                case 2: av1a2.iniciarVuelo(); return av1a2;
                case 5: av1a5.iniciarVuelo(); return av1a5;
            }
        }else if(origen==2){
            switch(destino){
                case 3: av2a3.iniciarVuelo(); return av2a3;
                case 1: av2a1.iniciarVuelo(); return av2a1;
                case 5: av2a5.iniciarVuelo(); return av2a5;
                case 6: av2a6.iniciarVuelo(); return av2a6;
            }
        }else if(origen==3){
            switch(destino){
                case 1: av3a1.iniciarVuelo(); return av3a1;
                case 2: av3a2.iniciarVuelo(); return av3a2;
                case 4: av3a4.iniciarVuelo(); return av3a4;
            }
        }else if(origen==4){
                switch(destino){
                case 0: av4a0.iniciarVuelo(); return av4a0;
                case 3: av4a3.iniciarVuelo(); return av4a3;
            }
        }else if(origen==5){
            switch(destino){
                case 0: av5a0.iniciarVuelo(); return av5a0;
                case 1: av5a1.iniciarVuelo(); return av5a1;
                case 2: av5a2.iniciarVuelo(); return av5a2;
                case 6: av5a6.iniciarVuelo(); return av5a6;
            }
        }else if(origen==6){
            switch(destino){
                case 0: av6a0.iniciarVuelo(); return av6a0;
                case 5: av6a5.iniciarVuelo(); return av6a5;
                case 2: av6a2.iniciarVuelo(); return av6a2;
            }
        }
        return res;
    }
     public void arrivarPasajeros(){
         //System.out.println("vuelos: "+vuelos.size());
        for(int i=0;i<vuelos.size();i++){
            if(llegoAdestino(vuelos.get(i).getOrigen(), vuelos.get(i).getDestino())){
                pais.moverAvion(vuelos.get(i).getId(),vuelos.get(i).getOrigen(),vuelos.get(i).getDestino());
                actualizarBoleto();
                repaint();
            }
        }
    }
    public boolean llegoAdestino(int origen, int destino){
        boolean res=false;
        if(origen==0){
            switch(destino){
                case 4: res=av0a4.getEnVuelo(); break;
                case 1: res=av0a1.getEnVuelo(); break;
                case 5: res=av0a5.getEnVuelo(); break;
                case 6: res=av0a6.getEnVuelo(); break;
            }
        }else if(origen==1){
            switch(destino){
                case 0: res=av1a0.getEnVuelo(); break;
                case 3: res=av1a3.getEnVuelo(); break;
                case 2: res=av1a2.getEnVuelo(); break;
                case 5: res=av1a5.getEnVuelo(); break;
            }
        }else if(origen==2){
            switch(destino){
                case 3: res=av2a3.getEnVuelo(); break;
                case 1: res=av2a1.getEnVuelo(); break;
                case 5: res=av2a5.getEnVuelo(); break;
                case 6: res=av2a6.getEnVuelo(); break;
            }
        }else if(origen==3){
            switch(destino){
                case 1: res=av3a1.getEnVuelo(); break;
                case 2: res=av3a2.getEnVuelo(); break;
                case 4: res=av3a4.getEnVuelo(); break;
            }
        }else if(origen==4){
                switch(destino){
                case 0: res=av4a0.getEnVuelo(); break;
                case 3: res=av4a3.getEnVuelo(); break;
            }
        }else if(origen==5){
            switch(destino){
                case 0: res=av5a0.getEnVuelo(); break;
                case 1: res=av5a1.getEnVuelo(); break;
                case 2: res=av5a2.getEnVuelo(); break;
                case 6: res=av5a6.getEnVuelo(); break;
            }
        }else if(origen==6){
            switch(destino){
                case 0: res=av6a0.getEnVuelo(); break;
                case 5: res=av6a5.getEnVuelo(); break;
                case 2: res=av6a2.getEnVuelo(); break;
            }
        }
        return res;
    }
    /**/
    public void cargarImagenes(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        imagen=tk.getImage(getClass().getResource("/images/bolivia3g.png"));
    }
    //@Override
    public void paintComponent(Graphics grafico){
        super.paintComponents(grafico);
        Graphics2D g=(Graphics2D)grafico;
        g.drawImage(imagen, 0, 0, imagen.getWidth(this), imagen.getHeight(this), this);
        
        drawEstados(grafico);
        
    }
    public void drawEstados(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Tahoma", 4, 11));
        drawCantAvionesYPasajeros(g, 0, ap.getPosX(0), ap.getPosY(0));
        drawCantAvionesYPasajeros(g, 1, ap.getPosX(1), ap.getPosY(1));
        drawCantAvionesYPasajeros(g, 2, ap.getPosX(2), ap.getPosY(2));
        drawCantAvionesYPasajeros(g, 3, ap.getPosX(3), ap.getPosY(3));
        drawCantAvionesYPasajeros(g, 4, ap.getPosX(4), ap.getPosY(4));
        drawCantAvionesYPasajeros(g, 5, ap.getPosX(5), ap.getPosY(5));
        drawCantAvionesYPasajeros(g, 6, ap.getPosX(6), ap.getPosY(6));
    }
    public void drawCantAvionesYPasajeros(Graphics g,int id, int x, int y){
        g.setColor(Color.white);
        g.setFont(new java.awt.Font("Tahoma", 1, 16));
        g.drawString(""+pais.getAeropuertoSegun(id).getNombre(), x, y-12);
        g.setColor(Color.black);
        g.drawString(""+pais.getAeropuertoSegun(id).getNombre(), x-2, y-11);
        g.setFont(new Font("Tahoma", 4, 11));
        
        g.setColor(new Color(60,60,60));
        g.fillRect(x-5, y-10, 70, 11);
        g.setColor(Color.white);
        g.drawString("Aviones "+pais.getCantAvionesSegunId(id), x, y);
        g.setColor(new Color(60,60,60));
        g.fillRect(x-10, y+40, 80, 11);
        g.setColor(Color.white);
        g.drawString("Pasajeros "+(pais.getPasajerosSegun(id).size()+pais.getPasajerosConBoletoSegun(id).size()), x-5, y+50);
    }
    /**/                       
    private void initComponents() {
        crearBotonAeropuerto("Aeropuerto de La Paz","La Paz", ap.getPosX(0), ap.getPosY(0), 0);
        crearBotonAeropuerto("Aeropuerto de Cochabamba","Cochabamba", ap.getPosX(1), ap.getPosY(1), 1);
        crearBotonAeropuerto("Aeropuerto de Santa Cruz","Santa Cruz", ap.getPosX(2), ap.getPosY(2), 2);
        crearBotonAeropuerto("Aeropuerto de Trinidad","Trinidad", ap.getPosX(3), ap.getPosY(3), 3);
        crearBotonAeropuerto("Aeropuerto de Cobija","Cobija", ap.getPosX(4), ap.getPosY(4), 4);
        crearBotonAeropuerto("Aeropuerto de Sucre","Sucre", ap.getPosX(5), ap.getPosY(5), 5);
        crearBotonAeropuerto("Aeropuerto de Tarija","Tarija", ap.getPosX(6), ap.getPosY(6), 6);
        //cronometro
        tiempo = new JLabel("00:00");
        tiempo.setBounds(470, 100, 150, 26);
        tiempo.setFont(new Font("Arial",Font.BOLD,20));
        //tiempo.setText("00:00");
        add(tiempo);
        //fecha
        fecha = new JLabel(pais.getFecha().replace('.', '/'));
        fecha.setBounds(450, 130, 150, 26);
        fecha.setFont(new Font("Arial",Font.BOLD,20));
        renovarDatos();
        //asignarVuelos();
        add(fecha);
        
        //crear aviones animados con hilos
        av0a5= new HiloAvion("/images/aviones/0a5.png", 0,5);//, 1.0, 0.92);
        add(av0a5,0);
        av5a0= new HiloAvion("/images/aviones/5a0.png", 5,0);//,-1.0,-0.92);
        add(av5a0,0);
        
        av0a1= new HiloAvion("/images/aviones/0a1.png", 0,1);//, 1.0, 0.52);
        add(av0a1,0);
        av1a0= new HiloAvion("/images/aviones/1a0.png", 1,0);//,-1.0,-0.52);
        add(av1a0,0);
        
        av0a4= new HiloAvion("/images/aviones/0a4.png", 0,4);//,-0.15,-1.0);
        add(av0a4,0);
        av4a0= new HiloAvion("/images/aviones/4a0.png", 4,0);//, 0.15, 1.0);
        add(av4a0,0);
        
        /*solo en estos 2 casos puede tener falla*/
        //av0a6= new HiloAvionFalla("/images/aviones/0a6.png", 220, 310, 371, 550, 0.61, 1.0,280,430,345,425, 1.0, 0.15,"/images/aviones/1a2.png");
        av0a6= new HiloAvion("/images/aviones/0a6.png", 0,6);//, 0.61, 1.0);
        add(av0a6,0);
        //av6a0= new HiloAvionFalla("/images/aviones/6a0.png", 371, 550, 220, 310,-0.61,-1.0,280,430,345,425, 1.0, 0.15,"/images/aviones/1a2.png");
        av6a0= new HiloAvion("/images/aviones/6a0.png", 6,0);//,-0.61,-1.0);
        add(av6a0,0);
        
        av4a3= new HiloAvion("/images/aviones/0a5.png", 4,3);//, 1.0, 0.9);
        add(av4a3,0);
        av3a4= new HiloAvion("/images/aviones/5a0.png", 3,4);//,-1.0,-0.9);
        add(av3a4,0);
        
        av3a2= new HiloAvion("/images/aviones/3a2.png", 3,2);//, 0.43, 1.0);
        add(av3a2,0);
        av2a3= new HiloAvion("/images/aviones/2a3.png", 2,3);//,-0.43,-1.0);
        add(av2a3,0);
        
        av3a1= new HiloAvion("/images/aviones/3a1.png", 3,1);//, -0.59, 1.0);//ll
        add(av3a1,0);
        av1a3= new HiloAvion("/images/aviones/1a3.png", 1,3);//, 0.59, -1.0);
        add(av1a3,0);
        
        av1a2= new HiloAvion("/images/aviones/1a2.png", 1,2);//, 1.0, 0.15);
        add(av1a2,0);
        av2a1= new HiloAvion("/images/aviones/2a1.png", 2,1);//,-1.0,-0.15);
        add(av2a1,0);
        
        av1a5= new HiloAvion("/images/aviones/3a2.png", 1,5);//, 0.6, 1.0);
        add(av1a5,0);
        av5a1= new HiloAvion("/images/aviones/2a3.png", 5,1);//,-0.6,-1.0);
        add(av5a1,0);
        
        av5a6= new HiloAvion("/images/aviones/4a0.png", 5,6);//, 0.21, 1.0);
        add(av5a6,0);
        av6a5= new HiloAvion("/images/aviones/0a4.png", 6,5);//,-0.21,-1.0);
        add(av6a5,0);
        
        av5a2= new HiloAvion("/images/aviones/0a3.png", 5,2);//, 1.0,-0.69);
        add(av5a2,0);
        av2a5= new HiloAvion("/images/aviones/3a0.png", 2,5);//,-1.0, 0.69);
        add(av2a5,0);
        
        av2a6= new HiloAvion("/images/aviones/2a6.png", 2,6);//,-0.33, 1.0);
        add(av2a6,0);
        av6a2= new HiloAvion("/images/aviones/6a2.png", 6,2);//, 0.33,-1.0);
        add(av6a2,0);
        
        //monitoreo
        monitoreo= new JInternalFrame("Monitoreo");
        monitoreo.setLayout(null);
        monitoreo.setSize(308, 646);
        monitoreo.setLocation(585, 0);
        
        cajaSelected= new JTextField();
        cajaSelected.setBounds(5, 0, 70, 25);
        cajaSelected.setEditable(false);
        monitoreo.add(cajaSelected);
        
        cbDestino=new JComboBox();
        cbDestino.setBounds(80, 0, 120, 25);
        cbDestino.addItem("La Paz");
        cbDestino.addItem("Cochabamba");
        cbDestino.addItem("SantaCruz");
        cbDestino.addItem("Trinidad");
        cbDestino.addItem("Cobija");
        cbDestino.addItem("Sucre");
        cbDestino.addItem("Tarija");
        monitoreo.add(cbDestino);
        
        btnCambiar=new JButton("Cambiar");
        btnCambiar.setBounds(205, 0, 85, 25);
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarDestino();
            }
        });
        monitoreo.add(btnCambiar);
        
        sp = new javax.swing.JScrollPane();
        actualizarTabla();
        sp.setBounds(0, 25, 302, 590);
        monitoreo.add(sp);
        /**/
        
        add(monitoreo,0);
    }// </editor-fold
    
    public void actualizarTabla(){
        table= new JTable();
        String cabecera[]={"Placa", "Vol.", "Pas.","Origen","Destino"};
        String datos[][]={};
        modelo = new DefaultTableModel(datos, cabecera);
        table.setModel(modelo);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cajaSelected.setText(modelo.getValueAt(table.getSelectedRow(), 0).toString());
            }
        });
        sp.setViewportView(table);
        
        int anchos [] = {62,30,40,85,85};
        for(int i=0; i<anchos.length; i++){
        	table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        /**/
        for(int i=0;i<pais.getAeropuertos().size();i++){
            for(int j=0;j<pais.getAeropuertos().get(i).getAviones().size();j++){
                modelo.addRow(pais.getAeropuertos().get(i).getAviones().get(j).getForMonitoreo());
            }
        }
    }
    public void cambiarDestino(){
        int destinoNum=cbDestino.getSelectedIndex();
        String destino=cbDestino.getSelectedItem().toString();
        for(int i=0;i<pais.getAeropuertos().size();i++){
            for(int j=0;j<pais.getAeropuertos().get(i).getAviones().size();j++){
                if(pais.getAeropuertos().get(i).getAviones().get(j).getPlaca().equals(cajaSelected.getText())//){
                        &&pais.getAeropuertos().get(i).getAviones().get(j).getVolando()){
                    pais.getAeropuertos().get(i).getAviones().get(j).setDestino(destino);
                    pais.getAeropuertos().get(i).getAviones().get(j).setDestinoNun(destinoNum);
                    actualizarTabla();
                }
            }
        }
        for (int i = 0; i < vuelos.size(); i++) {
            if(vuelos.get(i).getPlaca().equals(cajaSelected.getText())){
                    //&&vuelos.get(i).getHiloDeVuelo().getEnVuelo()){
                vuelos.get(i).setDestino(destinoNum);
                vuelos.get(i).getHiloDeVuelo().setDestino(destinoNum);
            }    
        }
    }
    public boolean verMonitoreo(){
        if(visibleMonitoreo){
            monitoreo.setVisible(false);
            visibleMonitoreo=false;    
        }
        else if(!visibleMonitoreo){
            monitoreo.setVisible(true);
            visibleMonitoreo=true;
        }
        return visibleMonitoreo;
    }
    public void simular(){
        vuelos.clear();
        t.start();
    
    }
    public void pararSimulacion(){
        t.stop();
    }
    
    /**/
    public void renovarDatos(){
        String fi=pais.getFecha().replace('.', ' ');
        //System.out.println(pais.getFecha());
        String da[]= fi.split(" ");
        try {
            dia=Integer.parseInt(da[0]);
            mes=Integer.parseInt(da[1]);
        } catch (Exception e) {
            dia=Integer.parseInt(da[0].replace("0", ""));
            mes=Integer.parseInt(da[1].replace("0", ""));
        }
        anio=Integer.parseInt(da[2]);
        //System.out.println(pais.getFecha());
        fecha.setText(pais.getFecha().replace('.', '/'));
        actualizarTime();
    }
    
    private void crearBotonAeropuerto(String nombre, String nombreCorto,int x, int y,int id){
        pais.crearAeropuerto(nombreCorto);
        btnAero = new javax.swing.JButton();
        btnAero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aeropu.png"))); // NOI18N
        btnAero.setToolTipText(nombre);
        btnAero.setBorder(null);
        btnAero.setBorderPainted(false);
        btnAero.setContentAreaFilled(false);
        btnAero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        btnAero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switch (id){
                    case 0: btnRegistro1ActionPerformed(evt,id, nombreCorto, x-50, y-40); break;
                    case 1: btnRegistro1ActionPerformed(evt,id, nombreCorto, x-50, y-40); break;
                    case 2: btnRegistro1ActionPerformed(evt,id, nombreCorto, x-50, y-40); break;
                    case 3: btnRegistro1ActionPerformed(evt,id, nombreCorto, x-50, y-40); break;
                    case 4: btnRegistro1ActionPerformed(evt,id, nombreCorto, x-50, y-40); break;
                    case 5: btnRegistro1ActionPerformed(evt,id, nombreCorto, x-50, y-95); break;
                    case 6: btnRegistro1ActionPerformed(evt,id, nombreCorto, x-50, y-220); break;
                }
                    
            }
        });
        
        btnAero.setBounds(x, y, 45, 45);
        add(btnAero);
    }
    private void btnRegistro1ActionPerformed(java.awt.event.ActionEvent evt, int id, String nC, int x, int y) {  
        r1= new Menu(id,nC,this);
        this.add(r1,0);
        r1.setLocation(x, y);
        r1.setResizable(false);
        r1.setVisible(true);
    }
    public int getCantDias(int mes){
        int res=0;
        switch(mes){
            case 1: res=31 ;break;//desde 2016
            case 2: res=29 ;break;
            case 3: res=31 ;break;
            case 4: res=30 ;break;
            case 5: res=31 ;break;
            case 6: res=30 ;break;
            case 7: res=31 ;break;
            case 8: res=31 ;break;//fin
            case 9: res=30 ;break;//desde 2015
            case 10:res=31 ;break;
            case 11:res=30 ;break;
            case 12:res=31 ;break;
        }
        
        return res;
    }
    public Point getUbicacion(){
        return r1.getLocation();
    }
    public Pais getPais(){return pais;}
    //public void setFecha(String fec){ fecha.setText(fec);}
    public int getHora(){return h;}
    public int getMin(){return m;}
    public void setHora(int h, int m){this.h=h;this.m=m;actualizarTime();}
   
}

