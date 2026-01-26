package operacije.mesto;

import domen.Mesto;
import operacije.ApstraktnaGenerickaOperacija;

public class ObrisiMesto extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Mesto)) {
            throw new Exception("Sistem ne moze da obrise mesto.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Mesto) param);
    }
}
