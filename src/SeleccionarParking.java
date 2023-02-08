import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SeleccionarParking extends JFrame implements ActionListener {
    private JPanel miPanel;//contenedor de los componentes

    private JMenuBar barraMenu;
    private JMenuItem menuReserva, menuHorarios,menuDatos;

    private JScrollBar scrollBar;

    /*labels de los menus para mostrar en pantalla*/
    private JLabel titulo;

    private Button buscar;

    public SeleccionarParking()//constructor
    {
        iniciarComponentes();
        //Asigna un titulo a la barra de titulo
        setTitle("Menú Hotel : Reserva Parking");
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
        scrollBar = new JScrollBar();

        titulo = new JLabel();

        buscar = new Button("Buscar");


        /*Fin instancias de los componentes*/

        /*Labels*/
        titulo.setBounds(10,0,200,30);
        titulo.setText("RESERVA PLAZA DE PARKING:");

        buscar.setBounds(300, 200, 100, 30);

        /*Agrega los Menus de la barra de Menu*/
        menuReserva.setText("Reserva de Habitaciones");
        barraMenu.add(menuReserva);

        menuHorarios.setText("Consultar Horarios Trabajador");
        barraMenu.add(menuHorarios);

        menuDatos.setText("Consultar Datos Cliente/Empleado");
        barraMenu.add(menuDatos);

        setJMenuBar(barraMenu);

        menuHorarios.addActionListener(this);
        menuDatos.addActionListener(this);
        buscar.addActionListener(this);

        miPanel.add(titulo);
        miPanel.add(buscar);
        add(miPanel);

        buscar.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==menuHorarios){


              VentanaHorarios  ventanaHorarios = new VentanaHorarios();

            ventanaHorarios.setVisible(true);

            this.setVisible(false);
        }

        if(e.getSource()==menuDatos){
            VentanaDatos ventanaDatos = new VentanaDatos();
            ventanaDatos.setVisible(true);

            this.setVisible(false);
        }

        /*if(e.getSource()==buscar){
            DatosReserva datosReserva = new DatosReserva();
            datosReserva.setVisible(true);
            this.setVisible(false);
        }*/
    }
}


