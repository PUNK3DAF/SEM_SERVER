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
        ansambli = (svi == null) ? new java.util.ArrayList<>() : svi;
    }

    public List<Ansambl> getAnsambli() {
        return ansambli;
    }

}
