/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Ariel
 */
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
/*     */ 
public class Archivo
{
    JFrame padre;
    private static FileNameExtensionFilter filtroI = new FileNameExtensionFilter("Archivos tipo imagenes", 
            new String[] { "png", "PNG", "jpg", "JPG", "gif", "GIF", "jpeg", "JPEG" });
    
    public Archivo(JFrame padre)
    {
        this.padre = padre;
    }
/*     */   
    public Image abrirImagen() {
        JFileChooser fileChooser = new JFileChooser();
        Image imagen = null;
        try {
            fileChooser.addChoosableFileFilter(filtroI);
            int rpta = fileChooser.showOpenDialog(this.padre);
            if (rpta == 0) {
                imagen = Toolkit.getDefaultToolkit().getImage(fileChooser.getSelectedFile() + "");
            }
        }
        catch (Exception e) {}
/*     */     
        return imagen;
    }
/*     */   
    public PaisGuardar abrirArchivo() {
        File archivoA = getFile(0);
        PaisGuardar guardarP = null;
        try {
            if (archivoA != null) {
                FileInputStream fis = new FileInputStream(archivoA);
                ObjectInputStream ois = new ObjectInputStream(fis);
                guardarP = (PaisGuardar)ois.readObject();
                ois.close();
                fis.close();
            }
        }
        catch (Exception e) {}
        return guardarP;
    }
/*     */   
    public void guardarArchivo(ArrayList<Aeropuerto> aeropuertos, ArrayList<Pasajero> pasajeros
            ,ArrayList<Pasajero> pasajerosConBoleto,String fecha, int hora, int min, Itiner itinerario) { 
        File archivoG = getFile(1);
        PaisGuardar guardarG = new PaisGuardar(aeropuertos, pasajeros,pasajerosConBoleto,fecha,hora,min,itinerario);
        if (archivoG != null) {
            String fileSalida = archivoG.getAbsolutePath();
            if (!fileSalida.toUpperCase().endsWith(".APA")) {
                fileSalida = fileSalida + ".APA";
            }
            try {
                FileOutputStream fos = new FileOutputStream(fileSalida);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(guardarG);
                oos.flush();
                oos.close();
                fos.close();
            } catch (Exception e) {}
        }
    }
/*     */   
    public File getFile(int n) {
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filtro = new FileFilter(){
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                if (file.getAbsolutePath().toUpperCase().endsWith("APA")) {
                    return true;
                }
                    return false;
            }
/*     */       
            public String getDescription(){
                return "Proyectos (.APA)";
            }
        };
        fileChooser.setFileFilter(filtro);
        switch (n) {
            case 0: fileChooser.showOpenDialog(this.padre); break;
            case 1: fileChooser.showSaveDialog(this.padre);
        }
/*     */     
        return fileChooser.getSelectedFile();
    }
}
