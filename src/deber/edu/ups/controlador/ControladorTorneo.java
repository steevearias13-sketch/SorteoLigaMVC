/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deber.edu.ups.controlador;

import deber.edu.ups.excepciones.NumeroEquiposInvalidoException;
import deber.edu.ups.excepciones.SorteoInvalidoException;
import deber.edu.ups.modelo.Equipo;
import deber.edu.ups.modelo.Torneo;
import deber.edu.ups.vista.VistaTorneo;
import java.io.IOException;

/**
 *
 * @author Steeve
 */
public class ControladorTorneo {
    private Torneo torneo;
    private VistaTorneo vista;
    private final String RUTA_ARCHIVO = "torneo.ser";
    
    public ControladorTorneo(Torneo torneo, VistaTorneo vista){
        this.torneo = torneo;
        this.vista = vista;   
    }
    
    public void iniciar(){
        boolean salir = false;
        while(!salir){
            int opcion = vista.mostrarMenu();
            switch(opcion){
                case 1 -> registrarEquiposPorDefecto();
                case 2 -> ejecutarSorteo();
                case 3 -> vista.mostrarPartidos(torneo.getPartido(), "Octavos");
                case 4 -> guardarTorneo();
                case 5 -> cargarTorneo();
                case 6 -> {
                    vista.mostrarMensaje("¡Gracias por usar el sistema de sorteos!");
                    salir = true;
                }
                default -> vista.mostrarError("Opcion invalida. Intente de nuevo");
            }
        }
    }
    
    private void registrarEquiposPorDefecto(){
        torneo.getEquipos().clear();
        String[] nombres = {
            "Liga de Quito", "Barcelona SC", "Emelec", "Independiente del Valle", "El Nacional", "Delfin SC", "Universidad Catolica", "Aucas", "Maraca", "Tecnico Universitario", "Orense SC", "Deportivo Cuenca", "Mushuc Runa", "Cumbaya FC", "Libertad FC", "Imbabura SC"
        };
        
        for(int i = 0; i < nombres.length; i++){
            torneo.agregarEquipo(new Equipo(i + 1, nombres[i]));
        }
        vista.mostrarMensaje("Se han registrado con exito los 16 equipos profesionales.");
    }
    
    private void ejecutarSorteo(){
        try{
            String etapa = "Octavos";
            // Limpiar sorteos anteriores
            torneo.getPartido().clear();
            torneo.sortearEtapa(etapa);
            vista.mostrarMensaje("Sorteo recursivo completado exitosamente para la etapa: " + etapa);
            vista.mostrarPartidos(torneo.getPartido(), etapa);
        }catch (NumeroEquiposInvalidoException | SorteoInvalidoException e) {
            vista.mostrarError(e.getMessage());
        }catch (Exception e) {
            vista.mostrarError("Ocurrio un error inesperado durante el sorteo: " + e.getMessage());
        }
    }
    
    private void guardarTorneo() {
        try {
            torneo.guardarTorneo(RUTA_ARCHIVO);
            vista.mostrarMensaje("Estado de torneo guardado correctamente en '" + RUTA_ARCHIVO + "'." );
        } catch (IOException e) {
            vista.mostrarError("Fallo en la persistencia al guardar el archivo:  " + e.getMessage());
        }
    }
    
    private void cargarTorneo() {
        try {
            this.torneo = Torneo.cargarTorneo(RUTA_ARCHIVO);
            vista.mostrarMensaje("Estado del torneo cargado con exito desde '" + RUTA_ARCHIVO + "'.");
        } catch (IOException | ClassNotFoundException e) {
            vista.mostrarError("Fallo al cargar el archivo de persistencia: " + e.getMessage());
        }
    }
}
