package test.konecta.nextdate.main.readfile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase para la lectura del archivo que contiene las
 * fechas festivas
 * 
 * @author Cristian David Herrera
 * @date 2024-12-20
 */
public class ReadFile {
    
    /**
     * Función para la lectura del archivo que contiene las fechas
     * festivas de Colombia.
     * 
     * @return List<LocalDate> - Lista de las fechas festivas
     * 
     * @author Cristian David Herrera
     * @date 2024-12-20
     */
    public static List<LocalDate> loadFile() {
        List<LocalDate> holidays = new ArrayList<>();
        String routeFile = "holidays.txt";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(routeFile))){
            while((line = br.readLine()) != null){
                holidays.add(LocalDate.parse(line));
            }
        } catch (IOException e){
            System.out.println("Ha ocurrido un error en la línea: " + line + ". Favor revisar");
        }
        return holidays;
    }
}
