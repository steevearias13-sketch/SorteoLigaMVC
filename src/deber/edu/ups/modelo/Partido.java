/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deber.edu.ups.modelo;

import java.io.Serializable;

/**
 *
 * @author Steeve
 */
public class Partido implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Equipo equipo1;
    private Equipo equipo2;
    private String etapa; 
    private Equipo ganador;
    
    // Constructor con 3 parametros para cuando recien se sortea el partido
    public Partido(Equipo equipo1, Equipo equipo2, String etapa){
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.etapa = etapa;
        this.ganador = null;
    }

    // Constructor completo con 4 parametros
    public Partido(Equipo equipo1, Equipo equipo2, String etapa, Equipo ganador){
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.etapa = etapa;
        this.ganador = ganador;
    }
    
    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public Equipo getGanador() {
        return ganador;
    }

    public void setGanador(Equipo ganador) {
        this.ganador = ganador;
    }

    @Override
    public String toString() {
        String resultado = "[" + etapa + "] " + equipo1.getNombre() + " VS " + equipo2.getNombre();
        if (ganador != null) {
            resultado += " --> Ganador: " + ganador.getNombre();
        }
        return resultado;
    }
    
}
