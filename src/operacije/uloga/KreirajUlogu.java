package operacije.uloga;

import domen.Uloga;
import operacije.ApstraktnaGenerickaOperacija;

public class KreirajUlogu extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Uloga)) {
            throw new Exception("Sistem ne moze da doda ulogu.");
        }
        Uloga u = (Uloga) param;
        if (u.getNaziv() == null || u.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv uloge je obavezan.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Uloga) param);
    }
}
