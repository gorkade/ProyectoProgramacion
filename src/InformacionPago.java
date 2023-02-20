import javax.swing.*;
//import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InformacionPago extends JFrame implements ActionListener{
    //Clase para crear la ventana de información de pago

    //Declaración de variables
    private static JButton enviar;

    //Constructor
    public InformacionPago(Habitacion habitacion, Cliente cliente, String fechaLlegadaa, String fechaSalidaa, Extra extras, int dias) throws ParseException {

        //llamamos a método para crear la ventana y creamos el panel con sus componentes y la configuración posterior
        IniciarComponentes(habitacion, cliente, fechaLlegadaa, fechaSalidaa, extras, dias);
        //Asigna un titulo a la barra de titulo
        setTitle("Menú Hotel : Informacion de pago");
        //tamaño de la ventana
        setSize(700,330);
        //pone la ventana en el Centro de la pantalla
        setLocationRelativeTo(null);
        /*impide que la ventana cambie de tamaño*/
        setResizable(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    public void IniciarComponentes(Habitacion habitacion, Cliente cliente, String fechaLlegadaa, String fechaSalidaa, Extra extras, int dias) throws ParseException {

        //Creamos el panel
        JPanel miPanel = new JPanel();
        miPanel.setLayout(null);

        //Menu superior de la ventana
        JMenuBar barraMenu = new JMenuBar();

        JMenuItem menuReserva = new JMenuItem("Reserva de Habitaciones");
        barraMenu.add(menuReserva);

        JMenuItem menuHorarios = new JMenuItem("Consultar Horarios Trabajador");
        barraMenu.add(menuHorarios);

        JMenuItem menuDatos = new JMenuItem("Consultar Datos Cliente/Empleado");
        barraMenu.add(menuDatos);

        setJMenuBar(barraMenu);

        //Instanciamos los componentes
        JLabel infoPago = new JLabel();
        JLabel nombreTitularTarj = new JLabel();
        JTextField NombreTitularTarjetaa = new JTextField ();
        JLabel numTarjeta = new JLabel();
        JTextField NumTarjetaa = new JTextField();
        JLabel fechaCaducidadTarjeta = new JLabel();
        JTextField FechaCaducidadTarjetaa = new JTextField();
        JLabel CVV = new JLabel();
        JTextField CVVV = new JTextField();

        JLabel DNII = new JLabel();
        JTextField DNIT = new JTextField();

        enviar = new JButton();


        //Configuramos la posicion y el texto de los componentes
        infoPago.setBounds(10, 10, 150, 40);
        infoPago.setText("Información del Pago:");

        nombreTitularTarj.setBounds(10, 40, 250, 40);
        nombreTitularTarj.setText("Nombre Titular de la Tarjeta: ");
        NombreTitularTarjetaa.setBounds(10, 70, 250, 40);

        DNII.setBounds(10, 110,250, 40);
        DNII.setText("DNI: ");
        DNIT.setBounds(50, 110, 150, 40);

        numTarjeta.setBounds(10, 145, 250, 40);
        numTarjeta.setText("Numero de la Tarjeta");
        NumTarjetaa.setBounds(10, 175, 250, 40);

        fechaCaducidadTarjeta.setBounds(300,40, 170, 40);
        fechaCaducidadTarjeta.setText("Fecha Caducidad");
        FechaCaducidadTarjetaa.setBounds(295, 70, 170, 40);

        CVV.setBounds(530, 40, 100, 40);
        CVV.setText("CVV:");
        CVVV.setBounds(525,70,50,40);

        enviar.setBounds(530, 220, 170, 40);
        enviar.setText("Enviar");



        //añadimos los componentes al panel
        miPanel.add(infoPago);
        miPanel.add(nombreTitularTarj);
        miPanel.add(NombreTitularTarjetaa);
        miPanel.add(DNII);
        miPanel.add(DNIT);
        miPanel.add(numTarjeta);
        miPanel.add(NumTarjetaa);
        miPanel.add(fechaCaducidadTarjeta);
        miPanel.add(FechaCaducidadTarjetaa);
        miPanel.add(CVV);
        miPanel.add(CVVV);
        miPanel.add(enviar);
        add(miPanel);


        //Añadimos los eventos a los componentes

        //boton ventana datos
        menuDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //abrimos la ventana de datos
                VentanaDatos ventanaDatos = new VentanaDatos();
                ventanaDatos.setVisible(true);
                dispose();
            }
        });

        //Boton ventana horarios
        menuHorarios.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //abrimos la ventana de horarios
                VentanaHorarios ventanaHorarios = new VentanaHorarios();
                ventanaHorarios.setVisible(true);
                dispose();
            }
        });

        //Boton enviar
        enviar.setVisible(true);
        enviar.addActionListener(e -> {
            if (e.getSource() == enviar) {
                //Recogemos el texto de los JTextFields
                String NombreTitular = NombreTitularTarjetaa.getText();
                String DNI = DNIT.getText();
                String NumeroTarjeta = NumTarjetaa.getText();
                String FechaCaducidadd = FechaCaducidadTarjetaa.getText();
                String CVVP = CVVV.getText();


                try {
                    if(Main.verificarCamposVacios(NombreTitular,DNI,NumeroTarjeta,FechaCaducidadd,CVVP)){
                        //Comprobamos que no haya campos vacios
                        JOptionPane.showMessageDialog(null,"No has introducido tus datos");
                    }else {
                        //Conectamos a la base de datos e insertamos los datos de pago
                        Statement miStatement = ConexionDB.miConexion.createStatement();
                        String SQL = "INSERT INTO Pago (DNI, CVV, NumTargeta, Titular, FechaCaducidad) VALUES ('" + DNI + "','" + CVVP + "','" + NumeroTarjeta + "','" + NombreTitular + "','" + FechaCaducidadd + "')";
                        miStatement.executeUpdate(SQL);
                    }

                    //Preguntamos si quiere generar un ticket
                    if (JOptionPane.YES_OPTION == JOptionPane.showOptionDialog(null, "¿Desea generar un ticket?", "Imprimir Ticket", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null)) {
                        JOptionPane.showMessageDialog(null, "Imprimiendo ticket");

                        //Generamos el ticket de la reserva
                        generarTicket(habitacion, cliente, fechaLlegadaa, fechaSalidaa, extras, dias,  DNI, NombreTitular, NumeroTarjeta);

                    } else {
                        JOptionPane.showMessageDialog(null, "No se imprimira ticket");
                    }

                    //SQL = "INSERT INTO Reserva (DNI, NumPlazaParking, NumHabitacion, ServicioExtra1, ServicioExtra2, ServicioExtra3, ServicioExtra4, ServicioExtra5, Descuento, PrecioTotal, FechaLlegada, FechaSalida) VALUES ('"+DNIR+"','"+CVVP+"','"+NumeroTarjeta+"','"+NombreTitular+"','"+FechaCaducidadd+"')";

                }catch(Exception ex) {
                    //Si hay algun error al insertar los datos, mostramos un mensaje de error
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null,"Error: No se ha podido insertar los datos");
                }


            }
        });
    }

    //Metodo para generar el ticket
    public void generarTicket(Habitacion habitacion, Cliente cliente, String fechaLlegadaa, String fechaSalidaa, Extra extras, int dias, String DNI, String NombreTitular, String NumeroTarjeta) throws IOException {
        //Creamos un objeto file con la ruta del ticket
        File ticket = new File("ticket.txt");

        //Si ya existe lo borramos
        if (ticket.exists()) {
            ticket.delete();
        }

        //Creamos el archivo
        ticket.createNewFile();

        //Mostramos la ruta en la que ha sido creado
        JOptionPane.showMessageDialog(null, "Ticket guardado en: " + ticket.getAbsolutePath());

        //Creamos un objeto FileWriter y BufferedWriter para escribir en el archivo
        FileWriter fw = new FileWriter(ticket);
        BufferedWriter bw = new BufferedWriter(fw);

        //Escribimos los datos de la reserva en el archivo
        bw.write("Nombre Cliente: " + cliente.getNombre() + " " + cliente.getApellidos());
        bw.newLine();

        bw.write("DNI: " + DNI);
        bw.newLine();

        bw.write("Nombre Titular Tarjeta: " + NombreTitular);
        bw.newLine();

        bw.write("Numero de Tarjeta: " + NumeroTarjeta.substring(0, 4) + " **** **** " + NumeroTarjeta.substring(12, 16));
        bw.newLine();

        bw.write("Numero de Habitacion: " + habitacion.getNumHabitacion());
        bw.newLine();

        bw.write("Tipo de Habitacion: " + habitacion.getTipoHabitacion());
        bw.newLine();

        bw.write("Fecha de Entrada: " + fechaLlegadaa);
        bw.newLine();

        bw.write("Fecha de Salida: " + fechaSalidaa);
        bw.newLine();

        bw.write("Precio habitacion (" + dias +" dias): " + habitacion.getPrecio()*dias + "€");
        bw.newLine();

        bw.write("Servicios extra: ");
        bw.newLine();

        if(extras.isBar()){
            bw.write("  Bar: " + extras.getPrecioBar() + "€");
            bw.newLine();
        }
        if(extras.isRestaurante()){
            bw.write("  Restaurante: " + extras.getPrecioRestaurante()+ "€");
            bw.newLine();
        }
        if(extras.isActividades()){
            bw.write("  Actividades: " + extras.getPrecioActividades()+ "€");
            bw.newLine();
        }
        if(extras.isGuarderia()){
            bw.write("  Guarderia: " + extras.getPrecioGuarderia()+ "€");
            bw.newLine();
        }
        if(extras.isCajaFuerte()){
            bw.write("  Caja Fuerte: " + extras.getPrecioCajaFuerte()+ "€");
            bw.newLine();
        }
        bw.write("Total extras: " + extras.precioExtra() + "€");
        bw.newLine();
        bw.newLine();

        if(extras.isDescuentoFamiliaN()){
            bw.write("Precio final + descuento familia numerosa: " + (((habitacion.getPrecio()*dias) + extras.precioExtra())-(((habitacion.getPrecio()*dias) + extras.precioExtra())*extras.porcentDescuentoFamiliaN)) + "€");
        }else{
            bw.write("Precio final: " + ((habitacion.getPrecio()*dias) + extras.precioExtra()) + "€");
        }
        bw.newLine();
        bw.newLine();

        bw.write("Gracias por su reserva");

        //Cerramos el BufferedWriter
        bw.close();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
