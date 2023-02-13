public class Extra {
    boolean bar;
    boolean restaurante;
    boolean actividades;
    boolean guarderia;
    boolean cajaFuerte;
    boolean descuentoFamiliaN;

    double precioBar= 10;
    double precioRestaurante = 30;
    double precioActividades = 40;
    double precioGuarderia = 40;
    double precioCajaFuerte = 5;
    double porcentDescuentoFamiliaN = 0.1;

    public Extra() {
    }

    public Extra(boolean bar, boolean restaurante, boolean actividades, boolean guarderia, boolean cajaFuerte, boolean descuentoFamiliaN) {
        this.bar = bar;
        this.restaurante = restaurante;
        this.actividades = actividades;
        this.guarderia = guarderia;
        this.cajaFuerte = cajaFuerte;
        this.descuentoFamiliaN = descuentoFamiliaN;
    }

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

    public double getPrecioBar() {
        return precioBar;
    }

    public double getPrecioRestaurante() {
        return precioRestaurante;
    }

    public double getPrecioActividades() {
        return precioActividades;
    }

    public double getPrecioGuarderia() {
        return precioGuarderia;
    }

    public double getPrecioCajaFuerte() {
        return precioCajaFuerte;
    }

    public double getPorcentDescuentoFamiliaN() {
        return porcentDescuentoFamiliaN;
    }

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
