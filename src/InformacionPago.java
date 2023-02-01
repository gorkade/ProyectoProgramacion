import javax.swing.*;

public class InformacionPago extends JFrame {

    private JMenuBar BarraMenu;
    private JMenuItem MenuReserva, MenuHorariosTrabajador,MenuDatosClientes;

    public InformacionPago(){

        IniciarComponentes();
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

    public void IniciarComponentes(){


        //Menu superior de la ventana
        BarraMenu= new JMenuBar();

        MenuReserva= new JMenuItem();
        BarraMenu.add(MenuReserva);

        MenuHorariosTrabajador= new JMenuItem();
        BarraMenu.add(MenuHorariosTrabajador);

        MenuDatosClientes= new JMenuItem();
        BarraMenu.add(MenuDatosClientes);




    }

}
