import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

public class PantallaInicial extends JFrame implements ActionListener {
    //Esta clase mestra la pantalla inicial del programa

    //Atributos
    private JMenuItem menuHorarios;
    private JMenuItem menuDatos;
    private JMenuItem menuReserva;

    public PantallaInicial()
    {
        //llamamos a método para crear la ventana y creamos el panel con sus componentes y la configuración posterior
        iniciarComponentes();
        //Asigna un titulo a la barra de titulo
        setTitle("Menú Hotel : Reserva");
        //tamaño de la ventana
        setSize(700,330);
        //pone la ventana en el Centro de la pantalla
        setLocationRelativeTo(null);
        /*impide que la ventana cambie de tamaño*/
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void iniciarComponentes() {
        /*Inicia instancias de los componentes*/
        JPanel miPanel = new JPanel();
        miPanel.setLayout(null);
        menuReserva = new JMenuItem("Reserva");
        menuHorarios = new JMenuItem("Horarios");
        menuDatos = new JMenuItem("Datos");
        JMenuBar barraMenu = new JMenuBar();

        JLabel titulo = new JLabel();


        /*Fin instancias de los componentes*/

        //Configuramos la posicion y los textos de los componentes
        titulo.setBounds(10,0,200,30);
        titulo.setText("GESTIÓN HOTELERA");



        /*Agrega los Menus de la barra de Menu*/
        menuReserva.setText("Reserva de Habitaciones");
        barraMenu.add(menuReserva);

        menuHorarios.setText("Consultar Horarios Trabajador");
        barraMenu.add(menuHorarios);

        menuDatos.setText("Consultar Datos Cliente/Empleado");
        barraMenu.add(menuDatos);

        //añadimos la barra de menu superior a la ventana
        setJMenuBar(barraMenu);


        //Añadimos los Listener a los componentes
        menuHorarios.addActionListener(this);
        menuDatos.addActionListener(this);
        menuReserva.addActionListener(this);

        //Añadimos los componentes al panel
        miPanel.add(titulo);
        add(miPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuReserva) {
            //Si se pulsa el boton de reserva, se abre la ventana de reserva
            InicioReserva ventanaReserva = new InicioReserva();
            ventanaReserva.setVisible(true);
            this.setVisible(false);
        }

        if(e.getSource()==menuHorarios){
            //Si se pulsa el menu de horarios se abre la ventana de horarios
            VentanaHorarios ventanaHorarios = new VentanaHorarios();

            ventanaHorarios.setVisible(true);

            this.setVisible(false);
        }

        if(e.getSource()==menuDatos){
            //Si se pulsa el menu de datos se abre la ventana de datos
            VentanaDatos ventanaDatos = new VentanaDatos();
            ventanaDatos.setVisible(true);

            this.setVisible(false);
        }

    }
}

