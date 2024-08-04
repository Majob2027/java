package hospital;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Hospital {
    static Scanner scanner = new Scanner(System.in);
    static List<hospital_1> hospitales = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    agregarHospital();
                    break;
                case 2:
                    AsignarHospital();
                    break;
                case 3:
                    actualizarHospital();
                    break;
                case 4:
                    eliminarPersona();
                    break;
                case 5:
                    actualizarPersona();
                    break;
                case 6:
                    mostrarHospitales();
                    break;
                case 7:
                    mostrarDoctores();
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println(" 1. Agregar Hospital");
        System.out.println(" 2. Asignar Hospital a persona");
        System.out.println(" 3. Actualizar Hospital");
        System.out.println(" 4. Eliminar persona");
        System.out.println(" 5. Actualizar persona");
        System.out.println(" 7. Mostrar Hospitales");
        System.out.println(" 8. Mostrar Doctores");
        System.out.println(" 9. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void agregarHospital() {
        System.out.print("Ingrese el nombre del hospital: ");
        String nombre = scanner.nextLine();
        hospital_1 hospital = new hospital_1(nombre);
        hospitales.add(hospital);
        System.out.println("Hospital agregado exitosamente.");
    }

    public static void AsignarHospital() {
        System.out.print("Ingrese el nombre del hospital al que desea agregar a la persona: ");
        String nombreHospital = scanner.nextLine();

        hospital_1 hospital = buscarHospital(nombreHospital);
        if (hospital == null) {
            System.out.println("Hospital no encontrado.");
            return;
        }

        System.out.print("Ingrese el nombre de la persona: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la dirección de la persona: ");
        String direccion = scanner.nextLine();

        System.out.println("Seleccione el tipo de persona:");
        System.out.println("1. Paciente");
        System.out.println("2. Doctor");
        int tipoPersona = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (tipoPersona == 1) {
            System.out.print("Ingrese la fecha de nacimiento (yyyy-MM-dd): ");
            String fechaNacimientoStr = scanner.nextLine();
            System.out.print("Ingrese la fecha de ingreso (yyyy-MM-dd): ");
            String fechaIngresoStr = scanner.nextLine();
            System.out.print("Ingrese el pabellón: ");
            String pabellon = scanner.nextLine();

            Date fechaNacimiento = null;
            Date fechaIngreso = null;

            try {
                fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
                fechaIngreso = dateFormat.parse(fechaIngresoStr);
            } catch (ParseException e) {
                System.out.println("Formato de fecha inválido. Asegúrese de usar el formato yyyy-MM-dd.");
                return;
            }

            Paciente paciente = new Paciente(nombre, direccion, fechaNacimiento, fechaIngreso, pabellon);
            hospital.agregarPersona(paciente);

        } else if (tipoPersona == 2) {
            System.out.print("Ingrese la fecha de vinculación (yyyy-MM-dd): ");
            String fechaVinculacionStr = scanner.nextLine();
            Date fechaVinculacion = parseDate(fechaVinculacionStr);
            if (fechaVinculacion == null) {
                return; // Error en el formato de fecha
            }

            System.out.print("Ingrese el salario: ");
            double salario = scanner.nextDouble();
            scanner.nextLine(); // Consumir la nueva línea

            System.out.print("¿El doctor es líder del equipo? (true/false): ");
            boolean esLider = scanner.nextBoolean();
            scanner.nextLine(); // Consumir la nueva línea

            Doctor doctor = new Doctor(nombre, direccion, fechaVinculacion, salario, esLider);
            hospital.agregarPersona(doctor);
        } else {
            System.out.println("Tipo de persona inválido.");
            return;
        }

        System.out.println("Persona agregada exitosamente.");
    }

    public static hospital_1 buscarHospital(String nombre) {
        for (hospital_1 hospital : hospitales) {
            if (hospital.getNombre().equalsIgnoreCase(nombre)) {
                return hospital;
            }
        }
        return null;
    }

    public static void actualizarHospital() {
        System.out.print("Ingrese el nombre del hospital a actualizar: ");
        String nombreActual = scanner.nextLine();
        hospital_1 hospital = buscarHospital(nombreActual);
        if (hospital == null) {
            System.out.println("Hospital no encontrado.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre del hospital: ");
        String nuevoNombre = scanner.nextLine();
        hospital.setNombre(nuevoNombre);
        System.out.println("Nombre del hospital actualizado exitosamente.");
    }

    public static void eliminarPersona() {
        System.out.print("Ingrese el nombre del hospital: ");
        String nombreHospital = scanner.nextLine();
        hospital_1 hospital = buscarHospital(nombreHospital);
        if (hospital == null) {
            System.out.println("Hospital no encontrado.");
            return;
        }

        System.out.print("Ingrese el nombre de la persona a eliminar: ");
        String nombrePersona = scanner.nextLine();

        boolean eliminado = hospital.eliminarPersona(nombrePersona); // Implementa eliminarPersona en hospital_1
        if (eliminado) {
            System.out.println("Persona eliminada exitosamente.");
        } else {
            System.out.println("Persona no encontrada en el hospital.");
        }
    }

    public static void actualizarPersona() {
        System.out.print("Ingrese el nombre del hospital: ");
        String nombreHospital = scanner.nextLine();
        hospital_1 hospital = buscarHospital(nombreHospital);
        if (hospital == null) {
            System.out.println("Hospital no encontrado.");
            return;
        }

        System.out.print("Ingrese el nombre de la persona a actualizar: ");
        String nombrePersona = scanner.nextLine();
        personal_1 persona = hospital.buscarPersona(nombrePersona); // Implementa buscarPersona en hospital_1

        if (persona == null) {
            System.out.println("Persona no encontrada.");
            return;
        }

        System.out.print("Ingrese la nueva dirección de la persona: ");
        String nuevaDireccion = scanner.nextLine();
        persona.setDireccion(nuevaDireccion);
        System.out.println("Dirección actualizada exitosamente.");
    }


    public static void mostrarHospitales() {
        for (hospital_1 hospital : hospitales) {
            System.out.println("Hospital: " + hospital.getNombre());
            for (personal_1 persona : hospital.getPersonas()) {
                System.out.println("  Persona: " + persona.getNombre());
            }
        }
    }

    public static void mostrarDoctores() {
        for (hospital_1 hospital : hospitales) {
            System.out.println("Hospital: " + hospital.getNombre());
            for (personal_1 persona : hospital.getPersonas()) {
                if (persona instanceof Doctor) {
                    Doctor doctor = (Doctor) persona;
                    System.out.println("  Doctor: " + doctor.getNombre() + ", Dirección: " + doctor.getDireccion() + ", Salario: " + doctor.getSalario() + ", Líder: " + doctor.isLider());
                }
            }
        }
    }

    // Método para parsear la fecha
    private static Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Use yyyy-MM-dd.");
            return null;
        }
    }
    
}
