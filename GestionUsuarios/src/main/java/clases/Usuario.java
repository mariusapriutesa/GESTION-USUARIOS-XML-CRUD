
package clases;


public class Usuario {
    private String dni;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String fecha;

    public Usuario() {
          this.dni = "";
        this.nombre = "";
        this.direccion = "";
        this.telefono = "";
        this.correo = "";
        this.fecha = "";
        
        
    }

    public Usuario(String dni, String nombre, String direccion, String telefono, String correo, String fecha) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fecha = fecha;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
