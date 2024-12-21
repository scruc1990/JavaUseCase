package test.konecta.nextdate.main.menu;

import javax.swing.JOptionPane;
import test.konecta.nextdate.main.nonworkingdays.NonWorkingDays;

/**
 * Clase para la generación del menú y las ventanas para realizar
 * las validaciones de fechas relacionadas con la nómina.
 * 
 * @author Cristian David Herrera
 * @date 2024-12-20
 */
public class Menu {
    
    /**
     * Función para mostrar el menu de la aplicación
     * 
     * @author Cristian David Herrera
     * @date 2024-12-20
     */
    public static void getMenu() {
         String strOption = "";
         int option = 0;
         String menu = "**Validador de fecha para nomina**\n"
                        + "1 - Validar fecha\n"
                        + "0 - Salir";
         do {
            try {
                strOption = JOptionPane.showInputDialog(menu);
                
                // Controlar el cancelar
                if (strOption == null) return; 
                
                option = Integer.parseInt(strOption);
                
                switch(option) {
                    case 1:
                        String stringDate = getDate();
      
                        JOptionPane.showMessageDialog(null, "La proxima fecha de corte es: "
                                                        + NonWorkingDays.validateDate(stringDate));
                    break;
                        
                    case 0:
                        System.exit(0);
                    break;
                        
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción no válida");
            }
         } while (option != 0);
    }

    /**
     * Método para solicitar la fecha al usuario y válida el formato de esta.
     * 
     * Retorna la fecha que cumple con el formato.
     * 
     * @return date 
     * 
     * @author Cristian David Herrera
     * @date 2024-12-20
     */
    private static String getDate() {
        Boolean format = true;
        String date;
        do {
            String message = (!format ? "Formato invaido, intente nuevamente\n" : "") 
                                + "Escribe la fecha a validar (yyyy-mm-dd): \n";
            date = JOptionPane.showInputDialog(null, message);
        } while((format = NonWorkingDays.formatDate(date)) != true);
    
        return date;
    }

}