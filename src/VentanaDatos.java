import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VentanaDatos extends JFrame implements ActionListener {
    private JPanel miPanel;//contenedor de los componentes
    private JMenuBar barraMenu;
    private JMenuItem menuReserva, menuHorarios,menuDatos;

    private JComboBox comboTipoPersona;

    String[] tiposPersonas = {"Seleccionar Tipo...", "Cliente", "Empleado"};
    private JLabel titulo, labelTipoPersona, labelDNI, labelDatos, labelDatos2;
    private JTextField dni;

    private Button consultar, añadir;

    public VentanaDatos()//constructor
    {
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

        /**/

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

        /*Labels*/
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

        setJMenuBar(barraMenu);

        consultar.addActionListener(this);
        menuReserva.addActionListener(this);
        menuHorarios.addActionListener(this);

        añadir.addActionListener(p -> {
            File archivo = new File("empleados.txt");
            try {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String linea;

                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(";");
                    String DNI = datos[0];
                    String NumEmpleado = datos[1];
                    String Nombre = datos[2];
                    String Apellido = datos[3];
                    String HoraInicio = datos[4];
                    String HoraFinal = datos[5];
                    String Zona = datos[6];
                    String TipoEmpleado = datos[7];
                    String Salario = datos[8];

                    Statement st = ConexionDB.miConexion.createStatement();

                    ResultSet rs = st.executeQuery("SELECT * FROM Empleado WHERE DNI = '" + DNI + "'");

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
        if (e.getSource() == consultar) {
            Statement miStatement = null;
            String DNI =  dni.getText();

            if (comboTipoPersona.getSelectedItem().equals("Empleado")) {
                   try {
                        miStatement = ConexionDB.miConexion.createStatement();
                       ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM Empleado where DNI like '" + DNI + "'");
                       if (miResultSet.next()) {
                           labelDatos.setText("Datos: ");
                           labelDatos.setText("NumEpleado: " + miResultSet.getString("NumEmpleado") +
                                   " Nombre: " + miResultSet.getString("Nombre") + " Aplleidos: " + miResultSet.getString("Apellido") +
                                   " Zona: " + miResultSet.getString("Zona") + " Tipo: " + miResultSet.getString("TipoEmpleado") );

                           labelDatos2.setText("Salario: " + miResultSet.getString("Salario")  );

                       }else{
                           JOptionPane.showMessageDialog(null,"No se ha encontrado el Empleado");
                       }
                   } catch (SQLException ex) {
                       JOptionPane.showMessageDialog(null,"Error al buscar los datos");
                   }
               }else if (comboTipoPersona.getSelectedItem().equals("Cliente")) {
                try {
                    miStatement = ConexionDB.miConexion.createStatement();
                    ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM Cliente where DNI like '"+DNI+"'");
                    if(miResultSet.next()) {
                        labelDatos.setText("Datos: ");
                        labelDatos.setText("Nombre: " + miResultSet.getString("Nombre") + " Aplleidos: " + miResultSet.getString("Apellido") +
                                 " Telefono: " + miResultSet.getString("Telf") + " Email: " + miResultSet.getString("Email") );

                        labelDatos2.setText("Dirección: " + miResultSet.getString("Direccion") + " Pais: " + miResultSet.getString("Pais") +
                                " Ciudad: " + miResultSet.getString("Ciudad") + " CP: " + miResultSet.getString("CP") );
                    }else{
                        JOptionPane.showMessageDialog(null,"No se ha encontrado el cliente");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Error al buscar los datos");
                }
            }
            labelDatos.setVisible(true);
        }

        if (e.getSource() == menuReserva) {
            VentanaPrincipal miFrame= new VentanaPrincipal();
            miFrame.setVisible(true);
            this.setVisible(false);
        }

        if (e.getSource() == menuHorarios) {

                VentanaHorarios miFrame = new VentanaHorarios();

                miFrame.setVisible(true);
                this.setVisible(false);
        }

    }
}
