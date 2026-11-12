/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deber.edu.ups.modelo;

/**
 *
 * @author Steeve
 */
import deber.edu.ups.excepciones.NumeroEquiposInvalidoException;
import deber.edu.ups.excepciones.SorteoInvalidoException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Torneo implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private List<Equipo> equipos;
    private List<Partido> partidos;
    
    public Torneo(String nombre){
        this.nombre = nombre;
        this.equipos = new ArrayList();
        this.partidos = new ArrayList();
    }
    
    public void agregarEquipo(Equipo equipo){
        this.equipos.add(equipo);  
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public List<Partido> getPartido() {
        return partidos;
    }

    public void setPartido(List<Partido> partido) {
        this.partidos = partido;
    }
    
    public void validarCantidadEquipos() throws NumeroEquiposInvalidoException{
        int cantidad = equipos.size();
        
        // Un número n > 0 es potencia de 2 si (n &(n - 1)) == 0
        boolean esPotenciaDeDos = (cantidad > 0) && ((cantidad & (cantidad -1)) == 0);
        
        if (!esPotenciaDeDos){
            throw new NumeroEquiposInvalidoException(
                    "El numero de equipos (" + cantidad + ") es invalido. Debe ser una potencia de 2 (ej. 16, 8, 4,2)."
            );
        }
    }
    // Metodo publico que inicia el proceso de sorteo
    public void sortearEtapa(String etapa) throws NumeroEquiposInvalidoException, SorteoInvalidoException{
        // Validar la cantidad de equipos antes de sortear
        validarCantidadEquipos();
        
        //Creamos una copia de la lista de equipos para ir removiendo los emparejamientos
        List<Equipo> disponibles = new ArrayList<>(this.equipos);
        
        // Iniciar la recursividad
        realizarSorteoRecursivo(disponibles, etapa);
        
    }
    // Metodo privado que ejecuta la recursividad
    private void realizarSorteoRecursivo(List<Equipo> disponibles, String etapa) throws SorteoInvalidoException{
        // Si no quedan equipos por sortear, la recursion termina
        if (disponibles.isEmpty()){
            return;
        }
        // Validacion en seguridad en recursion
        if (disponibles.size() < 2 || disponibles.size() % 2 != 0){
            throw new SorteoInvalidoException("Fallo en la asignacion: numero impar de equipos disponibles durante el sorteo");
        }
        
        Random random = new Random();
        
        // Paso recursivo
        // Seleccionar y mover el primer equipo al azar
        int index1 = random.nextInt(disponibles.size());
        Equipo e1 = disponibles.remove(index1);
        
        //Seleccionar y remover el oponente al azar de los restantes
        int index2 = random.nextInt(disponibles.size());
        Equipo e2 = disponibles.remove(index2);
        
        // Crear el partido y agregarlo a la listadel torneo
        Partido partido = new Partido(e1, e2, etapa);
        this.partidos.add(partido);
        
        // Llamada ecursiva con la lista reducida
        realizarSorteoRecursivo(disponibles, etapa);
    }
    
    // Guarda el objeto Torneo completo en un archivo binario (.ser)
    public void guardarTorneo(String rutaArchivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))){
            oos.writeObject(this);
        }
    }
    
    // Carga un objeto Torneo previamente guardado desde un archivo binario
    public static Torneo cargarTorneo(String rutaArchivo) throws IOException, ClassNotFoundException{
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))){
            return (Torneo) ois.readObject();
        }
    }
}
