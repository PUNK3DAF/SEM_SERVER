package operacije.uloga;

import domen.Uloga;
import operacije.ApstraktnaGenerickaOperacija;

public class ObrisiUloga extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Uloga)) {
            throw new Exception("Sistem ne moze da obrise ulogu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Uloga) param);
    }
}
