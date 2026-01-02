package operacije.ansambli;

import domen.Ansambl;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author vldmrk
 */
public class UcitajAnsamblById extends ApstraktnaGenerickaOperacija {

    private Ansambl ansambl;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Ansambl)) {
            throw new Exception("Neispravan parametar za ucitavanje ansambla po ID.");
        }
        Ansambl a = (Ansambl) param;
        if (a.getAnsamblID() <= 0) {
            throw new Exception("Neispravan ID ansambla.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Ansambl a = (Ansambl) param;
        List<Ansambl> lista = broker.getAll(new Ansambl(), null);
        ansambl = null;
        if (lista != null) {
            for (Ansambl x : lista) {
                if (x != null && x.getAnsamblID() == a.getAnsamblID()) {
                    ansambl = x;
                    break;
                }
            }
        }
    }

    public Ansambl getAnsambl() {
        return ansambl;
    }
}
