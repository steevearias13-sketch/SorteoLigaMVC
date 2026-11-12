/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deber.edu.ups.vista;

import deber.edu.ups.modelo.Partido;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Steeve
 */
public class VistaTorneo {
    private Scanner scanner;
    
    public VistaTorneo(){
        this.scanner = new Scanner(System.in);
    }
    
    public int mostrarMenu(){
        System.out.println("\n========== LIGA PROFESIONAL DE FUTBOL ==========");
        System.out.println("1. Cargar 16 equipos por defecto (Octavos de Final)");
        System.out.println("2. Ejecutar Sorteo Recursivo");
        System.out.println("3. Mostrar Partidos Sorteados");
        System.out.println("4. Guardar Torneo en archivo (.ser)");
        System.out.println("5. Cargar Torneo desde archivo (.ser)");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opcion: ");
        
        try {
            return Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public String pedirTexto(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }
    
    public void mostrarPartidos(List<Partido> partidos, String etapa){
        System.out.println("\n--- EMPAREJAMIENTOS GENERADOS [" + etapa.toUpperCase() + "] ---");
        if(partidos.isEmpty()){
            System.out.println("No hay partidos registrados aun.");
            return;
        }
        int contador = 1;
        for(Partido p : partidos){
            if (p.getEtapa().equalsIgnoreCase(etapa)){
                System.out.println(" Partido " + contador + ": " + p);
                contador++;
            }
        }
    }
    
    public void mostrarMensaje(String mensaje){
        System.out.println(">> " + mensaje);
    }
    
    public void mostrarError(String error){
        System.out.println(" ERROR " + error);
    }
}
