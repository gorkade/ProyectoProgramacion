import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class InformacionPago extends JFrame {

    private JPanel miPanel = new JPanel();

    private JMenuBar BarraMenu;
    private JMenuItem MenuReserva, MenuHorariosTrabajador,MenuDatosClientes;

    private JLabel InfoPago, NombreTitularTarj, NumTarjeta, FechaCaducidadTarjeta, CVV, DNII;

    private JButton enviar;

    public InformacionPago(){

        IniciarComponentes();
        //Asigna un titulo a la barra de titulo
        setTitle("Menú Hotel : Informacion de pago");
        //tamaño de la ventana
        setSize(700,330);
        //pone la ventana en el Centro de la pantalla
        setLocationRelativeTo(null);
        /*impide que la ventana cambie de tamaño*/
        setResizable(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void IniciarComponentes(){

        miPanel = new JPanel();
        miPanel.setLayout(null);

        //Menu superior de la ventana
        BarraMenu= new JMenuBar();

        MenuReserva= new JMenuItem("Reserva");
        BarraMenu.add(MenuReserva);

        MenuHorariosTrabajador= new JMenuItem("Consultar Horarios Trabajador");
        BarraMenu.add(MenuHorariosTrabajador);

        MenuDatosClientes= new JMenuItem("Datos Clientes");
        BarraMenu.add(MenuDatosClientes);

        setJMenuBar(BarraMenu);

        InfoPago = new JLabel();
        NombreTitularTarj = new JLabel();
        JTextField NombreTitularTarjetaa = new JTextField ();
        NumTarjeta = new JLabel();
        JTextField NumTarjetaa = new JTextField();
        FechaCaducidadTarjeta = new JLabel();
        JTextField FechaCaducidadTarjetaa = new JTextField();
        CVV = new JLabel();
        JTextField CVVV = new JTextField();

        DNII = new JLabel();
        JTextField DNIT = new JTextField();

        enviar = new JButton();


        InfoPago.setBounds(10, 10, 150, 40);
        InfoPago.setText("Información del Pago:");

        NombreTitularTarj.setBounds(10, 40, 250, 40);
        NombreTitularTarj.setText("Nombre Titular de la Tarjeta: ");
        NombreTitularTarjetaa.setBounds(10, 70, 250, 40);

        DNII.setBounds(10, 110,250, 40);
        DNII.setText("DNI: ");
        DNIT.setBounds(50, 110, 150, 40);

        NumTarjeta.setBounds(10, 145, 250, 40);
        NumTarjeta.setText("Numero de la Tarjeta");
        NumTarjetaa.setBounds(10, 175, 250, 40);

        FechaCaducidadTarjeta.setBounds(300,40, 170, 40);
        FechaCaducidadTarjeta.setText("Fecha Caducidad");
        FechaCaducidadTarjetaa.setBounds(295, 70, 170, 40);

        CVV.setBounds(530, 40, 100, 40);
        CVV.setText("CVV:");
        CVVV.setBounds(525,70,50,40);

        enviar.setBounds(530, 220, 170, 40);
        enviar.setText("Enviar");

        miPanel.add(InfoPago);
        miPanel.add(NombreTitularTarj);
        miPanel.add(NombreTitularTarjetaa);
        miPanel.add(DNII);
        miPanel.add(DNIT);
        miPanel.add(NumTarjeta);
        miPanel.add(NumTarjetaa);
        miPanel.add(FechaCaducidadTarjeta);
        miPanel.add(FechaCaducidadTarjetaa);
        miPanel.add(CVV);
        miPanel.add(CVVV);
        miPanel.add(enviar);
        add(miPanel);

        enviar.setVisible(true);
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == enviar) {
                    //Recogemos el texto de los JTextFields
                    String NombreTitular = NombreTitularTarjetaa.getText();
                    String DNI = DNIT.getText();
                    String NumeroTarjeta = NumTarjetaa.getText();
                    String FechaCaducidadd = FechaCaducidadTarjetaa.getText();
                    String CVVP = CVVV.getText();


                        try {
                            //Conectamos a la base de datos y realizamos una consulta para verificar si ese cliente ya existe
                            Statement miStatement = ConexionDB.miConexion.createStatement();
                            String instruccionSQL = "INSERT INTO Pago (DNI, CVV, NumTargeta, Titular, FechaCaducidad) VALUES ('"+DNI+"','"+CVVP+"','"+NumeroTarjeta+"','"+NombreTitular+"','"+FechaCaducidadd+"')";
                            miStatement.executeUpdate(instruccionSQL);

                        }catch(Exception ex) {
                            System.out.println(ex);
                            JOptionPane.showMessageDialog(null,"Error: No se ha podido insertar los datos");
                        }


                }
            }
        });

    }



}
