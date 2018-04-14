/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import controlador.Menu;
import controlador.CambiarFecha;
import controlador.Itinerario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JMenuItem;
import modelo.Archivo;
import modelo.Pais;
import modelo.PaisGuardar;


/**
 *
 * @author Galvez
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form main
     */
    Pais pais;
    Archivo archivo;
    volatile boolean abrir = false;

    public Main() {
        pais = new Pais();
        archivo = new Archivo(this); 

        initComponents();
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        toolBar = new javax.swing.JMenuBar();
        /**/
        menuArchivo = new javax.swing.JMenu();
        menuAbrir = new javax.swing.JMenuItem();
        menuGuardar = new javax.swing.JMenuItem();
        /**/
        menuSimulacion= new javax.swing.JMenu();
        menuSimular = new javax.swing.JMenuItem();
        menuDetenerS= new javax.swing.JMenuItem();
        
        menuFecha = new javax.swing.JMenu();
        cambiarFecha = new javax.swing.JMenu();
        
        menuMonitoreo = new javax.swing.JMenu();
        verMonitoreo = new javax.swing.JMenu();
        
        menuItinerario = new javax.swing.JMenu();
        verItinerario = new javax.swing.JMenu();
        
        menuItinerario = new javax.swing.JMenu();
        verItinerario = new javax.swing.JMenu();
        /**/
        panel = new PanelDibujo(pais, this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        setTitle("Simulador de Vuelos v.2.0");
        setPreferredSize(new java.awt.Dimension(900, 700));
        getContentPane().setLayout(null);

        //toolBar.setRollover(true);
        /**/
        menuArchivo.setText("Archivo");

        menuAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuAbrir.setText("Abrir");
        menuAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //Main.this.jMenuItem3ActionPerformed(evt);
                try
                {
                    abrirArchivo();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        });
        menuArchivo.add(menuAbrir);

        menuGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        menuGuardar.setText("Guardar");
        menuGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //Main.this.jMenuItem4ActionPerformed(evt);
                guardarArchivo();
            }
        });
        menuArchivo.add(menuGuardar);
        toolBar.add(menuArchivo);
        /**/
        menuSimular.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuSimular.setText("Simular");
        menuSimular.setFocusable(false);
        menuSimular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuSimular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuSimular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimularActionPerformed(evt);
            }
        });
        menuDetenerS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menuDetenerS.setText("Detener");
        menuDetenerS.setFocusable(false);
        menuDetenerS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuDetenerS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuDetenerS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.pararSimulacion();
            }
        });
        menuSimulacion.setText("Simulcion");
        menuSimulacion.add(menuSimular);
        menuSimulacion.add(menuDetenerS);
        toolBar.add(menuSimulacion);
        //toolBar.add(jSeparator1);
        cantAviones= new javax.swing.JMenu("Cantidad de Aviones 0  ");
        verAviones= new JMenuItem("Ver Aviones registrados");
        verAviones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TablaAviones tabla= new TablaAviones(panel.getPais().getAeropuertos());
                tabla.setLocationRelativeTo(null);
                tabla.setVisible(true);
            }
        });
        cantAviones.add(verAviones);
        toolBar.add(cantAviones);
        //toolBar.add(jSeparator2);
        cantPasajeros= new javax.swing.JMenu("Cantidad de Personas 0  ");
        verPasajeros= new JMenuItem("Ver Personas registradas");
        verPasajeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TablaPersonas tabla= new TablaPersonas(panel.getPais().getPasajeros(),panel.getPais().getPasajerosConBoleto());
                tabla.setLocationRelativeTo(null);
                tabla.setVisible(true);
            }
        });
        cantPasajeros.add(verPasajeros);
        toolBar.add(cantPasajeros);
        cantBoletosVendidos=new javax.swing.JMenu("Cantidad de Boletos vendidos 0  ");
        verBoletosVendidos= new JMenuItem("Ver Boletos Vendidos");
        verBoletosVendidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TablaBoletos tabla= new TablaBoletos(panel.getPais().getBoletos());
                tabla.setLocationRelativeTo(null);
                tabla.setVisible(true);
            }
        });
        cantBoletosVendidos.add(verBoletosVendidos);
        toolBar.add(cantBoletosVendidos);
        /**/
        menuFecha=new javax.swing.JMenu("Configuraraciones ");
        cambiarFecha= new JMenuItem("Cambiar Fecha y Hora");
        cambiarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambiarFecha cF= new CambiarFecha(pais.getFecha(),panel);
                cF.setLocation(550, 0);
                cF.setResizable(false);
                
                panel.add(cF,0);
                cF.setVisible(true);
            }
        });
        menuFecha.add(cambiarFecha);
        toolBar.add(menuFecha);
        /**/
        
        menuMonitoreo=new javax.swing.JMenu("Monitoreo");
        verMonitoreo= new JMenuItem("Ver");
        verMonitoreo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        verMonitoreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(panel.verMonitoreo())
                    verMonitoreo.setText("Ocultar");
                else
                    verMonitoreo.setText("Ver");
            }
        });
        menuMonitoreo.add(verMonitoreo);
        toolBar.add(menuMonitoreo);
        /**/
        menuItinerario=new javax.swing.JMenu("Itinerario");
        verItinerario= new JMenuItem("Ver");
        verItinerario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Itinerario itinerario= new Itinerario(pais);
                itinerario.setLocationRelativeTo(null);
                itinerario.setVisible(true);
            }
        });
        menuItinerario.add(verItinerario);
        toolBar.add(menuItinerario);
        /**/
        menuConsulta=new javax.swing.JMenu("Consulta");
        verConsulta= new JMenuItem("Preguntar");
        verConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Consultor consultor=new Consultor(pais);
                consultor.setLocationRelativeTo(null);
                consultor.setVisible(true);
            }
        });
        menuConsulta.add(verConsulta);
        toolBar.add(menuConsulta);
        /***/
        getContentPane().add(toolBar);
        toolBar.setBounds(0, 0, 900, 25);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );

        getContentPane().add(panel);
        panel.setBounds(0, 26, 900, 650);

        pack();
    }// </editor-fold>  
    
    public void abrirArchivo() throws IOException { this.abrir = true;
        PaisGuardar paisGuardado = this.archivo.abrirArchivo();
        if(paisGuardado==null){return;}
        panel.getPais().setDatos(paisGuardado.getAeropuertos(), paisGuardado.getPasajeros(),
                paisGuardado.getPasajerosConBoleto(),paisGuardado.getFecha(),paisGuardado.getItinerario());
        setCantPasajeros(panel.getPais().getCantPasajeros());
        setCantAviones(panel.getPais().getCantAviones());
        setCantBoletos(panel.getPais().getCantBoletos());
        //panel.setFecha(paisGuardado.getFecha());
        panel.renovarDatos();//es
        panel.setHora(paisGuardado.getHora(),paisGuardado.getMin());
        panel.actualizarTabla();
        panel.repaint();
        this.abrir = false; 
    }
               
    private void guardarArchivo() { 
        this.archivo.guardarArchivo(pais.getAeropuertos(),pais.getPasajeros(),
                pais.getPasajerosConBoleto(),pais.getFecha(),panel.getHora(),panel.getMin(),pais.getItiner()); 
    }
    private void btnSimularActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        panel.simular();
    }
    
    public void setCantPasajeros(int cantPerson){
        //cantPerson++;
        cantPasajeros.setText("Cantidad de Personas "+cantPerson);
    }
    //int cantAvion=0;
    public void setCantAviones(int cantAvion){
        //cantAvion++;
        cantAviones.setText("Cantidad de Aviones "+cantAvion);
    }
    //int cantBoletos=0;
    public void setCantBoletos(int cantBoletos){
        //cantBoletos++;
        cantBoletosVendidos.setText("Cantidad de Boletos Vendidos "+cantBoletos);
    }
    /**
     * metodo main
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        //</editor-fold> 
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main m= new Main();
                m.setLocationRelativeTo(null);
                m.setResizable(false);
                m.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JMenu menuSimulacion;
    private javax.swing.JMenuItem menuSimular;
    private javax.swing.JMenuItem menuDetenerS;
    
    private javax.swing.JMenu cantPasajeros;
    private javax.swing.JMenuItem verPasajeros;
    private javax.swing.JMenu cantAviones;
    private javax.swing.JMenuItem verAviones;
    private javax.swing.JMenu cantBoletosVendidos;
    private javax.swing.JMenuItem verBoletosVendidos;
    
    private javax.swing.JMenu menuFecha;
    private javax.swing.JMenuItem cambiarFecha;
    
    private javax.swing.JMenu menuMonitoreo;
    private javax.swing.JMenuItem verMonitoreo;
    
    private javax.swing.JMenu menuItinerario;
    private javax.swing.JMenuItem verItinerario;
    
    private javax.swing.JMenu menuConsulta;
    private javax.swing.JMenuItem verConsulta;
    
    private PanelDibujo panel;
    private javax.swing.JMenuBar toolBar;
    // End of variables declaration      
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuItem menuAbrir;
    private javax.swing.JMenuItem menuGuardar;
}
