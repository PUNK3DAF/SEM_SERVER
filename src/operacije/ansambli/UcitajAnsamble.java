package operacije.ansambli;

import domen.Ansambl;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author vldmrk
 */
public class UcitajAnsamble extends ApstraktnaGenerickaOperacija {

    private List<Ansambl> ansambli;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Ansambl> svi = broker.getAll(new Ansambl(), null);
        java.util.List<Ansambl> aktivni = new java.util.ArrayList<>();
        if (svi != null) {
            for (Ansambl a : svi) {
                if (a != null && a.getObrisan() == 0) {
                    aktivni.add(a);
                }
            }
        }
        ansambli = aktivni;
    }

    public List<Ansambl> getAnsambli() {
        return ansambli;
    }

}
