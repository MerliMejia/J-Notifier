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
        boolean notificar = false;

        for (int i = 0; i < datos.size(); i++) {
            diferencia = 0;
            JsonObject elemento = (JsonObject) datos.get(i);
            String[] c = elemento.get("FECHA").toString().replace(" de", "").replace("\"", "").split(" ");

            //System.out.println(diaHoy + "\n" + mesActual + "\n" + anoActual);
            int dia = Integer.parseInt(c[0]);
            int mes = meses.get(c[1]);
            int ano = Integer.parseInt(c[2]);

            //System.out.println("DIA: " + dia + "\n" + "MES: " + mes + "\n" + "ANO: " + ano);
            if (anoActual < ano) {
                diferencia += 365 * (ano - anoActual);
                //System.out.println("Ano mayor " + diferencia);
            } else if (anoActual != ano) {
                diferencia = 0;
                //System.out.println("Ano menor " + diferencia);
                //System.out.println(diferencia);
            }
            if (mesActual < mes) {
                diferencia += 30 * (mes - mesActual);
                //System.out.println("mes mayor " + diferencia);
            } else if (mesActual > mes) {
                if (anoActual < ano) {
                    diferencia += 30 * (mes - mesActual);
                    //System.out.println("Mes - anoactual es mayor o igual");
                } else if (anoActual > ano) {
                    diferencia = 0;
                    //System.out.println(diferencia);
                }
                //System.out.println("Mes menor " + diferencia);
            }

            if (diaHoy > dia) {
                if (mesActual < mes) {
                    diferencia += 1 * (dia - diaHoy);
                } else {
                    if (anoActual > ano) {
                        diferencia = 0;
                        //System.out.println(diferencia);
                    } else {
                        diferencia += 1 * (dia - diaHoy);
                    }

                }

                //System.out.println("Dia mayor " + diferencia);
            } else if (diaHoy < dia) {
                if (mesActual == mes) {
                    if (anoActual < ano) {
                        diferencia += 1 * (dia - diaHoy);
                    } else if (anoActual >= ano) {
                        diferencia -= 1 * (dia - diaHoy);
                    }

                } else if (mesActual > mes) {
                    diferencia += 1 * (dia - diaHoy);
                    //System.out.println("XD?");
                } else if (mesActual < mes) {
                    diferencia += 1 * (dia - diaHoy);
                }

                //System.out.println("Dia menor " + diferencia);
            }

            //System.out.println("DIFERENCIA: " + diferencia);
            //System.out.println(Math.abs(diferencia));
            if (Math.abs(diferencia) <= 6) {
                notificar = true;
            }

        }

        System.out.println(Math.abs(diferencia));
        if (notificar == true) {
            System.out.println("CONO");
            hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        mainForm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        notificar();
                        try {
                            hilo.sleep(5 * 1000);
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

        for(int i = 0; i < tray.getTrayIcons().length; i++)
        {
            tray.remove(tray.getTrayIcons()[i]);
        }
        Image image = Toolkit.getDefaultToolkit().createImage(resources.class.getResource("check.png"));

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
    
    public static void arrancar(JsonArray datos)
    {
        
        if(hilo != null)
        {
            hilo.stop();
            hilo = null;
            mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SystemTray tray = SystemTray.getSystemTray();

            for(int i = 0; i < tray.getTrayIcons().length; i++)
            {
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
