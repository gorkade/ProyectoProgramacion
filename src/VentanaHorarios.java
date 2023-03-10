import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VentanaHorarios extends JFrame implements ActionListener{
    //Esta clase muestra la ventana de horarios de un trabajador

    //Atributos
    private JMenuItem menuReserva;
    private JMenuItem menuDatos;
    private JMenuItem menuHorarios;
    private JLabel labelHorario;
    private JLabel labelZona;
    private JTextField dni;

    private Button consultar;

    public VentanaHorarios() //constructor
    {
        //llamamos a método para crear la ventana y creamos el panel con sus componentes y la configuración posterior
        iniciarComponentes();
        //Asigna un titulo a la barra de titulo
        setTitle("Menú Hotel : Consultar Horarios");
        //tamaño de la ventana
        setSize(700,330);
        //pone la ventana en el Centro de la pantalla
        setLocationRelativeTo(null);
        /*impide que la ventana cambie de tamaño*/
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void iniciarComponentes() {

        /*Inicia instancias de los componentes*/
        JPanel miPanel = new JPanel();
        miPanel.setLayout(null);
        menuReserva = new JMenuItem("Reserva");
        menuHorarios = new JMenuItem("Horarios");
        menuDatos = new JMenuItem("Datos");
        JMenuBar barraMenu = new JMenuBar();

        JLabel titulo = new JLabel();
        JLabel labelDNI = new JLabel();
        dni = new JTextField();
        labelHorario = new JLabel();

        labelZona =new JLabel();

        consultar = new Button("Consultar");

        //Configuramos la posicion y los textos de los componentes
        titulo.setBounds(10,0,250,30);
        titulo.setText("CONSULTAR HORARIOS TRABAJADORES:");

        labelDNI.setBounds(10,20,60,30);
        labelDNI.setText("DNI: ");

        dni.setBounds(70,25,130,20);

        labelHorario.setBounds(10,60,650,30);
        labelHorario.setText("Horario: ");

        labelZona.setBounds(10,90,650,50);
        labelZona.setText("Zona: ");

        consultar.setBounds(300, 200, 100, 30);

        /*Agrega los Menus de la barra de Menu*/
        menuReserva.setText("Reserva de habitaciones");
        barraMenu.add(menuReserva);
        
        menuHorarios.setText("Consultar Horarios Trabajador");
        barraMenu.add(menuHorarios);

        menuDatos.setText("Consultar Datos Cliente/Empleado");
        barraMenu.add(menuDatos);

        //Agrega la barra de menu a la ventana
        setJMenuBar(barraMenu);

        //añadimos los Listeners necesarios
        consultar.addActionListener(this);
        menuReserva.addActionListener(this);
        menuDatos.addActionListener(this);

        //Agregamos los componentes al panel
        miPanel.add(titulo);
        miPanel.add(labelDNI);
        miPanel.add(dni);
        miPanel.add(labelHorario);
        miPanel.add(labelZona);
        miPanel.add(consultar);
        add(miPanel);

        consultar.setVisible(true);
        //escondemos los componentes que no se muestran al principio
        labelHorario.setVisible(false);
        labelZona.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //programamos el boton consultar
        if (e.getSource() == consultar) {

            try {
                //conectamos con la base de datos y consultamos los datos del trabajador con el dni introducido
                Statement sentencia = ConexionDB.miConexion.createStatement();
                ResultSet resultado = sentencia.executeQuery("SELECT * FROM Empleado WHERE DNI like '" + dni.getText() + "'");

                if (resultado.next()) {
                    //si el dni existe en la base de datos, mostramos el horario y la zona del trabajador
                    labelHorario.setText("Horario:      Hora Inicio: " + resultado.getString("horaInicio") + " Hora Fin: " + resultado.getString("horaFinal"));
                    labelZona.setText("Zona: " + resultado.getString("zona"));
                }else{
                    //si el dni no existe en la base de datos, mostramos un mensaje de error
                    JOptionPane.showMessageDialog(null,"No se ha encontrado el Empleado");
                }
            } catch (SQLException ex) {
                //si hay algún error en la consulta, mostramos un mensaje de error
                throw new RuntimeException(ex);
            }

            //mostramos los componentes que contiene el horario y la zona del trabajador
            labelHorario.setVisible(true);
            labelZona.setVisible(true);
        }

        if (e.getSource() == menuReserva) {
            //si pulsamos el boton de reserva, abrimos la ventana inicial de reserva
            InicioReserva miFrame= new InicioReserva();
            miFrame.setVisible(true);
            this.setVisible(false);
        }

        if(e.getSource()==menuDatos){
            //si pulsamos el boton de datos, abrimos la ventana de datos
            VentanaDatos ventanaDatos = new VentanaDatos();
            ventanaDatos.setVisible(true);

            this.setVisible(false);
        }

    }
}
