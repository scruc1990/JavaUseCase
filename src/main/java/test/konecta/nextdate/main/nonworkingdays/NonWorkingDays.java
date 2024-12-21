
package test.konecta.nextdate.main.nonworkingdays;
import test.konecta.nextdate.main.readfile.ReadFile;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
/**
 * Clase para el manejo de la fecha de corte teniendo en cuenta los
 * días festivos y fin de semana del año.
 * 
 * @author Cristian David Herrera
 * @date 2024-12-20
 */
public class NonWorkingDays {

    /**
     * Función para obtener la fecha de corte de la quincena a
     * partir de la fecha utilizada para la consulta
     * 
     * @param stringDate - fecha a consultar
     * @return LocalDate date - fecha de corte
     * 
     * @author Cristian David Herrera
     * @date 2024-12-20
     */
    public static LocalDate validateDate(String stringDate) {
        LocalDate date = LocalDate.parse(stringDate);
        
        Boolean isNonWorkDay = isNonWorkingDays(date);
        
        int day = date.getDayOfMonth();
        
        if ((day == 15 || day == 30) && isNonWorkDay) {
            date = getBusinessDay(date);
        } else {
            date = calculateNextDate(date, day);
        }
 
        return date;
    }
    
    /**
     * Método para validar si la fecha pertenece a un día no hábil,
     * es decir, festivo o fin de semana.
     * 
     * Retorna True si es un día no hábil, de lo contrario retorna False.
     * 
     * @param date - fecha a consultar
     * @return boolean
     * 
     * @author Cristian David Herrera
     * @date 2024-12-20
     */
    private static boolean isNonWorkingDays(LocalDate date) {
        return isHoliday(date) || isWeekend(date);
    }
    
    /**
     * Método para validar si la fecha es una fecha festiva.
     * 
     * Retorna True en el caso de que si sea festiva;
     * caso contrario, retorna False.
     * 
     * @param date - fecha a consultar
     * @return boolean
     *
     * @author Cristian David Herrera
     * @date 2024-12-20
     */
    private static boolean isHoliday(LocalDate date){
        List<LocalDate> holidays = ReadFile.loadFile();
     
        return holidays.contains(date);
    } 
    
    /**
     * Método para validar si la fecha es un día del fin de semana.
     * 
     * Retorna True en el caso de que si sea,
     * el caso contrario retorna False.
     * 
     * @param date - fecha a consultar
     * @return boolean
     *
     * @author Cristian David Herrera
     * @date 2024-12-20
     */
    private static boolean isWeekend(LocalDate date){
        DayOfWeek day = date.getDayOfWeek();
        
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }
    
    /**
     * Método para obtener el último día hábil antes de la fecha proporcionada.
     *  
     * Si la fecha es un día no hábil, la función retrocede día
     * por día hasta encontrar el día hábil más cercano.
     * 
     * @param date - fecha a consultar
     * @return LocalDate - fecha habil
     *
     * @author Cristian David Herrera
     * @date 2024-12-20
     */
    private static LocalDate getBusinessDay(LocalDate date) {
        while(isNonWorkingDays(date)){
            date = date.minusDays(1);
        }
        return date;
    }
    
     /**
     * Método para calcular la siguiente fecha válida para una quincena,
     * considerando si es un día hábil.
     *
     * @param date - fecha a consultar
     * @return LocalDate - fecha habil
     *
     * @author Cristian David Herrera
     * @date 2024-12-20
     */
    private static LocalDate calculateNextDate(LocalDate date, int day){
        LocalDate nextDate;
        int month = date.getMonthValue();
        int year = date.getYear();
        if (day <= 15) {
            nextDate = getBusinessDay(LocalDate.of(year, month, 15));
        } else {
            int newDay = 30;
            if (month == 2){
                YearMonth february = YearMonth.of(year, 2);
                newDay = february.lengthOfMonth();
            }
            nextDate = getBusinessDay(LocalDate.of(year, month, newDay));
        }
        return nextDate;
    }
    
    /**
     * Función que valida que la fecha proporcionada esté en
     * el formato 'yyyy-MM-dd'.
     * 
     * retorna True si el formato es válido.
     * retorna Flase si el formato no es válido.
     * 
     * @param date - fecha a consultar formato
     * @return boolean - estado de la validación
     */
    public static boolean formatDate(String date){
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate.parse(date, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
