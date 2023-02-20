import javax.swing.*;
import java.util.Enumeration;

//Clase para poder hacer la selección de habitación con los RadioButton
public class GroupButtonUtils {
    //Método que comprueba si un RadioButton está seleccionado
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
}
