import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class HabitacionesDisponibles extends JFrame implements ActionListener {
    private JPanel miPanel;
    private JScrollPane scrollPane;
    private JMenuBar barraMenu;
    private JMenuItem menuReserva, menuHorarios,menuDatos;
    private JLabel titulo;
    private JTextField dni;
    private Button seleccionar;


    public HabitacionesDisponibles(final String tipoHabitacion, final int numCamasHabitacion, final String fechaLlegada, final String fechaSalida, final String tipoParking) throws SQLException, ClassNotFoundException {
        super();
        iniciarComponentes(tipoHabitacion, numCamasHabitacion, fechaLlegada, fechaSalida, tipoParking);
        //Asigna un titulo a la barra de titulo
        setTitle("Menú Hotel : Habitaciones Disponibles");
        //tamaño de la ventana
        setSize(700,330);
        //pone la ventana en el Centro de la pantalla
        setLocationRelativeTo(null);
        /*impide que la ventana cambie de tamaño*/
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void iniciarComponentes(String tipoHabitacion, int numCamasHabitacion, String fechaLlegada, String fechaSalida, String tipoParking) throws ClassNotFoundException, SQLException {

        /*
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = null;
        conexion = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/gestionhoteles", "guilleman", "tenismanza");

        Statement sentencia = conexion.createStatement();
*/
        //ConexionDB.ConectarDB();
        Statement sentencia = ConexionDB.miConexion.createStatement();
        int numHabEjemplo = 10;



        /*Inicia instancias de los componentes*/
        miPanel = new JPanel();
        miPanel.setLayout(null);
        menuReserva = new JMenuItem("Reserva");
        menuHorarios = new JMenuItem("Horarios");
        menuDatos = new JMenuItem("Datos");
        barraMenu = new JMenuBar();

        titulo = new JLabel();

        seleccionar = new Button("Consultar");

        menuReserva.setText("Reserva de Habitaciones");
        barraMenu.add(menuReserva);

        menuHorarios.setText("Consultar Horarios Trabajador");
        barraMenu.add(menuHorarios);

        menuDatos.setText("Consultar Datos Cliente/Empleado");
        barraMenu.add(menuDatos);

        setJMenuBar(barraMenu);

        /*Labels*/
        titulo.setBounds(10,0,250,30);
        titulo.setText("HABITACIONES DISPONIBLES:");

        ResultSet resultado = sentencia.executeQuery("SELECT * FROM Habitaciones WHERE TipoHabitacion = '" + tipoHabitacion + "' AND NumCamas = " + numCamasHabitacion /*+ " AND numHabitacion NOT IN (SELECT numHabitacion FROM Reserva WHERE fechaLlegada = '" + fechaLlegada + "' AND fechaSalida = '" + fechaSalida + "') "*/);

        int i = 0;
        while (resultado.next()) {
            JRadioButton radioButton1 = new JRadioButton("Habitación " + (i+1));
            JLabel labelDatos = new JLabel("Datos de la habitación " + (i+1) + ":");
            radioButton1.setBounds(10, 30 + (i*150), 200, 30);
            labelDatos.setBounds(50, radioButton1.getY()+30, 200, 30);
            JLabel labelNumHab = new JLabel("Número habitación " + resultado.getInt("numHabitacion"));
            labelNumHab.setBounds(50, labelDatos.getY()+20, 200, 30);
            JLabel labelTipoHab = new JLabel("Tipo habitación " + resultado.getString("TipoHabitacion"));
            labelTipoHab.setBounds(50, labelNumHab.getY()+20, 200, 30);
            JLabel labelNumCamas = new JLabel("Número de camas " + resultado.getInt("NumCamas"));
            labelNumCamas.setBounds(50, labelTipoHab.getY()+20, 200, 30);
            JLabel labelNumBanos = new JLabel("Número de baños " + resultado.getInt("NumBaños"));
            labelNumBanos.setBounds(50, labelNumCamas.getY()+20, 200, 30);
            miPanel.setPreferredSize(new Dimension(665, labelNumBanos.getY()+50));
            miPanel.add(radioButton1);
            miPanel.add(labelDatos);
            miPanel.add(labelNumHab);
            miPanel.add(labelTipoHab);
            miPanel.add(labelNumCamas);
            miPanel.add(labelNumBanos);

            seleccionar.setBounds(560,labelNumBanos.getY()+10,100,30);
            i++;
        }

        /*Añade los componentes al panel*/
        miPanel.add(titulo);
        miPanel.add(seleccionar);

        /*Añade el panel a la ventana*/
        add(miPanel);

        menuDatos.addActionListener(this);
        menuHorarios.addActionListener(this);
        seleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == seleccionar){
                    if (tipoParking == null){
                        DatosReserva datosReserva = new DatosReserva();
                        datosReserva.setVisible(true);
                    }else{
                        SeleccionarParking seleccionarParking = new SeleccionarParking();
                        seleccionarParking.setVisible(true);
                    }
                }
            }
        });

        scrollPane = new JScrollPane(miPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(700,330));
        this.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuHorarios){
            VentanaHorarios ventanaHorarios = new VentanaHorarios();
            ventanaHorarios.setVisible(true);

            this.setVisible(false);
        }

        if(e.getSource()==menuDatos){
            VentanaDatos ventanaDatos = new VentanaDatos();
            ventanaDatos.setVisible(true);

            this.setVisible(false);
        }
    }
}
