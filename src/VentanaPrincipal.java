import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private JMenuItem menuHorarios;
    private JMenuItem menuDatos;

    private JSpinner numCamas;
    /*items del menu Tipo*/
    String[] tiposHabitacion = {null , "Sencilla", "Doble", "Matrimonial", "Suite"};
    String[] tiposParking = {null, "Coche (+10€/noche)", "Moto (+5€/noche)"};

    private JComboBox comboTipo, comboTipoParking;
    private JDateChooser calendarioLlegada;
    private JDateChooser calendarioSalida;

    private JLabel labelTipoParking;

    private JCheckBox reservaParking;

    private Button buscar;

    public VentanaPrincipal()//constructor
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
        //contenedor de los componentes
        JPanel miPanel = new JPanel();
        miPanel.setLayout(null);
        JMenuItem menuReserva = new JMenuItem("Reserva");
        menuHorarios = new JMenuItem("Horarios");
        menuDatos = new JMenuItem("Datos");
        JMenuBar barraMenu = new JMenuBar();
        comboTipo = new JComboBox(tiposHabitacion);
        JScrollBar scrollBar = new JScrollBar();

        calendarioLlegada= new JDateChooser();
        calendarioLlegada.setMinSelectableDate(new java.util.Date());
        calendarioSalida= new JDateChooser();

        reservaParking = new JCheckBox("Reserva Parking");
        comboTipoParking = new JComboBox(tiposParking);

        numCamas = new JSpinner();

        /*labels de los menus para mostrar en pantalla*/
        JLabel titulo = new JLabel();
        JLabel labelTipo = new JLabel();
        JLabel labelNumCamas = new JLabel();

        JLabel labelLlegada = new JLabel();
        JLabel labelSalida = new JLabel();

        labelTipoParking =new JLabel();

        buscar = new Button("Buscar");


        /*Fin instancias de los componentes*/

        /*Labels*/
        titulo.setBounds(10,0,200,30);
        titulo.setText("RESERVA DE HABITACIONES");

        labelTipo.setBounds(10,20,130,30);
        labelTipo.setText("Tipo de habitación : ");

        comboTipo.setBounds(135,22,150,20);

        labelNumCamas.setBounds(10,40,150,30);
        labelNumCamas.setText("Número de Camas: ");

        numCamas.setBounds(135,42,150,20);
        numCamas.setValue(1);

        labelLlegada.setBounds(10,95,100,50);
        labelLlegada.setText("Fecha Llegada : ");

        labelSalida.setBounds(350,95,100,50);
        labelSalida.setText("Fecha Salida : ");

        calendarioLlegada.setBounds(115,105,130,30);
        calendarioSalida.setBounds(455,105,130,30);

        reservaParking.setBounds(10, 170, 150, 30);

        labelTipoParking.setBounds(10,180,100,50);
        labelTipoParking.setText("Tipo Parking : ");

        comboTipoParking.setBounds(115,200,150,20);

        buscar.setBounds(300, 200, 100, 30);

        /*Agrega los Menus de la barra de Menu*/
        menuReserva.setText("Reserva de Habitaciones");
        barraMenu.add(menuReserva);

        menuHorarios.setText("Consultar Horarios Trabajador");
        barraMenu.add(menuHorarios);

        menuDatos.setText("Consultar Datos Cliente/Empleado");
        barraMenu.add(menuDatos);

        setJMenuBar(barraMenu);

        calendarioSalida.setDateFormatString("dd/MM/yyyy");
        calendarioLlegada.setDateFormatString("dd/MM/yyyy");

        menuHorarios.addActionListener(this);
        menuDatos.addActionListener(this);
        reservaParking.addActionListener(this);
        buscar.addActionListener(this);
        calendarioLlegada.getDateEditor().addPropertyChangeListener(
                evt -> calendarioSalida.setMinSelectableDate(calendarioLlegada.getDate()));

        miPanel.add(titulo);
        miPanel.add(labelTipo);
        miPanel.add(labelNumCamas);
        miPanel.add(numCamas);
        miPanel.add(comboTipo);
        miPanel.add(labelLlegada);
        miPanel.add(labelSalida);
        miPanel.add(calendarioLlegada);
        miPanel.add(calendarioSalida);
        miPanel.add(reservaParking);
        miPanel.add(labelTipoParking);
        miPanel.add(comboTipoParking);
        miPanel.add(buscar);
        add(miPanel);

        buscar.setVisible(true);
        labelTipoParking.setVisible(false);
        comboTipoParking.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==reservaParking){
            if(reservaParking.isSelected()){
                labelTipoParking.setVisible(true);
                comboTipoParking.setVisible(true);
            }else{
                labelTipoParking.setVisible(false);
                comboTipoParking.setVisible(false);
                comboTipoParking.setSelectedIndex(0);
            }
        }

        if(e.getSource()==menuHorarios){


            VentanaHorarios ventanaHorarios = new VentanaHorarios();

            ventanaHorarios.setVisible(true);

            this.setVisible(false);
        }

        if(e.getSource()==menuDatos){
            VentanaDatos ventanaDatos = new VentanaDatos();
            ventanaDatos.setVisible(true);

            this.setVisible(false);
        }

        if(e.getSource()==buscar){
            String tipoHabitacion = (String) comboTipo.getSelectedItem();
            int numCamasHabitacion = (int) numCamas.getValue();
            String fechaLlegada = calendarioLlegada.getDate().toString();
            String fechaSalida = calendarioSalida.getDate().toString();
            String tipoParking = (String) comboTipoParking.getSelectedItem();

            HabitacionesDisponibles ventanaHabitaciones = null;

            try {
                ventanaHabitaciones = new HabitacionesDisponibles(tipoHabitacion,numCamasHabitacion,fechaLlegada,fechaSalida, tipoParking);
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null,"Error en la base de datos");

            }

            ventanaHabitaciones.setVisible(true);

            this.setVisible(false);
        }
    }
}

