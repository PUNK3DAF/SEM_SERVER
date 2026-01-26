package operacije.uloga;

import domen.Uloga;
import operacije.ApstraktnaGenerickaOperacija;

public class IzmeniUloga extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Uloga)) {
            throw new Exception("Sistem ne moze da izmeni ulogu.");
        }
        Uloga u = (Uloga) param;
        if (u.getNaziv() == null || u.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv uloge je obavezan.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Uloga) param);
    }
}
