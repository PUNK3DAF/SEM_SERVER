package operacije.zanr;

import domen.Zanr;
import operacije.ApstraktnaGenerickaOperacija;

public class KreirajZanr extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Zanr)) {
            throw new Exception("Sistem ne moze da doda zanr.");
        }
        Zanr z = (Zanr) param;
        if (z.getNaziv() == null || z.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv zanra je obavezan.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Zanr) param);
    }
}
