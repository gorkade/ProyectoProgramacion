import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        //Conectamos con la base de datos
        ConexionDB.ConectarDB();

        //Iniciamos el programa con la ventana inicial
        PantallaInicial miFrame= new PantallaInicial();
        miFrame.setVisible(true);
        miFrame.setResizable(true);

    }

    public static boolean verificarCamposVacios(String... campos) {
        //Recoremos cada String que pasemos por argumento
        for (String campo : campos) {
            if (campo.trim().isEmpty()) {
                return true; // Si algún campo está vacío, retorna verdadero
            }
        }
        return false; // Si ningún campo está vacío, retorna falso
    }
}