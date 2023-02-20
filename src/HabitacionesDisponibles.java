import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class HabitacionesDisponibles extends JFrame implements ActionListener {
    //Clase para mostrar las habitaciones que concuerdan con los parámetros de búsqueda

    //Atributos
    private JMenuItem menuHorarios;
    private JMenuItem menuDatos;
    private JTextField dni;
    private Button seleccionar;


    //Constructor
    public HabitacionesDisponibles(final String tipoHabitacion, final int numCamasHabitacion, final String fechaLlegada, final String fechaSalida, int dias, final String tipoParking) throws SQLException{
        super();
        //llamamos a método para crear la ventana y creamos el panel con sus componentes y la configuración posterior
        iniciarComponentes(tipoHabitacion, numCamasHabitacion, fechaLlegada, fechaSalida, dias, tipoParking);
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

    private void iniciarComponentes(String tipoHabitacion, int numCamasHabitacion, String fechaLlegada, String fechaSalida, int dias, String tipoParking) throws  SQLException {

        //nos conectamos a la base de datos
        Statement sentencia = ConexionDB.miConexion.createStatement();

        /*Inicia instancias de los componentes y sus campos de texto*/
        JPanel miPanel = new JPanel();
        miPanel.setLayout(null);
        JMenuItem menuReserva = new JMenuItem("Reserva");
        menuHorarios = new JMenuItem("Horarios");
        menuDatos = new JMenuItem("Datos");
        JMenuBar barraMenu = new JMenuBar();

        JLabel titulo = new JLabel();

        seleccionar = new Button("Consultar");

        //configuramos la barra de menu superior
        menuReserva.setText("Reserva de Habitaciones");
        barraMenu.add(menuReserva);

        menuHorarios.setText("Consultar Horarios Trabajador");
        barraMenu.add(menuHorarios);

        menuDatos.setText("Consultar Datos Cliente/Empleado");
        barraMenu.add(menuDatos);

        //añadimos la barra de menu superior a la ventana
        setJMenuBar(barraMenu);

        //Configuramos la posicion y los textos de los componentes
        titulo.setBounds(10,0,250,30);
        titulo.setText("HABITACIONES DISPONIBLES:");

        //consultamos las habitaciones que concuerdan con los parámetros de búsqueda
        ResultSet resultado = sentencia.executeQuery("SELECT * FROM Habitaciones WHERE TipoHabitacion = '" + tipoHabitacion + "' AND NumCamas = " + numCamasHabitacion /*+ " AND numHabitacion NOT IN (SELECT numHabitacion FROM Reserva WHERE fechaLlegada = '" + fechaLlegada + "' AND fechaSalida = '" + fechaSalida + "') "*/);

        
        int i = 0;
        //Creamos un grupo de botones para que solo se pueda seleccionar una habitación
        ButtonGroup grupoBotones = new ButtonGroup();
        //creamos un array de habitaciones para mostrar las habitaciones disponibles
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
        
        //recorremos el resultado de la consulta para generar los botones de las habitaciones disponibles con la información de cada una
        while (resultado.next()) {

            //Creamos el boton
            JRadioButton radioButton1 = new JRadioButton("Habitación " + (i+1));

            //Configuramos la posición y los textos de los datos de lahabitación
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

            JLabel labelPrecio= new JLabel("Precio total " + resultado.getDouble("Precio"));

            labelPrecio.setBounds(50,labelNumBanos.getY()+20,200,30);

            //Redimensionamos el panel para que se ajuste a las habitaciones disponibles
            miPanel.setPreferredSize(new Dimension(665, labelNumBanos.getY()+50));

            //añadimos el boton al grupo de botones
            grupoBotones.add(radioButton1);

            //añadimos los componentes al panel
            miPanel.add(radioButton1);
            miPanel.add(labelDatos);
            miPanel.add(labelNumHab);
            miPanel.add(labelTipoHab);
            miPanel.add(labelNumCamas);
            miPanel.add(labelNumBanos);
            miPanel.add(labelPrecio);

            //modificamos la posición del boton "Seleccionar" para que se muestre debajo de la última habitación
            seleccionar.setBounds(560,labelNumBanos.getY()+10,100,30);
            //Generamos un objeto de tipo Habitacion con los datos de la habitación y lo gurdamos en el array de habitaciones
            Habitacion h = new Habitacion(resultado.getInt("numHabitacion"), resultado.getString("TipoHabitacion"), resultado.getInt("NumCamas"), resultado.getInt("NumBaños"), resultado.getInt("Precio"), resultado.getString("DNIEmpleado"));
            habitaciones.add(h);
            //incrementamos el contador
            i++;
        }


        /*Añade los componentes al panel*/
        miPanel.add(titulo);
        miPanel.add(seleccionar);

        /*Añade el panel a la ventana*/
        add(miPanel);

        //añadimos Listeners a los botones
        menuDatos.addActionListener(this);
        menuHorarios.addActionListener(this);
        seleccionar.addActionListener(e -> {
            if(e.getSource() == seleccionar){
                //Comprobamos que boton de habitación está seleccionado
                GroupButtonUtils groupButtonUtils = new GroupButtonUtils();
                String numHabitacion = groupButtonUtils.getSelectedButtonText(grupoBotones);
                int numHab = Integer.parseInt(numHabitacion.substring(11));

                if (tipoParking == null){
                    //abrimos la ventana de datosde reserva de habitaciones pasando como parametros la habitación seleccionada, las fechas de entrada y salida y los dias de estancia
                    this.setVisible(false);
                    DatosReserva datosReserva = new DatosReserva(habitaciones.get(numHab-1), fechaLlegada, fechaSalida, dias);
                    datosReserva.setVisible(true);

                }else{
                    //abrimos la ventana de datosde reserva de habitaciones pasando como parametros la habitación seleccionada, las fechas de entrada y salida y los dias de estancia
                    Parking seleccionarParking = new Parking(48, habitaciones.get(numHab-1), fechaLlegada, fechaSalida, dias);
                    seleccionarParking.setVisible(true);
                    this.setVisible(false);


                }
           }
        });

        //hacemos que el panel se pueda scrollear si no cabe en la ventana;
        JScrollPane scrollPane = new JScrollPane(miPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(700,330));
        this.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==menuHorarios){
            //abrimos la ventana de horarios
            VentanaHorarios ventanaHorarios = new VentanaHorarios();

            ventanaHorarios.setVisible(true);

            this.setVisible(false);
        }

        if(e.getSource()==menuDatos){
            //abrimos la ventana de datos
            VentanaDatos ventanaDatos = new VentanaDatos();
            ventanaDatos.setVisible(true);

            this.setVisible(false);
        }
    }
}
