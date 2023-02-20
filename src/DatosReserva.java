import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatosReserva extends JFrame {

    //Esta clase genera la ventana para guardar los datos del cliente para realizar la reserva.

    //variables
    private JComboBox comboPais;
    public static String DNI;

    String[] pais = {"Afganistán","Albania","Alemania","Andorra","Angola","Antigua y Barbuda","Arabia Saudita","Argelia","Argentina","Armenia","Australia","Austria","Azerbaiyán","Bahamas","Bangladés","Barbados","Baréin","Bélgica","Belice","Benín","Bielorrusia","Birmania","Bolivia","Bosnia y Herzegovina","Botsuana","Brasil","Brunéi","Bulgaria","Burkina Faso","Burundi","Bután","Cabo Verde","Camboya","Camerún","Canadá","Catar","Chad","Chile","China","Chipre","Ciudad del Vaticano","Colombia","Comoras","Corea del Norte","Corea del Sur","Costa de Marfil","Costa Rica","Croacia","Cuba","Dinamarca","Dominica","Ecuador","Egipto","El Salvador","Emiratos Árabes Unidos","Eritrea","Eslovaquia","Eslovenia","España","Estados Unidos","Estonia","Etiopía","Filipinas","Finlandia","Fiyi","Francia","Gabón","Gambia","Georgia","Ghana","Granada","Grecia","Guatemala","Guyana","Guinea","Guinea ecuatorial","Guinea-Bisáu","Haití","Honduras","Hungría","India","Indonesia","Irak","Irán","Irlanda","Islandia","Islas Marshall","Islas Salomón","Israel","Italia","Jamaica","Japón","Jordania","Kazajistán","Kenia","Kirguistán","Kiribati","Kuwait","Laos","Lesoto","Letonia","Líbano","Liberia","Libia","Liechtenstein","Lituania","Luxemburgo","Madagascar","Malasia","Malaui","Maldivas","Malí","Malta","Marruecos","Mauricio","Mauritania","México","Micronesia","Moldavia","Mónaco","Mongolia","Montenegro","Mozambique","Namibia","Nauru","Nepal","Nicaragua","Níger","Nigeria","Noruega","Nueva Zelanda","Omán","Países Bajos","Pakistán","Palaos","Palestina","Panamá","Papúa Nueva Guinea","Paraguay","Perú","Polonia","Portugal","Reino Unido","República Centroafricana","República Checa","República de Macedonia","República del Congo","República Democrática del Congo","República Dominicana","República Sudafricana","Ruanda","Rumanía","Rusia","Samoa","San Cristóbal y Nieves","San Marino","San Vicente y las Granadinas","Santa Lucía","Santo Tomé y Príncipe","Senegal","Serbia","Seychelles","Sierra Leona","Singapur","Siria","Somalia","Sri Lanka","Suazilandia","Sudán","Sudán del Sur","Suecia","Suiza","Surinam","Tailandia","Tanzania","Tayikistán","Timor Oriental","Togo","Tonga","Trinidad y Tobago","Túnez","Turkmenistán","Turquía","Tuvalu","Ucrania","Uganda","Uruguay","Uzbekistán","Vanuatu","Venezuela","Vietnam","Yemen","Yibuti","Zambia","Zimbabue"};

    //constructor
    public DatosReserva(Habitacion habitacion, String fechaLlegada, String fechaSalida, int dias)//constructor
    {
        //llamamos a método para crear la ventana y creamos el panel con sus componentes y la configuración posterior
        iniciarComponentes(habitacion, fechaLlegada, fechaSalida, dias);
        //Asigna un titulo a la barra de titulo
        setTitle("Menú de Reserva Ejemplo : Titulo De La ventana");
        //tamaño de la ventana
        setSize(700,340);
        //pone la ventana en el Centro de la pantalla
        setLocationRelativeTo(null);
        /*impide que la ventana cambie de tamaño*/
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(true);
    }


    //Metodo que genera los componentes de la ventana
    private void iniciarComponentes(Habitacion habitacion, String fechaLlegada, String fechaSalida, int dias) {

        //nos conectamos a la base de datos
        ConexionDB.ConectarDB();


        /*Inicia instancias de los componentes*/
        JPanel miPanel = new JPanel();
        miPanel.setLayout(null);
        JMenu menuReserva = new JMenu("Reserva");
        JMenu menuHorarios = new JMenu("Horarios");
        JMenu menuDatos = new JMenu("Datos");
        JMenuBar barraMenu = new JMenuBar();
        comboPais = new JComboBox(pais);

        JTextField Nombre = new JTextField();
        JTextField Apellidos = new JTextField();
        JTextField NIF = new JTextField();
        JTextField Direccion = new JTextField();
        JTextField Telefono = new JTextField();
        JTextField Ciudad = new JTextField();
        JTextField CP = new JTextField();
        JTextField Email = new JTextField();

        JLabel labelNombre = new JLabel();
        JLabel labelApellidos = new JLabel();
        JLabel labelNIF = new JLabel();

        JLabel labelDireccion = new JLabel();
        JLabel labelTelefono = new JLabel();
        JLabel labelEmail = new JLabel();

        JLabel labelCiudad = new JLabel();
        JLabel labelPais = new JLabel();
        JLabel labelCP = new JLabel();

        Button enviar = new Button("Enviar");
        /*Fin instancias de los componentes*/

        //Configuramos la posicion y los textos de los componentes
        labelNombre.setBounds(10,0,200,30);
        labelNombre.setText("Nombre : ");
        Nombre.setBounds(70,5, 100, 20);


        labelApellidos.setBounds(10,35,100,30);
        labelApellidos.setText("Apellidos : ");
        Apellidos.setBounds(90, 40, 100, 20);

        labelNIF.setBounds(10,70,100,30);
        labelNIF.setText("NIF: ");
        NIF.setBounds(40, 75, 100, 20);

        labelDireccion.setBounds(10,105,100,30);
        labelDireccion.setText("Dirección : ");
        Direccion.setBounds(80, 110, 200, 20);

        labelTelefono.setBounds(10,140,100,30);
        labelTelefono.setText("Telefono : ");
        Telefono.setBounds(80,145, 100, 20);

        labelEmail.setBounds(185,140,100,30);
        labelEmail.setText("Email : ");
        Email.setBounds(255,140, 250, 20);
//
        labelCiudad.setBounds(10,175,130,30);
        labelCiudad.setText("Ciudad : ");
        Ciudad.setBounds(70, 180, 100,20);

        labelPais.setBounds(10, 210, 150, 30);
        labelPais.setText("Pais : ");
        comboPais.setBounds(50,215,150,20);

        labelCP.setBounds(10,245,100,30);
        labelCP.setText("Codigo Postal : ");
        CP.setBounds(110, 250,70, 20);

        enviar.setBounds(550, 250, 100, 30);

        //configuramos la barra de menu superior
        menuReserva.setText("Reserva");
        barraMenu.add(menuReserva);

        menuHorarios.setText("Consultar Horarios Trabajador");
        barraMenu.add(menuHorarios);

        menuDatos.setText("Consultar Datos Cliente/Empleado");
        barraMenu.add(menuDatos);

        //añadimos la barra de menu superior a la ventana
        setJMenuBar(barraMenu);

        //añadimos los componentes al panel
        miPanel.add(labelNombre);
        miPanel.add(Nombre);
        miPanel.add(labelApellidos);
        miPanel.add(Apellidos);
        miPanel.add(labelNIF);
        miPanel.add(NIF);
        miPanel.add(labelDireccion);
        miPanel.add(Direccion);
        miPanel.add(labelTelefono);
        miPanel.add(Telefono);
        miPanel.add(labelEmail);
        miPanel.add(Email);
        miPanel.add(labelCiudad);
        miPanel.add(Ciudad);
        miPanel.add(labelPais);
        miPanel.add(comboPais);
        miPanel.add(labelCP);
        miPanel.add(CP);
        miPanel.add(enviar);
        add(miPanel);
        miPanel.setVisible(true);

        enviar.setVisible(true);



        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Recogemos el texto de los JTextFields

                String nombre = Nombre.getText();
                String apellidos = Apellidos.getText();
                String nif = NIF.getText();
                String direccion = Direccion.getText();
                String telefono = Telefono.getText();
                String email = Email.getText();
                String ciudad = Ciudad.getText();
                String cp = CP.getText();
                String pais = comboPais.getSelectedItem().toString();

                //Creamos un objeto de tipo Cliente
                Cliente cliente = new Cliente(nombre, apellidos, nif, direccion, telefono, email, ciudad, cp, pais);

                try {

                    //Conectamos a la base de datos y realizamos una consulta para verificar si ese cliente ya existe
                    Statement miStatement = ConexionDB.miConexion.createStatement();
                    ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM Cliente where DNI like '"+nif+"'");
                    if(miResultSet.next()){
                        JOptionPane.showMessageDialog(null, "El cliente ya existe");
                    }else {

                        //Verificamos que los campos no esten vacios
                        if (Main.verificarCamposVacios(nombre,apellidos,nif,direccion,telefono,email,ciudad,cp,pais)){

                            JOptionPane.showMessageDialog(null,"No has introducido datos");
                        }else {
                            //Instrucccion para añadir el cliente a la base de datos
                            String instruccionSQL = "INSERT INTO Cliente(DNI, Nombre, Apellido, Telf, Email, Direccion, Pais, Ciudad, CP) VALUES ('" + nif + "','" + nombre + "','" + apellidos + "','" + telefono + "','" + email + "','" + direccion + "','" + pais + "','" + ciudad + "','" + cp + "')";
                            miStatement.executeUpdate(instruccionSQL);
                            JOptionPane.showMessageDialog(null, "Perfecto se han introducido los datos correctamente!");
                            dispose();
                            //abrimos la ventana de servicios extra, pasando como parametro el cliente que acabamos de crear, las fechas, la habitacion y el número de dias.
                            ServiciosExtra serviciosExtra = new ServiciosExtra(habitacion, cliente, fechaLlegada, fechaSalida, dias);
                            serviciosExtra.setVisible(true);

                        }
                    }
                }catch(Exception ex) {
                    //Si hay algún error en la conexión lo mostramos por pantalla
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null,"Error: No se ha podido insertar los datos");
                }


            }


        });




    }





}

