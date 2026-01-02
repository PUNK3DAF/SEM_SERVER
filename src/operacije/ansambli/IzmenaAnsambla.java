package operacije.ansambli;

import domen.Ansambl;
import domen.Ucesce;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class IzmenaAnsambla extends ApstraktnaGenerickaOperacija {

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
        broker.edit(a);

        // Delete existing participations for this ensemble (in-memory filter)
        List<Ucesce> postojece = (List<Ucesce>) (List<?>) broker.getAll(new Ucesce(), null);
        if (postojece != null) {
            for (Ucesce u : postojece) {
                if (u != null && u.getAnsambl() != null && u.getAnsambl().getAnsamblID() == a.getAnsamblID()) {
                    broker.delete(u);
                }
            }
        }

        // Insert the new participation set
        List<Ucesce> lista = a.getUcesca();
        if (lista != null) {
            for (Ucesce u : lista) {
                u.setAnsambl(a);
                broker.add(u);
            }
        }
    }

}
