/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 
@author Usuario*/
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Doctor extends personal_1 {
    private double salario;
    private boolean Lider;
    private List<Doctor> equipo;

    public Doctor(String nombre, String direccion, Date fechaVinculacion, double salario, boolean Lider) {
        super(nombre, direccion, fechaVinculacion);
        this.salario = salario;
        this.Lider = Lider;
        this.equipo = new ArrayList<>();
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isLider() {
        return Lider;
    }

    public void setLider(boolean lider) {
        Lider = lider;
    }

    public void agregarDoctorAlEquipo(Doctor doctor) {
        equipo.add(doctor);
    }

    @Override
    public String toString() {
        return super.toString() + ", Salario: " + salario + ", Líder: " + Lider;
    }
}

