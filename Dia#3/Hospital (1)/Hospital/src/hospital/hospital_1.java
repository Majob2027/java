/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital;
import java.util.ArrayList;
import java.util.List;
/**
 *
 
@author Usuario*/


public class hospital_1 {
    private String nombre;
    private List<Departamento> departamentos;
    private List<personal_1> personas;

    public hospital_1(String nombre) {
        this.nombre = nombre;
        this.departamentos = new ArrayList<>();
        this.personas = new ArrayList<>();
    }

    public void agregarDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

    // Cambiar el tipo del parámetro a personal_1
    public void agregarPersona(personal_1 persona) {
        personas.add(persona);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<personal_1> getPersonas() {
        return personas;
    }

    // Método para buscar una persona por su nombre
    public personal_1 buscarPersona(String nombre) {
        for (personal_1 persona : personas) {
            if (persona.getNombre().equalsIgnoreCase(nombre)) {
                return persona;
            }
        }
        return null;
    }

    // Método para eliminar una persona por su nombre
    public boolean eliminarPersona(String nombre) {
        personal_1 persona = buscarPersona(nombre);
        if (persona != null) {
            personas.remove(persona);
            return true;
        }
        return false;
    }
}

