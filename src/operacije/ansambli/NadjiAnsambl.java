package operacije.ansambli;

import domen.Ansambl;
import java.util.ArrayList;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 * Pretraži ansambla po vrednosti (ime ansambla)
 */
public class NadjiAnsambl extends ApstraktnaGenerickaOperacija {

    private List<Ansambl> ansambli;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof String)) {
            throw new Exception("Neispravan parametar za pretragu ansambla.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String vrednost = (String) param;
        List<Ansambl> svi = (List<Ansambl>) (List<?>) broker.getAll(new Ansambl(), null);
        ansambli = new ArrayList<>();
        
        if (svi != null) {
            for (Ansambl a : svi) {
                if (a.getImeAnsambla() != null && a.getImeAnsambla().toLowerCase().contains(vrednost.toLowerCase())) {
                    ansambli.add(a);
                }
            }
        }
    }

    public List<Ansambl> getAnsambli() {
        return ansambli;
    }

}
