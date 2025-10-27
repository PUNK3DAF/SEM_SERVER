/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        try {
            if (connection == null || connection.isClosed()) {
                String url = konfiguracija.Konfiguracija.getInstanca().getKonfiguracija("url");
                String user = konfiguracija.Konfiguracija.getInstanca().getKonfiguracija("username");
                String pass = konfiguracija.Konfiguracija.getInstanca().getKonfiguracija("password");
                try {
                    connection = DriverManager.getConnection(url, user, pass);
                    connection.setAutoCommit(false);
                } catch (SQLException ex) {
                    Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DbConnectionFactory getInstanca() {
        if (instanca == null) {
            instanca = new DbConnectionFactory();
        }

        return instanca;
    }

    public Connection getConnection() {
        return connection;
    }

}
