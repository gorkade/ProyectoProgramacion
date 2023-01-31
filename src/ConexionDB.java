import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexionDB {

    public static void ConectarDB(){



        try {

            //1.CREAR CONEXION
            Connection miConexion= DriverManager.getConnection("jdbc:mysql://db4free.net/gestionhoteles",
                    "guilleman","tenismanza");
            //2.CREAR OBJETO STATEMENT
            Statement miStatement= miConexion.createStatement();

        }catch(Exception e) {

            JOptionPane.showMessageDialog(null,"Error: No se ha establecido conexion con la base de datos");




        }
    }
}
