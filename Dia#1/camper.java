public class Camper {
    private String nombre;
    private int identificacion;
    private String apellidos;
    private String direccion;
    private int telefono;
    private String estado;
    private String riesgo;

    // Constructor completo
    public Camper(String nombre, int identificacion, String apellidos, String direccion, int telefono, String estado, String riesgo) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
        this.riesgo = riesgo;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Identificación: " + identificacion + ", Apellidos: " + apellidos +
               ", Dirección: " + direccion + ", Teléfono: " + telefono + ", Estado: " + estado + ", Riesgo: " + riesgo;
    }
}


 
