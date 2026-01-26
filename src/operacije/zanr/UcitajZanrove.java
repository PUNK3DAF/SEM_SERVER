package operacije.zanr;

import domen.Zanr;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class UcitajZanrove extends ApstraktnaGenerickaOperacija {

    private List<Zanr> zanrovi;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        zanrovi = (List<Zanr>) (List<?>) broker.getAll(new Zanr(), null);
    }

    public List<Zanr> getZanrovi() {
        return zanrovi;
    }
}
