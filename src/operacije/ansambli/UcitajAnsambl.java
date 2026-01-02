package operacije.ansambli;

import domen.Ansambl;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Učitaj ansambl po ID
 */
public class UcitajAnsambl extends ApstraktnaGenerickaOperacija {

    private Ansambl ansambl;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Ansambl)) {
            throw new Exception("Neispravan parametar za učitavanje ansambla.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Ansambl probe = (Ansambl) param;
        List<Ansambl> svi = (List<Ansambl>) (List<?>) broker.getAll(new Ansambl(), null);
        
        if (svi != null) {
            for (Ansambl a : svi) {
                if (a.getAnsamblID() == probe.getAnsamblID()) {
                    this.ansambl = a;
                    return;
                }
            }
        }
        this.ansambl = null;
    }

    public Ansambl getAnsambl() {
        return ansambl;
    }

}
