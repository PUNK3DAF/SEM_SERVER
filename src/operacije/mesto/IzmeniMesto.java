package operacije.mesto;

import domen.Mesto;
import operacije.ApstraktnaGenerickaOperacija;

public class IzmeniMesto extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Mesto)) {
            throw new Exception("Sistem ne moze da izmeni mesto.");
        }
        Mesto m = (Mesto) param;
        if (m.getNaziv() == null || m.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv mesta je obavezan.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Mesto) param);
    }
}
