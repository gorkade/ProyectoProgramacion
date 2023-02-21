import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Parking extends JFrame {
    private JPanel panel;
    private JButton[] parkingSpaces;
    private boolean[] parkingSpaceAvailability;
    private int numberOfSpaces;

    Statement miStatement= null;

    public Parking(int numberOfSpaces, Habitacion habitacion, String fechaLlegada, String fechaSalida,  int dias) {
        super("Parking Lot");
        //tamaño de la ventana
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.numberOfSpaces = numberOfSpaces;
        ImageIcon icon = new ImageIcon("sillaRuedas.png");
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        icon.setImage(scaledImage);


        panel = new JPanel();
        JLabel label = new JLabel("Plazas Parking disponibles:");
        label.setBorder(new EmptyBorder(10, 10, 0, 0));

        panel.setLayout(new FlowLayout()); //using flow layout
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));


        parkingSpaces = new JButton[numberOfSpaces];
        parkingSpaceAvailability = new boolean[numberOfSpaces];



        try {
            // En vez de cargar los items de el numberofSpaces, tenemos que realizar una carga de datos.
            miStatement = ConexionDB.miConexion.createStatement();
            String sql = "SELECT * FROM Parking ORDER BY NumPlaza ";
            ResultSet plazas = miStatement.executeQuery(sql);
            int i = -1;
            while (plazas.next()) {
                i++;
                parkingSpaces[i] = new JButton(String.valueOf(i + 1 ));
                parkingSpaces[i].setBackground(Color.GREEN);
                panel.add(parkingSpaces[i]);


                if(plazas.getInt("ocupado")==0){
                    parkingSpaces[i].setBackground(Color.GREEN);
                    parkingSpaceAvailability[i] = true;
                }
                else if(plazas.getInt("ocupado")==1){
                    parkingSpaces[i].setBackground(Color.RED);
                    parkingSpaceAvailability[i] = false;
                }
                if (i == 0 || i == 1 || i == 2 || i == 3) {
                    parkingSpaces[i].setIcon(icon);

                }
                parkingSpaces[i].setPreferredSize(new Dimension(100, 40));


                parkingSpaces[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();
                        int spaceNumber = Integer.parseInt(button.getText());
                        /*AQUI LA LÓGICA ES LA SIGUIENTE, COGE LOS DATOS DEL BOTON QUE HACER LA ACCIÓN, UNA VEZ REALIZA LA ACCIÓN EJECUTA UNA CONSULTA*/
                        /*La consulta SQL es la siguiente si se bloquea esa plaza de parking, hacemos un update del parking*/
                        //Comprobar si el boolean del SQL se lo traga
                        if (parkingSpaceAvailability[spaceNumber]) {

                            try {
                                //Ejecutamos la consulta, pones en ocupado la plaza
                                miStatement = ConexionDB.miConexion.createStatement();
                                miStatement.executeUpdate("UPDATE Parking set ocupado = 1 where NumPlaza = "+spaceNumber+"");
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            button.setBackground(Color.RED);
                            parkingSpaceAvailability[spaceNumber] = false;
                        } else {

                            try {
                                //Ejecutamos la consulta, pones en desocupado la plaza
                                miStatement = ConexionDB.miConexion.createStatement();
                                miStatement.executeUpdate("UPDATE Parking set ocupado = 0 where NumPlaza = "+spaceNumber+"");
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            button.setBackground(Color.GREEN);
                            parkingSpaceAvailability[spaceNumber] = true;
                        }
                    }
                });


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        add(label, BorderLayout.NORTH);

        //todo Hacemos los botones inferiores para que se vean displayeados en la parte inferior de la pantalla
        JPanel botonesinferiores = new JPanel();
        //todo boton disponible
        JButton disponible = new JButton();
        disponible.setBackground(Color.green);
        disponible.setPreferredSize(new Dimension(20, 20));
        //todo boton no disponible
        JButton noDisponible = new JButton();
        noDisponible.setBackground(Color.red);
        noDisponible.setPreferredSize(new Dimension(20, 20));
        //todo boton plaza minusvalido
        JButton plazaMinus = new JButton();

        plazaMinus.setIcon(icon);
        plazaMinus.setPreferredSize(new Dimension(20, 20));
        //todo boton plaza Seleccionado
        JButton seleccionado = new JButton();
        //todo boton aceptar
        JButton aceptar = new JButton("ACEPTAR");

        //todo jlabel mis muertos
        JLabel dispo = new JLabel("Disponible");
        JLabel nodispo = new JLabel("No Disponible");
        JLabel plazamin = new JLabel("Plaza Minusválidos");
        JLabel selec = new JLabel("Seleccionados");


// Add the 4 buttons to the new JPanel
        botonesinferiores.add(disponible);
        botonesinferiores.add(dispo);
        botonesinferiores.add(noDisponible);
        botonesinferiores.add(nodispo);
        botonesinferiores.add(plazaMinus);
        botonesinferiores.add(plazamin);
        botonesinferiores.add(seleccionado);
        botonesinferiores.add(selec);
        botonesinferiores.add(aceptar);

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatosReserva datosReserva = new DatosReserva(habitacion, fechaLlegada, fechaSalida, dias);
                datosReserva.setVisible(true);
                dispose();
            }
        });
// Add the new JPanel to the bottom of the main JPanel
        panel.add(botonesinferiores, BorderLayout.SOUTH);
        add(panel);



    }
}