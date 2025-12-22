package operacije.ansambli;

import domen.Ansambl;
import domen.ApstraktniDomenskiObjekat;
import domen.Ucesce;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class ObrisiAnsambl extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Ansambl)) {
            throw new Exception("Sistem ne moze da obrise ansambl.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Ansambl a = (Ansambl) param;

        // Prvo učitaj sve Ucesce zapise vezane za ovaj ansambl
        Ucesce probe = new Ucesce();
        probe.setAnsambl(a);
        List<ApstraktniDomenskiObjekat> ucesca = broker.getAll(probe, " WHERE ansambl = " + a.getAnsamblID());

        // Obriši sve učešće zapise da članovi ostanu, ali njihova učešća u ansamblu budu obrisana
        if (ucesca != null && !ucesca.isEmpty()) {
            for (ApstraktniDomenskiObjekat u : ucesca) {
                broker.delete(u);
            }
        }

        // Zatim označi ansambl kao obrisan
        a.setObrisan(1);
        broker.edit(a);
    }

}
