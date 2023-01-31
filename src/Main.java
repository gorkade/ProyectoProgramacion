public class Main {
    public static void main(String[] args) {
        ConexionDB.ConectarDB();
        VentanaPrincipal miFrame= new VentanaPrincipal();
        miFrame.setVisible(true);
        miFrame.setResizable(true);

    }
}