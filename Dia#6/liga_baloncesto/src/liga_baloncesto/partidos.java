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
public abstract class partidos {
    protected String equipo_local;
    protected String equipo_visitante;
    protected int cestas_local;
    protected int cestas_visitante;
    protected boolean finalizo;
    protected Date fecha_partido;

    public partidos(String equipo_local, String equipo_visitante, int cestas_local, int cestas_visitante, boolean finalizo, Date fecha_partido) {
        this.equipo_local = equipo_local;
        this.equipo_visitante = equipo_visitante;
        this.cestas_local = cestas_local;
        this.cestas_visitante = cestas_visitante;
        this.finalizo = finalizo;
        this.fecha_partido = fecha_partido;
    }
    
     public String getEquipoLocal() {
        return equipo_local;
    }

    public String getEquipoVisitante() {
        return equipo_visitante;
    }

    public int getCestasLocal() {
        return cestas_local;
    }

    public int getCestasVisitante() {
        return cestas_visitante;
    }

    public boolean isFinalizado() {
        return finalizo;
    }

    public Date getFechaPartido() {
        return fecha_partido;
    }

    // Setters
    public void setEquipoLocal(String equipoLocal) {
        this.equipo_local = equipoLocal;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipo_visitante = equipoVisitante;
    }

    public void setCestasLocal(int cestasLocal) {
        this.cestas_local = cestasLocal;
    }

    public void setCestasVisitante(int cestasVisitante) {
        this.cestas_visitante = cestasVisitante;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizo = finalizado;
    }

    public void setFechaPartido(Date fechaPartido) {
        this.fecha_partido = fechaPartido;
    }
    
    
     public abstract String resultado();
    public abstract void registrarPuntosLocal(int puntos);
    public abstract void registrarPuntosVisitante(int puntos);
    public abstract String obtenerGanador();
    public abstract void finalizar();
    public abstract String infoPartido();
}


// equipo local, equipo visitante, las cestas del equipo local, las cestas del equipo visitante, si el partido ha finalizado o no y la fecha del partido