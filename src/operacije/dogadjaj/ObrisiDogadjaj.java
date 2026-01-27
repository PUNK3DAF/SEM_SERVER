package operacije.dogadjaj;

import domen.Dogadjaj;
import operacije.ApstraktnaGenerickaOperacija;

public class ObrisiDogadjaj extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Dogadjaj)) {
            throw new Exception("Nije prosledjen validan dogadjaj!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Dogadjaj d = (Dogadjaj) param;
        broker.delete(d);
    }
}
