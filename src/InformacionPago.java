import javax.swing.*;

public class InformacionPago extends JFrame {

    private JPanel miPanel = new JPanel();

    private JMenuBar BarraMenu;
    private JMenuItem MenuReserva, MenuHorariosTrabajador,MenuDatosClientes;

    private JLabel InfoPago, NombreTitularTarj, NumTarjeta, FechaCaducidadTarjeta, CVV;

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

        enviar = new JButton();


        InfoPago.setBounds(0, 10, 150, 40);
        InfoPago.setText("Información del Pago:");

        NombreTitularTarj.setBounds(0, 40, 250, 40);
        NombreTitularTarj.setText("Nombre Titular de la Tarjeta: ");
        NombreTitularTarjetaa.setBounds(0, 60, 250, 40);

        NumTarjeta.setBounds(60, 40, 250, 40);
        NumTarjeta.setText("Numero de la Tarjeta");
        NumTarjetaa.setBounds(60, 60, 250, 40);

        FechaCaducidadTarjeta.setBounds(110,40, 100, 40);
        FechaCaducidadTarjeta.setText("Fecha Caducidad");
        FechaCaducidadTarjetaa.setBounds(110, 60, 100, 40);

        CVV.setBounds(160, 40, 100, 40);
        CVV.setText("CVV:");
        CVVV.setBounds(160,60,50,40);

        enviar.setBounds(160, 90, 50, 40);

        miPanel.add(InfoPago);
        miPanel.add(NombreTitularTarj);
        miPanel.add(NombreTitularTarjetaa);
        miPanel.add(NumTarjeta);
        miPanel.add(NumTarjetaa);
        miPanel.add(FechaCaducidadTarjeta);
        miPanel.add(FechaCaducidadTarjetaa);
        miPanel.add(CVV);
        miPanel.add(CVVV);
        miPanel.add(enviar);
        add(miPanel);

        enviar.setVisible(true);


    }



}
