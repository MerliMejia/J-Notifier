package notificador;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import notificador.UI.mainForm;

/**
 *
 * @author Merli Mejia
 */
public class Notificador {

    private static Map<String, Integer> meses = new HashMap<>();

    private static JsonArray obtenerDatos() {
        String rutaJSON = System.getProperty("user.dir") + "\\DBB.json";//RUTA DEL .JSON

        JsonParser parse = new JsonParser();
        JsonArray array = new JsonArray();

        try {
            JsonObject objeto = (JsonObject) parse.parse(new FileReader(rutaJSON));
            array = (JsonArray) objeto.get("datos");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        return array;
    }

    private static int diasDiferencia(JsonArray datos) {
        meses.put("enero", 1);
        meses.put("febrero", 2);
        meses.put("marzo", 3);
        meses.put("abril", 4);
        meses.put("mayo", 5);
        meses.put("junio", 6);
        meses.put("julio", 7);
        meses.put("agosto", 8);
        meses.put("septiembre", 9);
        meses.put("octubre", 10);
        meses.put("noviembre", 11);
        meses.put("diciembre", 12);

        LocalDateTime now = LocalDateTime.now();
        int diaHoy = now.getDayOfMonth();
        int mesActual = now.getMonthValue();
        int anoActual = now.getYear();
        int diferencia = 0;

        for (int i = 0; i < datos.size(); i++) {
            JsonObject elemento = (JsonObject) datos.get(i);
            String[] c = elemento.get("FECHA").toString().replace(" de", "").replace("\"", "").split(" ");

            //System.out.println(diaHoy + "\n" + mesActual + "\n" + anoActual);
            int dia = Integer.parseInt(c[0]);
            int mes = meses.get(c[1]);
            int ano = Integer.parseInt(c[2]);

            //System.out.println("DIA: " + dia + "\n" + "MES: " + mes + "\n" + "ANO: " + ano);
            if (anoActual < ano) {
                diferencia += 365 * (ano - anoActual);
                System.out.println("Ano mayor " + diferencia);
            } else if (anoActual != ano) {
                diferencia = 0;
                System.out.println("Ano menor " + diferencia);
                return diferencia;
            }
            if (mesActual < mes) {
                diferencia += 30 * (mes - mesActual);
                System.out.println("mes mayor " + diferencia);
            } else if (mesActual > mes) {
                if(anoActual < ano)
                {
                    diferencia += 30 * (mes - mesActual);
                    System.out.println("Mes - anoactual es mayor o igual");
                }else if(anoActual > ano)
                {
                    diferencia = 0;
                    return diferencia;
                }
                System.out.println("Mes menor " + diferencia);
            }

            if (diaHoy > dia) {
                diferencia += 1 * (dia - diaHoy);
                System.out.println("Dia mayor " + diferencia);

            } else if (diaHoy < dia) {
                if (mesActual == mes) {
                    if(anoActual < ano)
                    {
                        diferencia += 1 * (dia - diaHoy);
                    }else if(anoActual >= ano)
                    {
                        diferencia -= 1 * (dia - diaHoy);
                    }
                    
                } else if(mesActual > mes){
                    diferencia += 1 * (dia - diaHoy);
                    System.out.println("XD?");
                }else if(mesActual < mes)
                {
                    diferencia += 1 * (dia - diaHoy);
                }

                System.out.println("Dia menor " + diferencia);
            }

            //System.out.println("DIFERENCIA: " + diferencia);
        }

        return Math.abs(diferencia);
    }

    public static void main(String[] args) {

        //JFrame mainForm = new mainForm();
        JsonArray datos = obtenerDatos();
        System.out.println("DIAS DIF: " + diasDiferencia(datos));
    }

}
