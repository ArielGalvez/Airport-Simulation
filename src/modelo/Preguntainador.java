/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Dialogo modal que sirve de ventana de captura de datos.<br>
 * Contiene un JTextField en el que escribimos un texto y pulsando enter después
 * de escribir en el, la ventana se cierra.
 * 
 * @author Chuidiang
 * 
 */
public class Preguntainador extends JDialog {
    private JLabel areaPregunta;
    private JButton btnAceptar, btnCancelar;
    private ButtonGroup grupo;
    private JRadioButton[] opciones;
    private String respuesta;
    private JPanel panelPrincipal, panelImagen, panelPreguntas, panelBotones;
    private JLabel imagen;
    /**
     * Constructor que pone titulo al dialogo, construye la ventana y la hace
     * modal.
     * 
     * @param padre
     *            Frame que hace de padre de esta dialogo.
     */
    public Preguntainador(Frame padre, String pregunta, int img, String explicacion, Object[] datos) {
        super(padre,true);// esto es re importante para lo que querias
        setTitle("Pregunta");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        
        areaPregunta = new JLabel(pregunta);
        areaPregunta.setFont(new java.awt.Font("Tahoma", 1, 12));
        JPanel panelAux= new JPanel(new FlowLayout());
        panelAux.add(areaPregunta);
        add(panelAux);
        
        panelPrincipal= new JPanel(new GridLayout(1, 2, 5, 5));
                
        
        panelImagen=new JPanel(new BorderLayout());
        imagen=new JLabel(new javax.swing.ImageIcon(getClass().getResource("/images/preguntas/"+img+".png")));
        panelImagen.add(imagen, BorderLayout.CENTER);
        JLabel infoExplicacion= new JLabel(new javax.swing.ImageIcon(getClass().getResource("/images/nosotros.png")));
        infoExplicacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        infoExplicacion.setToolTipText(explicacion);
        panelImagen.add(infoExplicacion, BorderLayout.PAGE_START);
        panelPrincipal.add(panelImagen);
        //
        panelPreguntas= new JPanel(new GridLayout(datos.length, 1, 5, 5));
        grupo = new ButtonGroup();
        opciones= new JRadioButton[datos.length];
        for (int i = 0; i < datos.length; i++) {
            opciones[i]= new JRadioButton(datos[i].toString());
            grupo.add(opciones[i]);
            panelPreguntas.add(opciones[i]);
        }
        opciones[0].setSelected(true);//para marcar el primero
        panelPrincipal.add(panelPreguntas);
        add(panelPrincipal);
        /**/
        panelBotones = new JPanel(new FlowLayout());
        // Se oculta la ventana al pulsar <enter> sobre el textfield
        btnAceptar = new JButton(" Aceptar");
        //btnAceptar.setSize();
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                marcarRespuesta();
                setVisible(false);
            }
        });
        panelBotones.add(btnAceptar);

        btnCancelar = new JButton("Cancelar");
        //btnAceptar.setSize();
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });
        panelBotones.add(btnCancelar);
        
        add(panelBotones);
  
    }
    
    private void marcarRespuesta(){
        for (int i = 0; i < opciones.length; i++) {
            if(opciones[i].isSelected()){
                respuesta=opciones[i].getText();
            }
        }
    }
    /**
     * Deveulve el texto en el jtextfield
     * 
     * @return el texto
     */
    public String getText() {
        return respuesta;
    }
}
