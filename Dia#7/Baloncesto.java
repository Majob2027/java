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
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("Config.properties")) {
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
    
