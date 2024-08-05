/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package baloncesto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Baloncesto {

    private Connection con;
    public Connection Conexion() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("Archivo Config.properties no encontrado");
            }

            props.load(input);

            String url = props.getProperty("Url");
            String user = props.getProperty("User");
            String password = props.getProperty("Password");

            if (url == null || user == null || password == null) {
                throw new IllegalStateException("Una o más propiedades de conexión no están definidas");
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (IOException | ClassNotFoundException | SQLException | IllegalStateException e) {
            System.err.println("Error en la conexión :(, error: " + e);
            JOptionPane.showMessageDialog(null, "Error en la conexión: " + e.toString());
        }
        return con;
    }
    
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static boolean esFechaValida(String fecha) {
        try {
            LocalDate.parse(fecha, FORMATO_FECHA);
            return true; // La fecha es válida
        } catch (DateTimeParseException e) {
            return false; // La fecha no es válida
        }
    }
    
    private static int obtenerDatoObligatorioint(Scanner sc, String campo) {
        int entrada = -1; // Valor predeterminado para indicar un valor no establecido
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("Ingresa " + campo + " (obligatorio)");
            try {
                entrada = sc.nextInt();
                sc.nextLine();
                entradaValida = true; // Si la entrada es correcta, salimos del bucle
            } catch (InputMismatchException e) {
                // Limpiar el buffer del scanner en caso de entrada incorrecta
                sc.next(); 
                System.out.println("Entrada inválida. Por favor, ingresa un número entero.");
            }
        }
        return entrada;
    }
    
    
    private static String obtenerDatoObligatorio(Scanner sc, String campo) {
        String entrada;
        while(true){
            System.out.println("Ingresa " + campo + " (obligatorio)");
            entrada = sc.nextLine().trim();
            if (!entrada.isEmpty()){
                break;
            } else {
                System.out.println(campo + " es un campo obligatorio. Por favor, ingrésalo. :)");
            }
        }
        return entrada;
    }
    
    
     public void addPartido(Scanner sc){
         Baloncesto bl = new Baloncesto();
         Connection con = bl.Conexion();
         PreparedStatement ps = null;
         PreparedStatement ps2 = null;
         ResultSet rs = null;
         
         System.out.println("¿Que tipo de partido deseas ingresar?");
         System.out.println("1. Liga");
         System.out.println("2. PlayOffs");
         System.out.println("3. Salir");
         int option = sc.nextInt();
         sc.nextLine();
         
         switch(option){
             case 1:

                try {
                    
                    String equipo_local = obtenerDatoObligatorio(sc, "el nombre del equipo local");
                    String equipo_visitante = obtenerDatoObligatorio(sc, "el nombre del equipo visitante");
                    int cestas_local = obtenerDatoObligatorioint(sc, "el numero de cestas del equipo local");
                    int cestas_visitante = obtenerDatoObligatorioint(sc, "el numero de cestas del equipo visitante");

                    String estado;
                    while(true){
                        System.out.println("Ingresa el estado en el que se encuentra el partido(Activo, Finalizado)(Obligatorio)");
                        estado = sc.nextLine();
                        estado = estado.substring(0, 1).toUpperCase() + estado.substring(1).toLowerCase();

                        if("Activo".equals(estado) || "Finalizado".equals(estado)){
                            break;
                        } else {
                            System.out.println(estado + " no fue reconocido como un estado valido");
                        }
                       
                    }

                    String fecha_partido;
                    while(true){
                        System.out.println("Ingresa la fecha del partido(YYYY-MM-DD)");
                        fecha_partido = sc.nextLine().trim();

                        if (esFechaValida(fecha_partido)) {
                           break;
                       } else {
                           System.out.println("Fecha inválida. Asegúrate de que esté en formato YYYY-MM-DD y sea una fecha válida.");
                       }
                    }

                    //partido de liga
                    int jornada = obtenerDatoObligatorioint(sc, "el numero de la jornada ");
                    
                    
                    ps = con.prepareStatement("INSERT INTO partido (equipo_local, equipo_visitante, cestas_local, cestas_visitante, estado, fecha_partido) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                        ps2 = con.prepareStatement("INSERT INTO liga (id_partido, jornada) VALUES (?, ?)");

                        ps.setString(1, equipo_local);
                        ps.setString(2, equipo_visitante);
                        ps.setInt(3, cestas_local);
                        ps.setInt(4, cestas_visitante);
                        ps.setString(5, estado);
                        ps.setString(6, fecha_partido);

                        ps.executeUpdate();
                        rs = ps.getGeneratedKeys();

                        if (rs.next()) {
                            int id_partido = rs.getInt(1);
                            ps2.setInt(1, id_partido);
                            ps2.setInt(2, jornada);

                            ps2.executeUpdate();
                        }

                        System.out.println("Datos insertados correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error al insertar los datos a la base de datos: " + e.getMessage());
                    }
                break;
             case 2: 
                
                try {
                    
                    String equipo_local = obtenerDatoObligatorio(sc, "el nombre del equipo local");
                    String equipo_visitante = obtenerDatoObligatorio(sc, "el nombre del equipo visitante");
                    int cestas_local = obtenerDatoObligatorioint(sc, "el numero de cestas del equipo local");
                    int cestasVisitante = obtenerDatoObligatorioint(sc, "el numero de cestas del equipo visitante");

                    String estado;
                    while(true){
                        System.out.println("Ingresa el estado en el que se encuentra el partido(Activo, Finalizado)(Obligatorio)");
                        estado = sc.nextLine();
                        estado = estado.substring(0, 1).toUpperCase() + estado.substring(1).toLowerCase();

                        if("Activo".equals(estado) || "Finalizado".equals(estado)){
                            break;
                        } else {
                            System.out.println(estado + " no fue reconocido como un estado valido");
                        }
                    }

                    String fechaPartido;
                    while(true){
                        System.out.println("Ingresa la fecha del partido(YYYY-MM-DD)");
                        fechaPartido = sc.nextLine().trim();

                        if (esFechaValida(fechaPartido)) {
                           break;
                       } else {
                           System.out.println("Fecha inválida. Asegúrate de que esté en formato YYYY-MM-DD y sea una fecha válida.");
                       }
                    }

                    //partido de playOffs
                    String ronda;
                    while(true){
                        System.out.println("Ingresa la ronda en la que se encuentra el partido(Octavos, Cuartos, Final)");
                        ronda = sc.nextLine();
                        ronda = ronda.substring(0, 1).toUpperCase() + ronda.substring(1).toLowerCase();

                        if("Octavos".equals(ronda) || "Cuartos".equals(ronda) || "Final".equals(ronda)){
                            break;
                        } else {
                            System.out.println("Ronda no identificada. Por favor ingresa una valida");
                        }
                    }
                    
                    
                    ps = con.prepareStatement("INSERT INTO partido (equipoLocal, equipoVisitante, cestasLocal, cestasVisitante, estado, fechaPartido) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                        ps2 = con.prepareStatement("INSERT INTO playOffs (id_partido, ronda) VALUES (?, ?)");

                        ps.setString(1, equipo_local);
                        ps.setString(2, equipo_visitante);
                        ps.setInt(3, cestas_local);
                        ps.setInt(4, cestasVisitante);
                        ps.setString(5, estado);
                        ps.setString(6, fechaPartido);

                        ps.executeUpdate();
                        rs = ps.getGeneratedKeys();

                        if (rs.next()) {
                            int id_partido = rs.getInt(1);
                            ps2.setInt(1, id_partido);
                            ps2.setString(2, ronda);

                            ps2.executeUpdate();
                        }

                        System.out.println("Datos insertados correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error al insertar los datos a la base de datos: " + e.getMessage());
                    }
                break;
            case 3:
                System.out.println("Saliendo...");
                break;
             default:
                 System.out.println("Opcion incorrecta");
         }
          
    }
     
     
     public void deletePartido(Scanner sc) {
        Baloncesto bl = new Baloncesto();
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = bl.Conexion();
            System.out.println("Ingrese el id del partido que desea eliminar:");
            int id = sc.nextInt();
            sc.nextLine();

            ps = con.prepareStatement("DELETE FROM partido WHERE id = ?");
            ps.setInt(1, id);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("El partido y sus registros relacionados fueron eliminados correctamente.");
            } else {
                System.out.println("No se encontró un partido con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el partido: " + e.getMessage());
        }
    }
     
    public void viewPartido(Scanner sc) {
    Baloncesto bl = new Baloncesto();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = bl.Conexion();
        System.out.println("Ingrese el id del partido que desea ver:");
        int id = sc.nextInt();
        sc.nextLine();

        ps = con.prepareStatement("SELECT * FROM partido WHERE id = ?");
        ps.setInt(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("ID del partido: " + rs.getInt("id"));
            System.out.println("Equipo Local: " + rs.getString("equipo_local"));
            System.out.println("Equipo Visitante: " + rs.getString("equipo_visitante"));
            System.out.println("Cestas Local: " + rs.getInt("cestas_local"));
            System.out.println("Cestas Visitante: " + rs.getInt("cestas_visitante"));
            System.out.println("Estado: " + rs.getString("estado"));
            System.out.println("Fecha del partido: " + rs.getString("fecha_partido"));
        } else {
            System.out.println("No se encontró un partido con el ID proporcionado.");
        }
    } catch (SQLException e) {
        System.out.println("Error al ver el partido: " + e.getMessage());
    } finally {
        try { if (rs != null) rs.close(); } catch (SQLException e) { /* Ignored */ }
        try { if (ps != null) ps.close(); } catch (SQLException e) { /* Ignored */ }
        try { if (con != null) con.close(); } catch (SQLException e) { /* Ignored */ }
    }
}

    
 public void seeResults(Scanner sc) {
    Baloncesto bl = new Baloncesto();
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    try {
        con = bl.Conexion();
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM partido");

        while (rs.next()) {
            System.out.println("ID del partido: " + rs.getInt("id"));
            System.out.println("Equipo Local: " + rs.getString("equipo_local"));
            System.out.println("Equipo Visitante: " + rs.getString("equipo_visitante"));
            System.out.println("Cestas Local: " + rs.getInt("cestas_local"));
            System.out.println("Cestas Visitante: " + rs.getInt("cestas_visitante"));
            System.out.println("Estado: " + rs.getString("estado"));
            System.out.println("Fecha del partido: " + rs.getString("fecha_partido"));
            System.out.println("---------------------------------");
        }
    } catch (SQLException e) {
        System.out.println("Error al ver los resultados: " + e.getMessage());
    } finally {
        try { if (rs != null) rs.close(); } catch (SQLException e) { /* Ignored */ }
        try { if (st != null) st.close(); } catch (SQLException e) { /* Ignored */ }
        try { if (con != null) con.close(); } catch (SQLException e) { /* Ignored */ }
    }
}

     
 public void showwin(Scanner sc) {
    Baloncesto bl = new Baloncesto();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = bl.Conexion();
        System.out.println("Ingrese el id del partido para ver el ganador:");
        int id = sc.nextInt();
        sc.nextLine();

        ps = con.prepareStatement("SELECT equipo_local, cestas_local, equipo_visitante, cestas_visitante FROM partido WHERE id = ?");
        ps.setInt(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            String equipoLocal = rs.getString("equipo_local");
            int cestasLocal = rs.getInt("cestas_local");
            String equipoVisitante = rs.getString("equipo_visitante");
            int cestasVisitante = rs.getInt("cestas_visitante");

            if (cestasLocal > cestasVisitante) {
                System.out.println("El ganador es: " + equipoLocal);
            } else if (cestasLocal < cestasVisitante) {
                System.out.println("El ganador es: " + equipoVisitante);
            } else {
                System.out.println("El partido terminó en empate.");
            }
        } else {
            System.out.println("No se encontró un partido con el ID proporcionado.");
        }
    } catch (SQLException e) {
        System.out.println("Error al ver el ganador del partido: " + e.getMessage());
    } finally {
        try { if (rs != null) rs.close(); } catch (SQLException e) { /* Ignored */ }
        try { if (ps != null) ps.close(); } catch (SQLException e) { /* Ignored */ }
        try { if (con != null) con.close(); } catch (SQLException e) { /* Ignored */ }
    }
}

    
    
    
    public static void main(String[] args) {
        Baloncesto bl = new Baloncesto ();
        Scanner sc = new Scanner(System.in );
        bl.Conexion();
        bl.addPartido(sc);
        int option;
        
        do{
        
        System.out.println(" ¿ Que deseas hacer ?");
        
        System.out.println("1. Agregar partido");
        System.out.println("2. eliminar  partido");
        System.out.println("3. Mostrar información del partido");
        System.out.println("4. Mostrar resultado del partido");
        System.out.println("5. Mostrar ganador del partido");
        System.out.println("6. salir");
        option = sc.nextInt();
        sc.nextLine();
        
        switch(option){
                case 1:
                    bl.addPartido(sc);
                    break;
                case 2:
                    bl.deletePartido(sc);
                    break;
                case 3:
                    bl.viewPartido(sc);
                    break;
                case 4:
                    bl.seeResults(sc);
                    break;
                case 5:
                    bl.showwin(sc);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }   
            
        } while (option != 6);
        
        }
        
    }
    
    

    
