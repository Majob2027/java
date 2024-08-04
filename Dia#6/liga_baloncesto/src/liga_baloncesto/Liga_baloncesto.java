/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package liga_baloncesto;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Liga_baloncesto {
    static Scanner scanner = new Scanner(System.in);
    private static ArrayList<partidos> lista_partidos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de opciones:");
            System.out.println("1. Registrar partido de liga");
            System.out.println("2. Registrar partido playoff");
            System.out.println("3. Finalizar partido");
            System.out.println("4. Mostrar información del partido");
            System.out.println("5. Mostrar resultado del partido");
            System.out.println("6. Mostrar ganador del partido");
            System.out.println("7. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarPartidoLiga(scanner);
                    break;
                case 2:
                    registrarPartidoPlayoff(scanner);
                    break;
                case 3:
                    finalizarPartido(scanner);
                    break;
                case 4:
                    mostrarInfoPartido(scanner);
                    break;
                case 5:
                    mostrarResultadoPartido(scanner);
                    break;
                case 6:
                    mostrarGanadorPartido(scanner);
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
       
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (opcion != 6);

        scanner.close();
    }

    private static void registrarPartidoLiga(Scanner scanner) {
        System.out.print("Ingrese el equipo local: ");
        String equipo_local = scanner.next();
        System.out.print("Ingrese el equipo visitante: ");
        String equipo_visitante = scanner.next();
        System.out.print("Ingrese la jornada: ");
        int jornada = scanner.nextInt();
        Date fechaPartido = new Date();
        System.out.print("Ingrese cestas de local: ");
        int cestas_local = scanner.nextInt();
        System.out.print("Ingrese cestas de visitante: ");
        int cestas_visitante = scanner.nextInt();

        partidos partido = new partido_liga(equipo_local, equipo_visitante, cestas_local, cestas_visitante, false, fechaPartido, jornada);
        lista_partidos.add(partido);
        System.out.println("Partido registrado con éxito.");
    }
    
    private static void registrarPartidoPlayoff(Scanner scanner) {
    System.out.print("Ingrese equipo local: ");
    String equipo_local = scanner.next();
    System.out.print("Ingrese equipo visitante: ");
    String equipo_visitante = scanner.next();
    System.out.print("Ingrese ronda (ej. octavos, cuartos, final): ");
    String ronda = scanner.next();

    System.out.print("Ingrese las cestas de local: ");
    int cestas_local = scanner.nextInt();
    System.out.print("Ingrese las cestas de visitante: ");
    int cestas_visitante = scanner.nextInt();

    Date fechaPartido = new Date();  // Usamos la fecha actual para el partido

    // Supongo que 'ronda' debería ser un String. Si 'ronda' realmente necesita ser un int, usa: int ronda = scanner.nextInt();
    partidos partido = new partidos_playoffs(equipo_local, equipo_visitante, cestas_local, cestas_visitante, false, fechaPartido, ronda);
    lista_partidos.add(partido);
    System.out.println("Partido registrado con éxito.");
}


    private static void finalizarPartido(Scanner scanner) {
        partidos partido = seleccionarPartido(scanner);
        if (partido != null) {
            partido.finalizar();
            System.out.println("El partido ha sido finalizado.");
        }
    }

    private static void mostrarInfoPartido(Scanner scanner) {
        partidos partido = seleccionarPartido(scanner);
        if (partido != null) {
            System.out.println(partido.infoPartido());
        }
    }

    private static void mostrarResultadoPartido(Scanner scanner) {
        partidos partido = seleccionarPartido(scanner);
        if (partido != null) {
            System.out.println(partido.resultado());
        }
    }

    private static void mostrarGanadorPartido(Scanner scanner) {
        partidos partido = seleccionarPartido(scanner);
        if (partido != null) {
            System.out.println("El ganador es: " + partido.obtenerGanador());
        }
    }

    private static partidos seleccionarPartido(Scanner scanner) {
        if (lista_partidos.isEmpty()) {
            System.out.println("No hay partidos registrados.");
            return null;
        }

        System.out.println("Seleccione el partido:");
        for (int i = 0; i < lista_partidos.size(); i++) {
            System.out.println(i + 1 + ". " + lista_partidos.get(i).infoPartido());
        }
        int seleccion = scanner.nextInt();

        if (seleccion > 0 && seleccion <= lista_partidos.size()) {
            return lista_partidos.get(seleccion - 1);
        } else {
            System.out.println("Selección no válida.");
            return null;
        }
    }
}
    

