/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sorteoligamvc;

import deber.edu.ups.controlador.ControladorTorneo;
import deber.edu.ups.modelo.Torneo;
import deber.edu.ups.vista.VistaTorneo;

/**
 *
 * @author Steeve
 */
public class SorteoLigaMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Torneo modelo = new Torneo("Liga Pro Ecuador 2026");
        VistaTorneo vista = new VistaTorneo();
        
        ControladorTorneo controlador = new ControladorTorneo(modelo, vista);
        controlador.iniciar();
    }
    
}
