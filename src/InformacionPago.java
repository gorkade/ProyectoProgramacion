import javax.swing.*;
//import java.sql.ResultSet;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InformacionPago extends JFrame {

    private static JButton enviar;

    public InformacionPago(Habitacion habitacion, Cliente cliente, String fechaLlegadaa, String fechaSalidaa, Extra extras, int dias) throws ParseException {

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

        JPanel miPanel = new JPanel();
        miPanel.setLayout(null);

        //Menu superior de la ventana
        JMenuBar barraMenu = new JMenuBar();

        JMenuItem menuReserva = new JMenuItem("Reserva");
        barraMenu.add(menuReserva);

        JMenuItem menuHorariosTrabajador = new JMenuItem("Consultar Horarios Trabajador");
        barraMenu.add(menuHorariosTrabajador);

        JMenuItem menuDatosClientes = new JMenuItem("Datos Clientes");
        barraMenu.add(menuDatosClientes);

        setJMenuBar(barraMenu);

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
                    //Conectamos a la base de datos
                    Statement miStatement = ConexionDB.miConexion.createStatement();
                    String SQL = "INSERT INTO Pago (DNI, CVV, NumTargeta, Titular, FechaCaducidad) VALUES ('"+DNI+"','"+CVVP+"','"+NumeroTarjeta+"','"+NombreTitular+"','"+FechaCaducidadd+"')";
                    miStatement.executeUpdate(SQL);

                    if (JOptionPane.YES_OPTION == JOptionPane.showOptionDialog(null, "¿Desea generar un ticket?", "Imprimir Ticket", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null)) {
                        JOptionPane.showMessageDialog(null, "Imprimiendo ticket");

                        File ticket = new File("ticket.txt");

                        if (!ticket.exists()) {
                            ticket.createNewFile();
                            System.out.println("Ticket guardado en: " + ticket.getPath());
                        }

                        FileWriter fw = new FileWriter(ticket);
                        BufferedWriter bw = new BufferedWriter(fw);

                        bw.write("Nombre: " + NombreTitular);
                        bw.newLine();
                        bw.write("DNI: " + DNI);
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
                        if(extras.isDescuentoFamiliaN()){
                            bw.write("Precio final + descuento familia numerosa: " + ((habitacion.getPrecio()*dias + extras.precioExtra())*extras.porcentDescuentoFamiliaN) + "€");
                        }else{
                            bw.write("Precio final: " + (habitacion.getPrecio()*dias + extras.precioExtra()) + "€");
                        }
                        bw.newLine();
                        bw.newLine();
                        bw.write("Gracias por su reserva");
                        bw.close();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se imprimira ticket");
                    }

                    //SQL = "INSERT INTO Reserva (DNI, NumPlazaParking, NumHabitacion, ServicioExtra1, ServicioExtra2, ServicioExtra3, ServicioExtra4, ServicioExtra5, Descuento, PrecioTotal, FechaLlegada, FechaSalida) VALUES ('"+DNIR+"','"+CVVP+"','"+NumeroTarjeta+"','"+NombreTitular+"','"+FechaCaducidadd+"')";

                }catch(Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null,"Error: No se ha podido insertar los datos");
                }


            }
        });
    }



}
