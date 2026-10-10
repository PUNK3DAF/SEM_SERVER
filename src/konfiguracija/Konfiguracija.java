package konfiguracija;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Konfiguracija {

    private static final String PROPERTY_NAME = "sem.server.config";
    private static final String ENV_NAME = "SEM_SERVER_CONFIG";

    private static Konfiguracija instanca;
    private Properties konfiguracija;
    private String aktivnaPutanja;

    private Konfiguracija() {
        konfiguracija = new Properties();
        aktivnaPutanja = pronadjiPutanjuKonfiguracije();
        ucitajKonfiguraciju();
    }

    private String pronadjiPutanjuKonfiguracije() {
        String izSvojstva = System.getProperty(PROPERTY_NAME);
        if (izSvojstva != null && !izSvojstva.trim().isEmpty()) {
            return izSvojstva.trim();
        }

        String izOkoline = System.getenv(ENV_NAME);
        if (izOkoline != null && !izOkoline.trim().isEmpty()) {
            return izOkoline.trim();
        }

        Path userHomeConfig = Paths.get(System.getProperty("user.home"), ".sem-server", "config.properties");
        if (Files.exists(userHomeConfig)) {
            return userHomeConfig.toString();
        }

        Path repoConfig = Paths.get(System.getProperty("user.dir"), "config", "config.properties");
        if (Files.exists(repoConfig)) {
            return repoConfig.toString();
        }

        return userHomeConfig.toString();
    }

    private void ucitajKonfiguraciju() {
        File fajl = new File(aktivnaPutanja);
        if (!fajl.exists()) {
            try {
                Path parent = fajl.toPath().getParent();
                if (parent != null) {
                    Files.createDirectories(parent);
                }
                Files.createFile(fajl.toPath());
            } catch (IOException ex) {
                System.err.println("Nije moguce kreirati konfiguracioni fajl na putanji: " + aktivnaPutanja);
            }
            return;
        }

        try (FileInputStream fis = new FileInputStream(fajl)) {
            konfiguracija.load(fis);
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
        try (FileOutputStream fos = new FileOutputStream(aktivnaPutanja)) {
            konfiguracija.store(fos, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
