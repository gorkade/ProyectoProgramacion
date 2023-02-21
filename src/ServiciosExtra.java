import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

public class ServiciosExtra extends JFrame implements ActionListener {
    //clase para seleccionar los servicios extras que se quieren contratar
    public ServiciosExtra(Habitacion habitacion, Cliente cliente, String fechaLlegada, String fechaSalida, int dias)//constructor
    {
        //llamamos a método para crear la ventana y creamos el panel con sus componentes y la configuración posterior
        iniciarComponentes(habitacion, cliente, fechaLlegada, fechaSalida, dias);
        //Asigna un titulo a la barra de titulo
        setTitle("Menú Hotel : Reserva");
        //tamaño de la ventana
        setSize(700,330);
        //pone la ventana en el Centro de la pantalla
        setLocationRelativeTo(null);
        /*Hacemos que la ventana cambie de tamaño*/
        setResizable(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void iniciarComponentes(Habitacion habitacion, Cliente cliente, String fechaLlegada, String fechaSalida, int dias) {

        /*Inicia instancias de los componentes*/
        JPanel miPanel = new JPanel();
        miPanel.setLayout(null);
        JMenuItem menuReserva = new JMenuItem("Reserva");
        JMenuItem menuHorarios = new JMenuItem("Horarios");
        JMenuItem menuDatos = new JMenuItem("Datos");
        JMenuBar barraMenu = new JMenuBar();
        JScrollBar scrollBar = new JScrollBar();

        JLabel labelServiciosExtra = new JLabel();
        JCheckBox bar = new JCheckBox("Bar (+10€)");
        JCheckBox restaurante = new JCheckBox("Restaurante (+30€)");
        JCheckBox actividades = new JCheckBox("Acceso a actividades (+40€)");
        JCheckBox guarderia = new JCheckBox("Guarderia (+40€)");
        JCheckBox cajaFuerte = new JCheckBox("Caja Fuerte (+5€)");
        JLabel labelOfertasDispo = new JLabel();
        JCheckBox descuentoFamiliaN = new JCheckBox("Descuento Familia Numerosa (10%, Presentar carné en recepción)");


        Button enviar = new Button("Enviar");


        /*Fin instancias de los componentes*/

        /*Labels*/
        labelServiciosExtra.setBounds(10,10,200,30);
        labelServiciosExtra.setText("Servicios extra : ");

        bar.setBounds(10,30,130,30);
        restaurante.setBounds(10, 60, 230, 30);
        actividades.setBounds(10, 90, 230, 30);
        guarderia.setBounds(10, 120, 230, 30);
        cajaFuerte.setBounds(10, 150, 230, 30);

        labelOfertasDispo.setBounds(10, 180, 230, 30);
        labelOfertasDispo.setText("Ofertas Disponibles : ");
        descuentoFamiliaN.setBounds(10,210, 370, 30);


        enviar.setBounds(300, 235, 100, 30);

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

        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Extra extras = null;
                try {
                    extras = new Extra();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                if (bar.isSelected()) {
                    extras.setBar(true);
                }else {
                    extras.setBar(false);
                }

                if (restaurante.isSelected()) {
                    extras.setRestaurante(true);
                }else {
                    extras.setRestaurante(false);
                }

                if (actividades.isSelected()) {
                    extras.setActividades(true);
                }else {
                    extras.setActividades(false);
                }

                if (guarderia.isSelected()) {
                    extras.setGuarderia(true);
                }else {
                    extras.setGuarderia(false);
                }

                if (cajaFuerte.isSelected()) {
                    extras.setCajaFuerte(true);
                }else {
                    extras.setCajaFuerte(false);
                }

                if (descuentoFamiliaN.isSelected()) {
                    extras.setDescuentoFamiliaN(true);
                }else {
                    extras.setDescuentoFamiliaN(false);
                }

                try {
                    InformacionPago infoPago = new InformacionPago(habitacion, cliente, fechaLlegada, fechaSalida, extras, dias);
                    infoPago.setVisible(true);
                    dispose();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}




