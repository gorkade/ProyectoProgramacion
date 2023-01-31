import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VentanaDatos extends JFrame implements ActionListener {
    private JPanel miPanel;//contenedor de los componentes
    private JMenuBar barraMenu;
    private JMenuItem menuReserva, menuHorarios,menuDatos;

    private JComboBox comboTipoPersona;

    String[] tiposPersonas = {"Seleccionar Tipo...", "Cliente", "Empleado"};
    private JLabel titulo, labelTipoPersona, labelDNI, labelDatos;
    private JTextField dni;

    private Button consultar;

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

        titulo = new JLabel();
        labelTipoPersona = new JLabel();
        comboTipoPersona = new JComboBox(tiposPersonas);
        dni = new JTextField();
        labelDNI = new JLabel();
        labelDatos = new JLabel();

        consultar = new Button("Consultar");

        /*Labels*/
        titulo.setBounds(10,0,250,30);
        titulo.setText("CONSULTAR DATOS PERSONAS:");

        labelTipoPersona.setBounds(10,20,120,30);
        labelTipoPersona.setText("Tipo de Persona: ");

        comboTipoPersona.setBounds(130,25,130,20);

        labelDNI.setBounds(10,45,150,30);
        labelDNI.setText("Documento de Identidad: ");

        dni.setBounds(160,50,130,20);

        labelDatos.setBounds(300,70,650,30);

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

        miPanel.add(titulo);
        miPanel.add(labelTipoPersona);
        miPanel.add(comboTipoPersona);
        miPanel.add(dni);
        miPanel.add(labelDNI);
        miPanel.add(labelDatos);
        miPanel.add(consultar);
        add(miPanel);

        labelDatos.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == consultar) {
            labelDatos.setText("Datos: ");
            labelDatos.setVisible(true);
        }

        if (e.getSource() == menuReserva) {
            VentanaPrincipal miFrame= new VentanaPrincipal();
            miFrame.setVisible(true);
            this.setVisible(false);
        }

        if (e.getSource() == menuHorarios) {
            VentanaHorarios miFrame= null;
            try {
                miFrame = new VentanaHorarios();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            miFrame.setVisible(true);
            this.setVisible(false);
        }

    }
}
