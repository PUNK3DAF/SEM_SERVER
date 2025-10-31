/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.ansambli;

import domen.Ansambl;
import domen.Ucesce;
import operacije.ApstraktnaGenerickaOperacija;
import java.sql.*;
import java.util.List;
import repository.db.DbConnectionFactory;

/**
 *
 * @author vldmrk
 */
public class AzurirajSastavAnsambla extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Ansambl)) {
            throw new Exception("Neispravan parametar za azuriranje sastava ansambla.");
        }
        Ansambl a = (Ansambl) param;
        if (a.getAnsamblID() <= 0) {
            throw new Exception("Nevalidan ID ansambla za azuriranje.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Ansambl a = (Ansambl) param;

        // 1) update osnovnih podataka ansambla
        broker.edit(a);

        // 2) obriši sve postojeće ucesce za taj ansambl
        Statement st = DbConnectionFactory.getInstanca().getConnection().createStatement();
        try {
            st.executeUpdate("DELETE FROM ucesce WHERE ansambl = " + a.getAnsamblID());
        } finally {
            st.close();
        }

        // 3) ubaci nova ucesca
        List<Ucesce> lista = a.getUcesca();
        if (lista != null) {
            for (Ucesce u : lista) {
                u.setAnsambl(a);
                broker.add(u);
            }
        }
    }

}
