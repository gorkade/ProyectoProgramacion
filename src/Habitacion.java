public class Habitacion {
    int numHabitacion;
    int numCamas;
    String tipoHabitacion;
    int numBaños;
    double precio;
    String dniEmpleado;

    public Habitacion() {
    }

    public Habitacion(int numHabitacion, String tipoHabitacion, int numCamas, int numBaños, double precio, String dniEmpleado) {
        this.numHabitacion = numHabitacion;
        this.numCamas = numCamas;
        this.tipoHabitacion = tipoHabitacion;
        this.numBaños = numBaños;
        this.precio = precio;
        this.dniEmpleado = dniEmpleado;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public int getNumCamas() {
        return numCamas;
    }

    public void setNumCamas(int numCamas) {
        this.numCamas = numCamas;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getNumBaños() {
        return numBaños;
    }

    public void setNumBaños(int numBaños) {
        this.numBaños = numBaños;
    }

    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
