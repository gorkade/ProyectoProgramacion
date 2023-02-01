import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServiciosExtra extends DatosReserva{
    private JPanel miPanel;//contenedor de los componentes

    private JMenuBar barraMenu;
    private JMenuItem menuReserva, menuHorarios,menuDatos;

    private JScrollBar scrollBar;
    private JSpinner numCamas;
    /*items del menu Tipo*/

    private JComboBox comboTipo, comboTipoParking;

    /*labels de los menus para mostrar en pantalla*/
    private JLabel labelServiciosExtra,  labelOfertasDispo;

    private JCheckBox bar, restaurante, actividades, guarderia, cajaFuerte, descuentoFamiliaN;

    private Button enviar;

    public ServiciosExtra()//constructor
    {
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

        labelServiciosExtra = new JLabel();
        bar = new JCheckBox("Bar (+10€)");
        restaurante = new JCheckBox("Restaurante (+30€)");
        actividades = new JCheckBox("Acceso a actividades (+40€)");
        guarderia = new JCheckBox("Guarderia (+40€)");
        cajaFuerte = new JCheckBox("Caja Fuerte (+5€)");
        labelOfertasDispo = new JLabel();
        descuentoFamiliaN = new JCheckBox("Descuento Familia Numerosa (10%, Presentar carné en recepción)");


        enviar = new Button("Enviar");


        /*Fin instancias de los componentes*/

        /*Labels*/
        labelServiciosExtra.setBounds(10,0,200,30);
        labelServiciosExtra.setText("Servicios extra : ");

        bar.setBounds(10,30,130,30);
        restaurante.setBounds(10, 40, 130, 30);
        actividades.setBounds(10, 60, 130, 30);
        guarderia.setBounds(10, 80, 130, 30);
        cajaFuerte.setBounds(10, 100, 130, 30);

        labelOfertasDispo.setBounds(10, 130, 130, 30);
        labelServiciosExtra.setText("Ofertas Disponibles : ");
        descuentoFamiliaN.setBounds(10,150, 130, 30);


        enviar.setBounds(300, 200, 100, 30);

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
        enviar.addActionListener(this);

        miPanel.add(labelServiciosExtra);
        miPanel.add(bar);
        miPanel.add(restaurante);
        miPanel.add(actividades);
        miPanel.add(guarderia);
        miPanel.add(cajaFuerte);
        miPanel.add(labelOfertasDispo);
        miPanel.add(descuentoFamiliaN);
        miPanel.add(enviar);
        add(miPanel);

        enviar.setVisible(true);
    }

}




