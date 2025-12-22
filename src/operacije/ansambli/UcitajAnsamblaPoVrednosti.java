package operacije.ansambli;

import domen.Ansambl;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajAnsamblaPoVrednosti extends ApstraktnaGenerickaOperacija {

    private List<Ansambl> ansambli;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String vrednost = (String) param;
        String where = " WHERE obrisan=0 AND (imeAnsambla LIKE '%" + vrednost + "%' OR opisAnsambla LIKE '%" + vrednost + "%')";
        ansambli = broker.getAll(new Ansambl(), where);
    }

    public List<Ansambl> getAnsambli() {
        return ansambli;
    }

}
