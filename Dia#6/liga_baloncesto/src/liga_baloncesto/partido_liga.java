/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liga_baloncesto;
import java.util.Date;




/**
 *
 * @author Usuario
 */

public class partido_liga extends partidos {
    
    private int jornada;
    
    public partido_liga(String equipo_local, String equipo_visitante, int cestas_local, int cestas_visitante, boolean finalizo, Date fecha_partido, int jornada) {
        super(equipo_local, equipo_visitante, cestas_local, cestas_visitante, finalizo, fecha_partido);
        this.jornada = jornada;
    }
    
    
    public int getjornada(){
    
    return jornada;    
            
    }
    
    
    public void setJornada(){
    this.jornada = jornada;
    }

    // Implementación de los métodos abstractos
    @Override
    public String resultado() {
        return "Cestas Local: " + cestas_local + ", Cestas Visitante: " + cestas_visitante;
    }

    @Override
    public void registrarPuntosLocal(int puntos) {
        this.cestas_local += puntos;
    }

    @Override
    public void registrarPuntosVisitante(int puntos) {
        this.cestas_visitante += puntos;
    }

    @Override
    public String obtenerGanador() {
        if (cestas_local > cestas_visitante) {
            return equipo_local;
        } else if (cestas_visitante > cestas_local) {
            return equipo_visitante;
        } else {
            return "Empate";
        }
    }

    @Override
    public void finalizar() {
        this.finalizo = true;
    }

    @Override
    public String infoPartido() {
        return "Equipo local: " + equipo_local + " | Equipo visitante: " + equipo_visitante + " | Cestas local: " + cestas_local + " | Cestas visitante: " + cestas_visitante + " | Fecha: " + fecha_partido + " | Jornada: " + jornada;
    }
}