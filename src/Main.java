import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        ConexionDB.ConectarDB();
        VentanaPrincipal miFrame= new VentanaPrincipal();
        miFrame.setVisible(true);
        miFrame.setResizable(true);

    }

    public static boolean verificarCamposVacios(String... campos) {
        //Recoremos cada TextField que pasemos por argumento
        for (String campo : campos) {
            if (campo.trim().isEmpty()) {
                return true; // Si algún campo está vacío, retorna verdadero
            }
        }
        return false; // Si ningún campo está vacío, retorna falso
    }
}