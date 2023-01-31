import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DatosReserva extends VentanaPrincipal {
    private JPanel miPanel;//contenedor de los componentes
    private JMenuBar barraMenu;
    private JMenu menuReserva, menuHorarios,menuDatos;

    /*items del menu Tipo*/
    private JComboBox comboPais;
    /*labels de los menus para mostrar en pantalla*/
    private JLabel labelNombre, labelApellidos, labelNIF, labelDireccion, labelTelefono, labelCiudad, labelPais, labelCP;
    private Button enviar;

    String[] pais = {"Afganistán","Albania","Alemania","Andorra","Angola","Antigua y Barbuda","Arabia Saudita","Argelia","Argentina","Armenia","Australia","Austria","Azerbaiyán","Bahamas","Bangladés","Barbados","Baréin","Bélgica","Belice","Benín","Bielorrusia","Birmania","Bolivia","Bosnia y Herzegovina","Botsuana","Brasil","Brunéi","Bulgaria","Burkina Faso","Burundi","Bután","Cabo Verde","Camboya","Camerún","Canadá","Catar","Chad","Chile","China","Chipre","Ciudad del Vaticano","Colombia","Comoras","Corea del Norte","Corea del Sur","Costa de Marfil","Costa Rica","Croacia","Cuba","Dinamarca","Dominica","Ecuador","Egipto","El Salvador","Emiratos Árabes Unidos","Eritrea","Eslovaquia","Eslovenia","España","Estados Unidos","Estonia","Etiopía","Filipinas","Finlandia","Fiyi","Francia","Gabón","Gambia","Georgia","Ghana","Granada","Grecia","Guatemala","Guyana","Guinea","Guinea ecuatorial","Guinea-Bisáu","Haití","Honduras","Hungría","India","Indonesia","Irak","Irán","Irlanda","Islandia","Islas Marshall","Islas Salomón","Israel","Italia","Jamaica","Japón","Jordania","Kazajistán","Kenia","Kirguistán","Kiribati","Kuwait","Laos","Lesoto","Letonia","Líbano","Liberia","Libia","Liechtenstein","Lituania","Luxemburgo","Madagascar","Malasia","Malaui","Maldivas","Malí","Malta","Marruecos","Mauricio","Mauritania","México","Micronesia","Moldavia","Mónaco","Mongolia","Montenegro","Mozambique","Namibia","Nauru","Nepal","Nicaragua","Níger","Nigeria","Noruega","Nueva Zelanda","Omán","Países Bajos","Pakistán","Palaos","Palestina","Panamá","Papúa Nueva Guinea","Paraguay","Perú","Polonia","Portugal","Reino Unido","República Centroafricana","República Checa","República de Macedonia","República del Congo","República Democrática del Congo","República Dominicana","República Sudafricana","Ruanda","Rumanía","Rusia","Samoa","San Cristóbal y Nieves","San Marino","San Vicente y las Granadinas","Santa Lucía","Santo Tomé y Príncipe","Senegal","Serbia","Seychelles","Sierra Leona","Singapur","Siria","Somalia","Sri Lanka","Suazilandia","Sudán","Sudán del Sur","Suecia","Suiza","Surinam","Tailandia","Tanzania","Tayikistán","Timor Oriental","Togo","Tonga","Trinidad y Tobago","Túnez","Turkmenistán","Turquía","Tuvalu","Ucrania","Uganda","Uruguay","Uzbekistán","Vanuatu","Venezuela","Vietnam","Yemen","Yibuti","Zambia","Zimbabue"};
    public DatosReserva()//constructor
    {
        iniciarComponentes();
        //Asigna un titulo a la barra de titulo
        setTitle("Menú de Reserva Ejemplo : Titulo De La ventana");
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


        ConexionDB.ConectarDB();


        /*Inicia instancias de los componentes*/
        miPanel = new JPanel();
        miPanel.setLayout(null);
        menuReserva = new JMenu("Reserva");
        menuHorarios = new JMenu("Horarios");
        menuDatos = new JMenu("Datos");
        barraMenu = new JMenuBar();
        comboPais = new JComboBox(pais);

        JTextField Nombre = new JTextField();
        JTextField Apellidos = new JTextField();
        JTextField NIF = new JTextField();
        JTextField Direccion = new JTextField();
        JTextField Telefono = new JTextField();
        JTextField Ciudad = new JTextField();
        JTextField CP = new JTextField();

        labelNombre = new JLabel();
        labelApellidos = new JLabel();
        labelNIF = new JLabel();

        labelDireccion =new JLabel();
        labelTelefono =new JLabel();

        labelCiudad =new JLabel();
        labelPais = new JLabel();
        labelCP = new JLabel();

        enviar = new Button("Enviar");
        /*Fin instancias de los componentes*/

        /*Labels*/
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

        /*Agrega los Menus de la barra de Menu*/
        menuReserva.setText("Reserva");
        barraMenu.add(menuReserva);

        menuHorarios.setText("Consultar Horarios Trabajador");
        barraMenu.add(menuHorarios);

        menuDatos.setText("Consultar Datos Cliente/Empleado");
        barraMenu.add(menuDatos);

        setJMenuBar(barraMenu);
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


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
        miPanel.add(labelCiudad);
        miPanel.add(Ciudad);
        miPanel.add(labelPais);
        miPanel.add(comboPais);
        miPanel.add(labelCP);
        miPanel.add(CP);
        miPanel.add(enviar);
        add(miPanel);

        enviar.setVisible(true);

    }


}

