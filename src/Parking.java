import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Parking extends JFrame {
    private JPanel panel;
    private JButton[] parkingSpaces;
    private boolean[] parkingSpaceAvailability;
    private int numberOfSpaces;

    public Parking(int numberOfSpaces, Habitacion habitacion, String fechaLlegada, String fechaSalida,  int dias) {
        super("Parking Lot");

        this.numberOfSpaces = numberOfSpaces;
        ImageIcon icon = new ImageIcon("C:\\Users\\DAM.DESKTOP-GO77QJ7\\IdeaProjects\\parking\\wheelchair.png");

        panel = new JPanel();
        JLabel label = new JLabel("Plazas Parking disponibles:");
        label.setBorder(new EmptyBorder(10, 10, 0, 0));

        panel.setLayout(new FlowLayout()); //using flow layout
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));


        parkingSpaces = new JButton[numberOfSpaces];
        parkingSpaceAvailability = new boolean[numberOfSpaces];


        for (int i = 0; i < numberOfSpaces; i++) {

            parkingSpaces[i] = new JButton(String.valueOf(i + 1));
            if (i == 0 || i == 1 || i == 2 || i == 3) {
                parkingSpaces[i].setIcon(icon);

            }
            parkingSpaces[i].setPreferredSize(new Dimension(100, 40));

            parkingSpaces[i].setBackground(Color.GREEN);
            parkingSpaceAvailability[i] = true;
            parkingSpaces[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    int spaceNumber = Integer.parseInt(button.getText()) - 1;

                    if (parkingSpaceAvailability[spaceNumber]) {
                        button.setBackground(Color.RED);
                        parkingSpaceAvailability[spaceNumber] = false;
                    } else {
                        button.setBackground(Color.GREEN);
                        parkingSpaceAvailability[spaceNumber] = true;
                    }
                }
            });
            panel.add(parkingSpaces[i]);

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
            }
        });
// Add the new JPanel to the bottom of the main JPanel
        panel.add(botonesinferiores, BorderLayout.SOUTH);
        add(panel);


        //tamaño de la ventana
        setSize(700,330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}