package operacije.ansambli;

import domen.Ansambl;
import domen.ApstraktniDomenskiObjekat;
import domen.Ucesce;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

public class ObrisiAnsambl extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Ansambl)) {
            throw new Exception("Sistem ne moze da obrise ansambl.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Ansambl a = (Ansambl) param;

        Ucesce probe = new Ucesce();
        probe.setAnsambl(a);
        List<ApstraktniDomenskiObjekat> ucesca = broker.getAll(probe, null);

        if (ucesca != null && !ucesca.isEmpty()) {
            for (ApstraktniDomenskiObjekat u : ucesca) {
                if (u instanceof Ucesce) {
                    Ucesce ux = (Ucesce) u;
                    if (ux.getAnsambl() != null && ux.getAnsambl().getAnsamblID() == a.getAnsamblID()) {
                        broker.delete(u);
                    }
                }
            }
        }

        broker.delete(a);
    }

}
