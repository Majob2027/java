package crud;

import java.util.Scanner;

public class CRUD {
    private Camper[] campers;
    private int contador;

    public CRUD(int tamaño) {
        campers = new Camper[tamaño];
        contador = 0;
    }

    // Crear
    public void inscribirCamper(String nombre, int identificacion, String apellidos, String direccion,
                                int telefono, String estado, String riesgo) {
        if (contador < campers.length) {
            campers[contador] = new Camper(nombre, identificacion, apellidos, direccion, telefono, estado, riesgo);
            contador++;
            System.out.println("Camper inscrito correctamente.");
        } else {
            System.out.println("No se puede inscribir más campers, el array está lleno.");
        }
    }

    // Actualizar Camper
    public void actualizarCamper(int indice, String nuevoNombre, int nuevaIdentificacion, String nuevosApellidos,
                                 String nuevaDireccion, int nuevoTelefono, String nuevoEstado, String nuevoRiesgo) {
        if (indice >= 0 && indice < contador) {
            Camper camper = campers[indice];
            camper.setNombre(nuevoNombre);
            camper.setIdentificacion(nuevaIdentificacion);
            camper.setApellidos(nuevosApellidos);
            camper.setDireccion(nuevaDireccion);
            camper.setTelefono(nuevoTelefono);
            camper.setEstado(nuevoEstado);
            camper.setRiesgo(nuevoRiesgo);
            System.out.println("Camper actualizado correctamente.");
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }

    // Borrar Camper
    public void borrarCamper(int indice) {
        if (indice >= 0 && indice < contador) {
            for (int i = indice; i < contador - 1; i++) {
                campers[i] = campers[i + 1];
            }
            campers[contador - 1] = null;
            contador--;
            System.out.println("Camper eliminado correctamente.");
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }

    // Mostrar Campers
    public void mostrarCampers() {
        if (contador == 0) {
            System.out.println("No hay campers inscritos.");
        } else {
            System.out.println("Lista de campers:");
            for (int i = 0; i < contador; i++) {
                System.out.println((i + 1) + ". " + campers[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CRUD crud = new CRUD(100);

        while (true) {
            limpiarPantalla();
            System.out.println("**************************************************");
            System.out.println("*                   Bienvenido                   *");
            System.out.println("**************************************************");
            System.out.println("¿Cuál es tu rol en CAMPUSLANDS?");
            System.out.println("1. Coordinador\t 2. Trainer\t 3. Salir");
            int rol = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea

            if (rol == 1) {
                while (true) {
                    limpiarPantalla();
                    System.out.println("**************************************************");
                    System.out.println("*             Bienvenido Coordinador             *");
                    System.out.println("**************************************************");
                    System.out.println("¿A cuál módulo desea ingresar?");
                    System.out.println("1. Camper\t 2. Trainer\t 3. Reportes\t 4. Rutas\t 5. Volver al menú anterior");
                    int decision = scanner.nextInt();
                    scanner.nextLine();  // Consumir la nueva línea

                    if (decision == 1) {
                        while (true) {
                            limpiarPantalla();
                            System.out.println("**************************************************");
                            System.out.println("*                  MÓDULO CAMPERS                *");
                            System.out.println("**************************************************");
                            System.out.println("1. Inscribir Camper\t 2. Actualizar Camper\t 3. Borrar Camper\t 4. Mostrar Campers\t 5. Volver al menú anterior");
                            int decisionCamper = scanner.nextInt();
                            scanner.nextLine();  // Consumir la nueva línea

                            switch (decisionCamper) {
                                case 1:
                                    System.out.println("Ingrese el nombre del camper:");
                                    String nombre = scanner.nextLine();
                                    System.out.println("Ingrese la identificación del camper:");
                                    int identificacion = scanner.nextInt();
                                    scanner.nextLine();  // Consumir la nueva línea
                                    System.out.println("Ingrese los apellidos del camper:");
                                    String apellidos = scanner.nextLine();
                                    System.out.println("Ingrese la dirección del camper:");
                                    String direccion = scanner.nextLine();
                                    System.out.println("Ingrese el teléfono del camper:");
                                    int telefono = scanner.nextInt();
                                    scanner.nextLine();  // Consumir la nueva línea
                                    System.out.println("Ingrese el estado del camper:");
                                    String estado = scanner.nextLine();
                                    System.out.println("Ingrese el riesgo del camper:");
                                    String riesgo = scanner.nextLine();
                                    crud.inscribirCamper(nombre, identificacion, apellidos, direccion, telefono, estado, riesgo);
                                    break;
                                case 2:
                                    System.out.println("Ingrese el índice del camper que desea actualizar:");
                                    int indiceActualizar = scanner.nextInt();
                                    scanner.nextLine();  // Consumir la nueva línea
                                    if (indiceActualizar >= 1 && indiceActualizar <= crud.contador) {
                                        System.out.println("Ingrese el nuevo nombre:");
                                        String nuevoNombre = scanner.nextLine();
                                        System.out.println("Ingrese la nueva identificación:");
                                        int nuevaIdentificacion = scanner.nextInt();
                                        scanner.nextLine();  // Consumir la nueva línea
                                        System.out.println("Ingrese los nuevos apellidos:");
                                        String nuevosApellidos = scanner.nextLine();
                                        System.out.println("Ingrese la nueva dirección:");
                                        String nuevaDireccion = scanner.nextLine();
                                        System.out.println("Ingrese el nuevo teléfono:");
                                        int nuevoTelefono = scanner.nextInt();
                                        scanner.nextLine();  // Consumir la nueva línea
                                        System.out.println("Ingrese el nuevo estado:");
                                        String nuevoEstado = scanner.nextLine();
                                        System.out.println("Ingrese el nuevo riesgo:");
                                        String nuevoRiesgo = scanner.nextLine();
                                        crud.actualizarCamper(indiceActualizar - 1, nuevoNombre, nuevaIdentificacion, nuevosApellidos, nuevaDireccion, nuevoTelefono, nuevoEstado, nuevoRiesgo);
                                    } else {
                                        System.out.println("Índice inválido.");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Ingrese el índice del camper que desea borrar:");
                                    int indiceBorrar = scanner.nextInt();
                                    scanner.nextLine();  // Consumir la nueva línea
                                    if (indiceBorrar >= 1 && indiceBorrar <= crud.contador) {
                                        crud.borrarCamper(indiceBorrar - 1);
                                    } else {
                                        System.out.println("Índice inválido.");
                                    }
                                    break;
                                case 4:
                                    crud.mostrarCampers();
                                    break;
                                case 5:
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                                    break;
                            }

                            if (decisionCamper == 5) {
                                break;
                            }
                        }
                    } else if (decision == 5) {
                        break;
                    }
                }
            } else if (rol == 3) {
                break;
            }
        }

        scanner.close();
    }

    public static void limpiarPantalla() {
        // Método para limpiar la pantalla
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
