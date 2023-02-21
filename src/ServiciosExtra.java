import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

public class ServiciosExtra extends JFrame implements ActionListener {
    //clase para seleccionar los servicios extras que se quieren contratar

    //Atributos menu superior
    JMenuItem menuReserva;
    JMenuItem menuHorarios;
    JMenuItem menuDatos;

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
        menuReserva = new JMenuItem("Reserva");
        menuHorarios = new JMenuItem("Horarios");
        menuDatos = new JMenuItem("Datos");
        JMenuBar barraMenu = new JMenuBar();

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

        //Configuramos la posicion y los textos de los componentes
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

        //añadimos la barra de menu superior a la ventana
        setJMenuBar(barraMenu);


        //Añadimos los Listener a los botones que vamos a escuchar
        menuHorarios.addActionListener(this);
        menuDatos.addActionListener(this);
        enviar.addActionListener(this);

        //Añadimos los componentes al panel
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

        //programamos el boton enviar
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //creamos un nuevo objeto extra vacío
                Extra extras;
                try {
                    extras = new Extra(false,false,false,false,false,false);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException(ex);
                }

                //Comprobamos que servicios se han seleccionado y los cambiamos a true en el objeto extra
                if (bar.isSelected()) {
                    extras.setBar(true);
                }

                if (restaurante.isSelected()) {
                    extras.setRestaurante(true);
                }

                if (actividades.isSelected()) {
                    extras.setActividades(true);
                }

                if (guarderia.isSelected()) {
                    extras.setGuarderia(true);
                }

                if (cajaFuerte.isSelected()) {
                    extras.setCajaFuerte(true);
                }

                if (descuentoFamiliaN.isSelected()) {
                    extras.setDescuentoFamiliaN(true);
                }

                try {
                    //abrimos la ventana de la información de pago con la habitación, el cliente, la fecha de llegada,
                    // la fecha de salida, los días de estancia y los servicios extra como parametros
                    InformacionPago infoPago = new InformacionPago(habitacion, cliente, fechaLlegada, fechaSalida, extras, dias);
                    infoPago.setVisible(true);
                    //cerramos la ventana actual
                    dispose();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==menuHorarios){
            //abrimos la ventana de horarios
            VentanaHorarios ventanaHorarios = new VentanaHorarios();

            ventanaHorarios.setVisible(true);

            this.setVisible(false);
        }

        if(e.getSource()==menuDatos){
            //abrimos la ventana de datos
            VentanaDatos ventanaDatos = new VentanaDatos();
            ventanaDatos.setVisible(true);

            this.setVisible(false);
        }
    }
}




