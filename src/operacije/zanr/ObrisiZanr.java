package operacije.zanr;

import domen.Zanr;
import operacije.ApstraktnaGenerickaOperacija;

public class ObrisiZanr extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Zanr)) {
            throw new Exception("Sistem ne moze da obrise zanr.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Zanr) param);
    }
}
