import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        ConexionDB.ConectarDB();
        VentanaPrincipal miFrame= new VentanaPrincipal();
        miFrame.setVisible(true);
        miFrame.setResizable(true);

    }
}