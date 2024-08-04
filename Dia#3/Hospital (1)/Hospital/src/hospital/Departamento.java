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
public class Departamento {
    private String nombre;
    private List<personal_1> personal_hospital;

    public Departamento(String nombre) {
        this.nombre = nombre;
        this.personal_hospital = new ArrayList<>();
    }

    public void agregarPersonal(personal_1 personal_1) {
        this.personal_hospital.add(personal_1);
    }
}
