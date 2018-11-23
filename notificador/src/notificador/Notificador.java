package notificador;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import notificador.UI.mainForm;
import notificador.resources.resources;

/**
 *
 * @author Merli Mejia
 */
public class Notificador {

    private static Map<String, Integer> meses = new HashMap<>();
    public static Thread hilo;
    private static String cierre = "";

    public static JsonArray obtenerDatos() {
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

    private static void diasDiferencia(JsonArray datos) {

        String rutaJSON = System.getProperty("user.dir") + "\\DBB.json";//RUTA DEL .JSON

        JsonParser parse = new JsonParser();
        Object obj;
        JsonObject json = null;
        try {
            obj = parse.parse(new FileReader(rutaJSON)); //LEO EL JSON
            json = (JsonObject) obj;//JSON OBJECT
            cierre = json.get("cierre").toString().replace("\"", "");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        String[] cierreArray = cierre.replace(" de", "").replace("\"", "").split(" ");

        int diaCierre = Integer.parseInt(cierreArray[0]);
        int mesCierre = meses.get(cierreArray[1]);
        int anoCierre = Integer.parseInt(cierreArray[2]);

        boolean notificar = false;

        for (int i = 0; i < datos.size(); i++) {
            JsonObject elemento = (JsonObject) datos.get(i);
            String[] c = elemento.get("FECHA").toString().replace(" de", "").replace("\"", "").split(" ");

            //System.out.println(diaHoy + "\n" + mesActual + "\n" + anoActual);
            int dia = Integer.parseInt(c[0]);
            int mes = meses.get(c[1]);
            int ano = Integer.parseInt(c[2]);

            if (anoCierre == ano) {
                System.out.println("ano cierre es igual");
                if (mesCierre > mes) {
                    System.out.println("Mes cierre es mayor");
                    notificar = true;
                } else {
                    if (mesCierre == mes) {
                        if (diaCierre >= dia) {
                            System.out.println("Dia es mayor");
                            notificar = true;
                        }
                    }
                }
            }

            if (anoCierre > ano) {
                System.out.println("ano cierre es mayor");
                notificar = true;
            } else if (anoCierre < ano) {
                System.out.println("ano cierre es menor");
                notificar = false;
            }

        }

        if (notificar == true) {
            hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        mainForm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        notificar();
                        try {
                            hilo.sleep(60 * 1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Notificador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            });
            hilo.start();
        }

    }

    private static void notificar() {
        SystemTray tray = SystemTray.getSystemTray();

        for (int i = 0; i < tray.getTrayIcons().length; i++) {
            tray.remove(tray.getTrayIcons()[i]);
        }
        Image image = Toolkit.getDefaultToolkit().createImage(resources.class.getResource("logo.png"));

        TrayIcon trayIcon = new TrayIcon(image, "DEMO!");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("J-NOTIFIER - Made By Merli Mejia");
        try {
            tray.add(trayIcon);
            trayIcon.displayMessage("REALIZAR RESERVA!", "Debes realizar una reserva para uno o más restaurantes", MessageType.INFO);

            trayIcon.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {

                        mainForm.setVisible(true);
                        tray.remove(trayIcon);
                    }
                }
            });
        } catch (AWTException ex) {
            Logger.getLogger(Notificador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void arrancar(JsonArray datos) {

        if (hilo != null) {
            hilo.stop();
            hilo = null;
            mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SystemTray tray = SystemTray.getSystemTray();

            for (int i = 0; i < tray.getTrayIcons().length; i++) {
                tray.remove(tray.getTrayIcons()[i]);
            }
        }
        diasDiferencia(datos);
    }

    public static JsonArray datos;
    public static JFrame mainForm;

    public static void main(String[] args) {

        mainForm = new mainForm();
        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        datos = obtenerDatos();
        arrancar(datos);
    }

}
