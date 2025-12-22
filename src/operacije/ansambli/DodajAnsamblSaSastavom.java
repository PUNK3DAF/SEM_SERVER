package operacije.ansambli;

import domen.Ansambl;
import domen.Ucesce;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;
import repository.db.DbConnectionFactory;
import java.sql.*;

/**
 *
 * @author vldmrk
 */
public class DodajAnsamblSaSastavom extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Ansambl)) {
            throw new Exception("Neispravan parametar za dodavanje ansambla.");
        }
        Ansambl a = (Ansambl) param;
        if (a.getImeAnsambla() == null || a.getImeAnsambla().trim().isEmpty()) {
            throw new Exception("Ime ansambla je obavezno.");
        }
        if (a.getAdmin() == null) {
            throw new Exception("Admin mora biti postavljen za ansambl.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Ansambl a = (Ansambl) param;
        broker.add(a);

        Statement st = DbConnectionFactory.getInstanca().getConnection().createStatement();
        try {
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() AS id");
            if (rs.next()) {
                int generated = rs.getInt("id");
                a.setAnsamblID(generated);
            }
            rs.close();
        } finally {
            st.close();
        }

        List<Ucesce> lista = a.getUcesca();
        if (lista != null) {
            for (Ucesce u : lista) {
                u.setAnsambl(a);
                broker.add(u);
            }
            int ukupno = lista.size();
            System.out.println("SERVER DEBUG: zavrseno ubacivanje ucesca, ukupno = " + ukupno);
        }
    }

}
