/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vldmrk
 */
public class Konfiguracija {

    private static Konfiguracija instanca;
    private Properties konfiguracija;

    private Konfiguracija() {
        konfiguracija = new Properties();
        try {
            konfiguracija.load(new FileInputStream("C:\\Users\\vldmrk\\OneDrive - Fakultet organizacionih nauka\\Documents\\NetBeansProjects\\SEM_SERVER\\config\\config.properties"));
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
