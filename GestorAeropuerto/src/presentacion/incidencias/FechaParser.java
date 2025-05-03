package presentacion.incidencias;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaParser {
    public static Date convertirAFecha(String fechaStr) {
    	 SimpleDateFormat formato = new SimpleDateFormat("yy/MM/dd");
         try {
             java.util.Date fechaUtil = formato.parse(fechaStr);
             return new Date(fechaUtil.getTime()); // convertir a java.sql.Date
         } catch (ParseException e) {
             System.err.println("Error al parsear la fecha: " + fechaStr);
             return null;
    }
         
    }
}
