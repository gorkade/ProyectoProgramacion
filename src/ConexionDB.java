import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//Clase para conectar a la base de datos
public class ConexionDB {


    static Connection miConexion = null;

    public ConexionDB(Connection miConexion) {
        ConexionDB.miConexion = miConexion;
    }

    //Método que realiza la conexion
    public static void ConectarDB() {
        try {

            //1.CREAR CONEXION
            miConexion = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/gestionhoteles",
                    "guilleman", "tenismanza");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: No se ha establecido conexion con la base de datos");
        }

    }
}
//como que hay que hacer consulta    yo tengo en myphpadmin cosas mira