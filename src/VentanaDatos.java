import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VentanaDatos extends JFrame implements ActionListener {
    //Esta clase muestra la ventana de datos de las personas

    //Atributos
    private JPanel miPanel;
    private JMenuBar barraMenu;
    private JMenuItem menuReserva, menuHorarios,menuDatos;

    private JComboBox comboTipoPersona;

    String[] tiposPersonas = {"Seleccionar Tipo...", "Cliente", "Empleado"};
    private JLabel titulo, labelTipoPersona, labelDNI, labelDatos, labelDatos2;
    private JTextField dni;

    private Button consultar, añadir;

    public VentanaDatos()//constructor
    {
        //llamamos a método para crear la ventana y creamos el panel con sus componentes y la configuración posterior
        iniciarComponentes();
        //Asigna un titulo a la barra de titulo
        setTitle("Menú Hotel : Consultar Datos");
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
        miPanel = new JPanel();
        miPanel.setLayout(null);
        menuReserva = new JMenuItem("Reserva");
        menuHorarios = new JMenuItem("Horarios");
        menuDatos = new JMenuItem("Datos");
        barraMenu = new JMenuBar();
        añadir = new Button("Añadir Empleado Partiendo de un Archivo");

        titulo = new JLabel();
        labelTipoPersona = new JLabel();
        comboTipoPersona = new JComboBox(tiposPersonas);
        dni = new JTextField();
        labelDNI = new JLabel();
        labelDatos = new JLabel();
        labelDatos2 = new JLabel();


        consultar = new Button("Consultar");

        //Configuramos la posicion y los textos de los componentes
        titulo.setBounds(10,0,250,30);
        titulo.setText("DATOS PERSONAS:");

        labelTipoPersona.setBounds(10,20,120,30);
        labelTipoPersona.setText("Tipo de Persona: ");

        comboTipoPersona.setBounds(130,25,130,20);

        añadir.setBounds(300,25,250,20);

        labelDNI.setBounds(10,45,150,30);
        labelDNI.setText("Documento de Identidad: ");

        dni.setBounds(160,50,130,20);

        labelDatos.setBounds(10,70,650,100);
        labelDatos2.setBounds(10,90,650,100);



        consultar.setBounds(300, 200, 100, 30);

        /*Agrega los Menus de la barra de Menu*/
        menuReserva.setText("Reserva de habitaciones");
        barraMenu.add(menuReserva);

        menuHorarios.setText("Consultar Horarios Trabajador");
        barraMenu.add(menuHorarios);

        menuDatos.setText("Consultar Datos Cliente/Empleado");
        barraMenu.add(menuDatos);

        //Añadimos la barra del menu a la ventana
        setJMenuBar(barraMenu);

        //Añadimos los Listener a los botones
        consultar.addActionListener(this);
        menuReserva.addActionListener(this);
        menuHorarios.addActionListener(this);

        //programamos el boton añadir para añadir empleados a partir de un archivo
        añadir.addActionListener(p -> {
            //Creamos un objeto File para abrir el archivo
            File archivo = new File("empleados.txt");
            try {
                //Creamos un objeto FileReader y BufferedReader para leer el archivo
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String linea;

                //leemos el archivo linea a linea
                while ((linea = br.readLine()) != null) {
                    //separamos los datos de cada linea por el caracter ";" y los guardamos en un array
                    String[] datos = linea.split(";");
                    //Gurdamos todos los datos en variables
                    String DNI = datos[0];
                    String NumEmpleado = datos[1];
                    String Nombre = datos[2];
                    String Apellido = datos[3];
                    String HoraInicio = datos[4];
                    String HoraFinal = datos[5];
                    String Zona = datos[6];
                    String TipoEmpleado = datos[7];
                    String Salario = datos[8];

                    //Nos conectamos a la base de datos
                    Statement st = ConexionDB.miConexion.createStatement();

                    //Con esta consulta comprobamos si el empleado ya existe en la base de datos
                    ResultSet rs = st.executeQuery("SELECT * FROM Empleado WHERE DNI = '" + DNI + "'");

                    //Si el empleado no existe en la base de datos, lo añadimos
                    if (!rs.next()) {
                        st.executeUpdate("INSERT INTO Empleado VALUES ('" + DNI + "','" + NumEmpleado + "','" + Nombre + "','" + Apellido + "','" + HoraInicio + "','" + HoraFinal + "','" + Zona + "','" + TipoEmpleado + "','" + Salario + "')");
                    }
                }
                JOptionPane.showMessageDialog(null, "Empleados leídos y añadidos correctamente");

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //Añadimos los componentes al panel
        miPanel.add(titulo);
        miPanel.add(labelTipoPersona);
        miPanel.add(comboTipoPersona);
        miPanel.add(dni);
        miPanel.add(añadir);
        miPanel.add(labelDNI);
        miPanel.add(labelDatos);
        miPanel.add(labelDatos2);
        miPanel.add(consultar);
        add(miPanel);


        labelDatos.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //programamos el boton consultar
        if (e.getSource() == consultar) {
            Statement miStatement = null;
            //Recogemos el DNI de la persona que queremos consultar
            String DNI =  dni.getText();

            //Si es un empleado
            if (comboTipoPersona.getSelectedItem().equals("Empleado")) {
                   try {
                       //Nos conectamos a la BD y consultamos todos los datos del empleado
                        miStatement = ConexionDB.miConexion.createStatement();
                       ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM Empleado where DNI like '" + DNI + "'");

                       //Mostramos los resultados si dicho empleado existe en la base de datos
                       if (miResultSet.next()) {
                           labelDatos.setText("Datos: ");
                           labelDatos.setText("NumEpleado: " + miResultSet.getString("NumEmpleado") +
                                   " Nombre: " + miResultSet.getString("Nombre") + " Aplleidos: " + miResultSet.getString("Apellido") +
                                   " Zona: " + miResultSet.getString("Zona") + " Tipo: " + miResultSet.getString("TipoEmpleado") );

                           labelDatos2.setText("Salario: " + miResultSet.getString("Salario")  );

                       }else{
                           //Si no existe, mostramos un mensaje de error
                           JOptionPane.showMessageDialog(null,"No se ha encontrado el Empleado");
                       }
                   } catch (SQLException ex) {
                       //SI hay algun error en la conexion, mostramos un mensaje de error
                       JOptionPane.showMessageDialog(null,"Error al buscar los datos");
                   }
                   //Si es un cliente
               }else if (comboTipoPersona.getSelectedItem().equals("Cliente")) {
                try {
                    //Nos conectamos a la BD y consultamos todos los datos del empleado
                    miStatement = ConexionDB.miConexion.createStatement();
                    ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM Cliente where DNI like '"+DNI+"'");

                    //Mostramos los resultados si dicho cliente existe en la base de datos
                    if(miResultSet.next()) {
                        labelDatos.setText("Datos: ");
                        labelDatos.setText("Nombre: " + miResultSet.getString("Nombre") + " Aplleidos: " + miResultSet.getString("Apellido") +
                                 " Telefono: " + miResultSet.getString("Telf") + " Email: " + miResultSet.getString("Email") );

                        labelDatos2.setText("Dirección: " + miResultSet.getString("Direccion") + " Pais: " + miResultSet.getString("Pais") +
                                " Ciudad: " + miResultSet.getString("Ciudad") + " CP: " + miResultSet.getString("CP") );
                    }else{
                        //Si no existe el cliente, mostramos un mensaje de error
                        JOptionPane.showMessageDialog(null,"No se ha encontrado el cliente");
                    }
                } catch (SQLException ex) {
                    //SI hay algun error en la conexion, mostramos un mensaje de error
                    JOptionPane.showMessageDialog(null,"Error al buscar los datos");
                }
            }
            //Hacemos visible los labels donde se muestran los datos
            labelDatos.setVisible(true);
        }

        if (e.getSource() == menuReserva) {
            //Abrimos la ventana de inicial de reserva
            InicioReserva miFrame= new InicioReserva();
            miFrame.setVisible(true);
            this.setVisible(false);
        }

        if (e.getSource() == menuHorarios) {

            //Abrimos la ventana de horarios
                VentanaHorarios miFrame = new VentanaHorarios();

                miFrame.setVisible(true);
                this.setVisible(false);
        }

    }
}
