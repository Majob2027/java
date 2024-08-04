/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital;
import java.util.Date;
/**
 *
 
@author camper*/


public class Paciente extends personal_1 {
    private Date fechaIngreso;
    private String pabellon;

    public Paciente(String nombre, String direccion, Date fechaNacimiento, Date fechaIngreso, String pabellon) {
        super(nombre, direccion, fechaNacimiento);
        this.fechaIngreso = fechaIngreso;
        this.pabellon = pabellon;
    }

    // Getters y setters
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getPabellon() {
        return pabellon;
    }

    public void setPabellon(String pabellon) {
        this.pabellon = pabellon;
    }

    @Override
    public String toString() {
        return super.toString() + ", Fecha de Ingreso: " + fechaIngreso + ", Pabellón: " + pabellon;
    }
}


