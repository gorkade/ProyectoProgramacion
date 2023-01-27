import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaHorarios extends JFrame implements ActionListener{
    private JPanel miPanel;//contenedor de los componentes
    private JMenuBar barraMenu;
    private JMenuItem menuReserva, menuHorarios,menuDatos;
    private JLabel titulo, labelDNI, labelHorario, labelZona;
    private JTextField dni;

    private Button consultar;

    public VentanaHorarios()//constructor
    {
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

        /**/

        /*Inicia instancias de los componentes*/
        miPanel = new JPanel();
        miPanel.setLayout(null);
        menuReserva = new JMenuItem("Reserva");
        menuHorarios = new JMenuItem("Horarios");
        menuDatos = new JMenuItem("Datos");
        barraMenu = new JMenuBar();

        titulo = new JLabel();
        labelDNI = new JLabel();
        dni = new JTextField();
        labelHorario = new JLabel();

        labelZona =new JLabel();

        consultar = new Button("Consultar");

        /*Labels*/
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

        setJMenuBar(barraMenu);

        consultar.addActionListener(this);
        menuReserva.addActionListener(this);
        menuDatos.addActionListener(this);

        miPanel.add(titulo);
        miPanel.add(labelDNI);
        miPanel.add(dni);
        miPanel.add(labelHorario);
        miPanel.add(labelZona);
        miPanel.add(consultar);
        add(miPanel);

        consultar.setVisible(true);
        labelHorario.setVisible(false);
        labelZona.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == consultar) {
            labelHorario.setText("Horario: " + "select horario from trabajador where dni = " + dni.getText());
            labelZona.setText("Zona: " + "select zona from trabajador where dni = " + dni.getText());
            labelHorario.setVisible(true);
            labelZona.setVisible(true);
        }

        if (e.getSource() == menuReserva) {
            VentanaPrincipal miFrame= new VentanaPrincipal();
            miFrame.setVisible(true);
            this.setVisible(false);
        }

        if(e.getSource()==menuDatos){
            VentanaDatos ventanaDatos = new VentanaDatos();
            ventanaDatos.setVisible(true);

            this.setVisible(false);
        }

    }
}
