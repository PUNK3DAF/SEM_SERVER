package operacije.ansambli;

import domen.Ansambl;
import domen.Ucesce;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class KreirajAnsambl extends ApstraktnaGenerickaOperacija {

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
        Ansambl a = (Ansambl) param;
        broker.add(a);

        List<Ansambl> svi = (List<Ansambl>) (List<?>) broker.getAll(new Ansambl(), null);
        if (svi != null && !svi.isEmpty()) {
            int maxId = 0;
            for (Ansambl x : svi) {
                if (x.getAnsamblID() > maxId) {
                    maxId = x.getAnsamblID();
                }
            }
            a.setAnsamblID(maxId);
        }

        List<Ucesce> lista = a.getUcesca();
        if (lista != null) {
            for (Ucesce u : lista) {
                u.setAnsambl(a);
                broker.add(u);
            }
        }
    }

}
