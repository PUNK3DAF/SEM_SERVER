package konfiguracija;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Konfiguracija {

    private static Konfiguracija instanca;
    private Properties konfiguracija;

    private Konfiguracija() {
        konfiguracija = new Properties();
        try {
            konfiguracija.load(new FileInputStream("C:\\Users\\vldmrk\\OneDrive - Fakultet organizacionih nauka\\Documents\\NetBeansProjects\\SEM_SERVER\\config\\config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Konfiguracija getInstanca() {
        if (instanca == null) {
            instanca = new Konfiguracija();
        }

        return instanca;
    }

    public String getKonfiguracija(String key) {
        return konfiguracija.getProperty(key, "n/a");
    }

    public void setKonfiguracija(String key, String value) {
        konfiguracija.setProperty(key, value);
    }

    public void sacuvajIzmene() {
        try {
            konfiguracija.store(new FileOutputStream("C:\\Users\\vldmrk\\OneDrive - Fakultet organizacionih nauka\\Documents\\NetBeansProjects\\SEM_SERVER\\config\\config.properties"), null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
