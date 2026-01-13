package repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vldmrk
 */
public class DbConnectionFactory {

    private static DbConnectionFactory instanca;
    private Connection connection;

    private DbConnectionFactory() {

    }

    public static DbConnectionFactory getInstanca() {
        if (instanca == null) {
            instanca = new DbConnectionFactory();
        }

        return instanca;
    }

    public synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = konfiguracija.Konfiguracija.getInstanca().getKonfiguracija("url");
                String user = konfiguracija.Konfiguracija.getInstanca().getKonfiguracija("username");
                String pass = konfiguracija.Konfiguracija.getInstanca().getKonfiguracija("password");
                try {
                    System.out.println("SERVER DEBUG: konektujem na DB url=" + url + " user=" + user);
                    connection = DriverManager.getConnection(url, user, pass);
                    connection.setAutoCommit(false);
                    Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.INFO, "DB connection opened/reopened");
                } catch (SQLException ex) {
                    Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, "Failed to open DB connection", ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public synchronized void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException ex) {
                Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
