public class Cliente {
    String nombre;
    String apellidos;
    String nif;
    String direccion;
    String telefono;
    String email;
    String ciudad;
    String cp;
    String pais;

    public Cliente() {
    }

    public Cliente(String nombre, String apellidos, String nif, String direccion, String telefono, String email, String ciudad, String cp, String pais) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.ciudad = ciudad;
        this.cp = cp;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNif() {
        return nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCp() {
        return cp;
    }

    public String getPais() {
        return pais;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
