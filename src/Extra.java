import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Extra {
    //Clase Extras para guardar la informacion de los servicios extra seleccionados por el cliente

    //Atributos
    boolean bar;
    boolean restaurante;
    boolean actividades;
    boolean guarderia;
    boolean cajaFuerte;
    boolean descuentoFamiliaN;

    //Precios de los servicios extra

    //Nos conectamos a la base de datos para obtener los precios de los servicios extra

    Statement sentencia = ConexionDB.miConexion.createStatement();

    double precioBar;
    double precioRestaurante;
    double precioActividades;
    double precioGuarderia;
    double precioCajaFuerte;
    double porcentDescuentoFamiliaN = 0.1;

    //Constructores con y sin parametros
    public Extra() throws SQLException {
    }

    public Extra(boolean bar, boolean restaurante, boolean actividades, boolean guarderia, boolean cajaFuerte, boolean descuentoFamiliaN) throws SQLException {
        this.bar = bar;
        this.restaurante = restaurante;
        this.actividades = actividades;
        this.guarderia = guarderia;
        this.cajaFuerte = cajaFuerte;
        this.descuentoFamiliaN = descuentoFamiliaN;

        ResultSet resultado = sentencia.executeQuery("SELECT Precio FROM ServiciosExtra WHERE Tipo like 'Bar'");
        if(resultado.next()){
            this.precioBar = resultado.getDouble("Precio");
        }

        resultado = sentencia.executeQuery("SELECT Precio FROM ServiciosExtra WHERE Tipo like 'Restaurante'");
        if(resultado.next()){
            this.precioRestaurante = resultado.getDouble("Precio");
        }

        resultado = sentencia.executeQuery("SELECT Precio FROM ServiciosExtra WHERE Tipo like 'Acceso a Actividades'");
        if(resultado.next()){
            this.precioActividades = resultado.getDouble("Precio");
        }

        resultado = sentencia.executeQuery("SELECT Precio FROM ServiciosExtra WHERE Tipo like 'Guarderia'");
        if(resultado.next()){
            this.precioGuarderia = resultado.getDouble("Precio");
        }

        resultado = sentencia.executeQuery("SELECT Precio FROM ServiciosExtra WHERE Tipo like 'Caja Fuerte'");
        if(resultado.next()){
            this.precioCajaFuerte = resultado.getDouble("Precio");
        }
    }

    //Getters y Setters
    public boolean isBar() {
        return bar;
    }

    public boolean isRestaurante() {
        return restaurante;
    }

    public boolean isActividades() {
        return actividades;
    }

    public boolean isGuarderia() {
        return guarderia;
    }

    public boolean isCajaFuerte() {
        return cajaFuerte;
    }

    public boolean isDescuentoFamiliaN() {
        return descuentoFamiliaN;
    }

    public void setBar(boolean bar) {
        this.bar = bar;
    }

    public void setRestaurante(boolean restaurante) {
        this.restaurante = restaurante;
    }

    public void setActividades(boolean actividades) {
        this.actividades = actividades;
    }

    public void setGuarderia(boolean guarderia) {
        this.guarderia = guarderia;
    }

    public void setCajaFuerte(boolean cajaFuerte) {
        this.cajaFuerte = cajaFuerte;
    }

    public void setDescuentoFamiliaN(boolean descuentoFamiliaN) {
        this.descuentoFamiliaN = descuentoFamiliaN;
    }

    public double getPrecioBar() throws SQLException {
        return precioBar;
    }

    public double getPrecioRestaurante() throws SQLException {
        return precioRestaurante;
    }

    public double getPrecioActividades() throws SQLException {
        return precioActividades;
    }

    public double getPrecioGuarderia() throws SQLException {
        return precioGuarderia;
    }

    public double getPrecioCajaFuerte() throws SQLException {
        return precioCajaFuerte;
    }

    public double getPorcentDescuentoFamiliaN() {
        return porcentDescuentoFamiliaN;
    }

    //Método para calcular el precio total de los servicios extra seleccionados
    public double precioExtra(){
        double precioExtra = 0;
        if (bar) {
            precioExtra += precioBar;
        }
        if (restaurante) {
            precioExtra += precioRestaurante;
        }
        if (actividades) {
            precioExtra += precioActividades;
        }
        if (guarderia) {
            precioExtra += precioGuarderia;
        }
        if (cajaFuerte) {
            precioExtra += precioCajaFuerte;
        }
        return precioExtra;
    }
}
