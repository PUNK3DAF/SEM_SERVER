package operacije.ansambli;

import domen.Ansambl;
import operacije.ApstraktnaGenerickaOperacija;

public class AzurirajAnsambl extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Ansambl)) {
            throw new Exception("Sistem ne moze da doda ansambl.");
        }
        Ansambl a = (Ansambl) param;
        if (a.getImeAnsambla() == null || a.getImeAnsambla().isEmpty()) {
            throw new Exception("NEPRAVILAN UNOS IMENA ANSAMBLA");
        }

        if (a.getOpisAnsambla() == null || a.getOpisAnsambla().isEmpty()) {
            throw new Exception("NEPRAVILAN UNOS OPISA ANSAMBLA");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Ansambl) param);
    }

}
