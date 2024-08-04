/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital;
import java.util.Date;

/**
 *
 
@author Usuario*/
import java.util.Date;

public class personal_1 {
    private String nombre;
    private String direccion;
    private Date fechaVinculacion; // Agregar campo fecha de vinculación

    // Constructor
    public personal_1(String nombre, String direccion, Date fechaVinculacion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaVinculacion = fechaVinculacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaVinculacion() {
        return fechaVinculacion;
    }

    public void setFechaVinculacion(Date fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Dirección: " + direccion + ", Fecha de Vinculación: " + fechaVinculacion;
    }
}


