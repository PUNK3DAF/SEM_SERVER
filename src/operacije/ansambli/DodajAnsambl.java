package operacije.ansambli;

import domen.Ansambl;
import domen.Ucesce;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class DodajAnsambl extends ApstraktnaGenerickaOperacija {

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

        // Try to retrieve the generated ID using a simple DESC LIMIT query through the same repository
        List<Ansambl> poslednji = (List<Ansambl>) (List<?>) broker.getAll(new Ansambl(), " ORDER BY ansamblID DESC LIMIT 1");
        if (poslednji != null && !poslednji.isEmpty()) {
            a.setAnsamblID(poslednji.get(0).getAnsamblID());
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
